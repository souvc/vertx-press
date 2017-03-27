package io.vertPress.manage;

import io.vertPress.manage.handle.AuthHandler;
import io.vertPress.manage.handle.LoginHandler;
import io.vertPress.manage.init.InitDatabase;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
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

	private final static int SERVER_PORT = 8080;

	@Override
	public void start() throws Exception {
		// 初始化数据
		new InitDatabase().setUpInitialData();

		// 主路由
		Router router = Router.router(vertx);

		// We need cookies, sessions and request bodies
		router.route().handler(CookieHandler.create());
		router.route().handler(BodyHandler.create());
		router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

		// Any requests to URI starting '/manage/' require login
		router.route("/manage/*").handler(AuthHandler.create("/login.html"));

		// Handles the actual login
		router.route("/login").handler(LoginHandler.create());

		// Handles the actual logout
	    router.route("/logout").handler(context -> {
	        context.clearUser();
	        context.response().putHeader("location", "/login.html").setStatusCode(302).end();
	      });


		// Serve the static private pages from directory 'webroot'
		router.route().handler(StaticHandler.create().setCachingEnabled(false).setWebRoot("webroot"));

		LOGGER.debug("ManageServer is running.");
		// 启动服务
		vertx.createHttpServer().requestHandler(router::accept).listen(SERVER_PORT);
	}

}
