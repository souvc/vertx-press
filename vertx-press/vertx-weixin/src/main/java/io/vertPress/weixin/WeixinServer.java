package io.vertPress.weixin;

import io.vertPress.weixin.handle.GetHandler;
import io.vertPress.weixin.handle.PostHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * @ClassName: WeixinServer
 * @Description: TODO 微信模块
 * @author FoamValue foamvalue@live.cn
 * @date 2017年4月28日 下午4:45:00
 * 
 */
public class WeixinServer extends AbstractVerticle {

	/**
	 * @Fields LOGGER : 日志对象
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(WeixinServer.class);
	
	private final static int SERVER_PORT = 8081;

	@Override
	public void start() throws Exception {

		// 主路由
		Router router = Router.router(vertx);
		
		router.get().handler(GetHandler.create());
		router.post().handler(PostHandler.create());
		router.route().handler(StaticHandler.create());
		
		router.route().handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/plain");
			response.end("Hello World from Vert.x-Web!");
		});
		
		LOGGER.debug("Weixin is running.");
		vertx.createHttpServer().requestHandler(router::accept).listen(SERVER_PORT);
	}

}
