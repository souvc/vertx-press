package io.vertPress.manage;

import io.vertPress.manage.auth.AuthHandle;
import io.vertPress.manage.init.InitDatabase;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;

/**
 * @ClassName: ManageServer
 * @Description: TODO 主入口
 * @author FoamValue foamvalue@live.cn
 * @date 2017年2月26日 下午2:02:18
 * 
 */
public class ManageServer extends AbstractVerticle {

	/**
	 * @Fields LOGGER : 日志对象
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ManageServer.class);
	
	/**
	 * @Fields ENGINE : 模版引擎
	 */
	private final static FreeMarkerTemplateEngine ENGINE = FreeMarkerTemplateEngine.create();

	/**
	 * @Fields MYSQL : 数据库对象
	 */
	private static JsonObject MYSQL = null;

	private static AsyncSQLClient sqlClient = null;

	private final static int SERVER_PORT = 8080;

	private final static String DB_HOST = "127.0.0.1";
	private final static int DB_PROT = 3306;
	private final static int DB_MAX_POOL_SIZE = 10;
	private final static String DB_USERNAME = "root";
	private final static String DB_PASSWORD = "cxj123";
	private final static String DB_DATABASE = "vertx-press";
	private final static String DB_CHARSET = "UTF-8";
	private final static int DB_QUERY_TIMEOUT = 10000;

	static {
		// 初始化数据库链接对象
		if (MYSQL == null) {
			MYSQL = new JsonObject()
					.put("host", DB_HOST)
					.put("port", DB_PROT)
					.put("maxPoolSize", DB_MAX_POOL_SIZE)
					.put("username", DB_USERNAME)
					.put("password", DB_PASSWORD)
					.put("database", DB_DATABASE)
					.put("charset", DB_CHARSET)
					.put("queryTimeout", DB_QUERY_TIMEOUT);
		}
	}

	@Override
	public void start() throws Exception {
		// 主路由
		Router router = Router.router(vertx);

		// 初始化数据库对象
		sqlClient = MySQLClient.createShared(vertx, MYSQL);
		router.route().handler(routingContext -> sqlClient.getConnection(res -> {
			if (res.failed()) {
				routingContext.fail(res.cause());
			} else {
				SQLConnection conn = res.result();
				routingContext.put("conn", conn);
				routingContext.addHeadersEndHandler(done -> conn.close(v -> {
				}));
				routingContext.next();
			}
		})).failureHandler(routingContext -> {
			SQLConnection conn = routingContext.get("conn");
			if (conn != null) {
				conn.close(v -> {
				});
			}
		});

		/**
		 * 初始化路由 - 数据库
		 * */
		Router initRouter = Router.router(vertx);
		initRouter.get("/database").handler(new InitDatabase()::handleGetProduct);
		router.mountSubRouter("/init", initRouter);
				
		/**
		 * 管理端路由
		 * */
		Router manageRouter = Router.router(vertx);
		manageRouter.route().handler(CookieHandler.create());
		manageRouter.route().handler(BodyHandler.create());
		manageRouter.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
		manageRouter.route().handler(routingContext -> {
			routingContext.put("name", "Vert.x Web");
			ENGINE.render(routingContext, "templates/index.ftl", res -> {
				if (res.succeeded()) {
					routingContext.response().end(res.result());
				} else {
					routingContext.fail(res.cause());
				}
			});
		});
		manageRouter.route("/private/*").handler(new AuthHandle()::handleGetProduct);
		router.mountSubRouter("/manage", manageRouter);

		LOGGER.debug("ManageServer is running.");
		// 启动服务
		vertx.createHttpServer().requestHandler(router::accept).listen(SERVER_PORT);
	}

}
