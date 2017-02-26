package io.vertPress.manage;

import io.vertPress.manage.init.InitDatabase;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

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
	 * @Fields MYSQL : 数据库对象
	 */
	private static JsonObject MYSQL = null;
	
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
		
		// 子路由 - 初始化数据库
		Router initRouter = Router.router(vertx);
		initRouter.get("/database").handler(new InitDatabase()::handleGetProduct);
		
		// 子路由 - 管理服务
		Router manageRouter = Router.router(vertx);
		
		// 设置子路由到主路由
		router.mountSubRouter("/init", initRouter);
		router.mountSubRouter("/manage", manageRouter);
		
		LOGGER.debug("ManageServer is running.");
		// 启动服务
		vertx.createHttpServer().requestHandler(router::accept).listen(SERVER_PORT);
	}
}
