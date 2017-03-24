package io.vertPress.manage;

import io.vertPress.manage.handle.RedirectAuthHandler;
import io.vertPress.manage.init.DatabaseUtil;
import io.vertPress.manage.init.InitDatabase;
import io.vertPress.manage.utils.ResultUtil;
import io.vertx.core.AbstractVerticle;
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
	
	private static AsyncSQLClient sqlClient = null;

	private final static int SERVER_PORT = 8080;

	@Override
	public void start() throws Exception {
		// 初始化数据
		new InitDatabase().setUpInitialData();
		
		// 主路由
		Router router = Router.router(vertx);
		// 初始化数据库对象
		sqlClient = MySQLClient.createShared(vertx, DatabaseUtil.getJsonObject());
		router.route().handler(routingContext -> sqlClient.getConnection(res -> {
			if (res.failed()) {
				routingContext.fail(res.cause());
			} else {
				SQLConnection conn = res.result();
				routingContext.put("conn", conn);
				routingContext.addHeadersEndHandler(done -> conn.close(v -> {
				}));
			}
		})).failureHandler(routingContext -> {
			SQLConnection conn = routingContext.get("conn");
			if (conn != null) {
				conn.close(v -> {
				});
			}
		});

		/**
		 * 管理端路由
		 * */
		Router manageRouter = Router.router(vertx);
		manageRouter.route().handler(CookieHandler.create());
		manageRouter.route().handler(BodyHandler.create());
		manageRouter.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
		manageRouter.route().handler(routingContext -> {
			routingContext.put("name", "Vert.x Web");
			ResultUtil.redirectURL(routingContext, "templates/index.ftl");
		});
		manageRouter.route("/private/*").handler(RedirectAuthHandler.check("templates/index.ftl"));
		router.mountSubRouter("/manage", manageRouter);

		LOGGER.debug("ManageServer is running.");
		// 启动服务
		vertx.createHttpServer().requestHandler(router::accept).listen(SERVER_PORT);
	}

}
