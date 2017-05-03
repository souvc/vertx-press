package io.vertPress.other;

import io.vertPress.other.handler.QuoteHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * @ClassName: OtherServer
 * @Description: TODO 第三方服务（多路由）
 * @author FoamValue foamvalue@live.cn
 * @date 2017年5月2日 下午5:03:03
 * 
 */
public class OtherServer extends AbstractVerticle {

	/**
	 * @Fields LOGGER : 日志对象
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(OtherServer.class);

	private final static int SERVER_PORT = 18080;

	@Override
	public void start() throws Exception {

		// 主路由
		Router router = Router.router(vertx);

		router.route().handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/plain");
			response.end("Hello World from Vert.x-Web!");
		});

		// 获取报价
		router.get("/getPrice").handler(QuoteHandler.getPrice());

		router.route().handler(StaticHandler.create());
		LOGGER.debug("Other is running.");
		vertx.createHttpServer().requestHandler(router::accept).listen(SERVER_PORT);
	}
}
