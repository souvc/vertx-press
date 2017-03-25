package io.vertPress.manage.utils;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;

/**
 * @ClassName: ResultUtil
 * @Description: TODO
 * @author FoamValue foamvalue@live.cn
 * @date 2017年2月26日 下午4:33:13
 * 
 */
public final class ResultUtil {
	
	/**
	 * @Fields ENGINE : TODO 模版引擎
	 */
	private final static FreeMarkerTemplateEngine ENGINE = FreeMarkerTemplateEngine.create();

	/**
	 * @Title: sendError
	 * @Description: TODO 返回 HTTP 状态类型
	 * @param @param statusCode
	 * @param @param response
	 * @return void
	 * @throws
	 */
	public static void sendError(int statusCode, HttpServerResponse response) {
		response.setStatusCode(statusCode).end();
	}
	
	/**
	 * @Title: sendJSON
	 * @Description: TODO 返回 JSON 字符串
	 * @param @param result
	 * @param @param response
	 * @return void
	 * @throws
	 */
	public static void sendJSON(String result, HttpServerResponse response) {
		response.putHeader("content-type", "application/json").end(result);
	}
	
	/**
	 * @Title: redirectURL
	 * @Description: TODO 跳转页面
	 * @param @param event
	 * @param @param redirectURL
	 * @return void
	 * @throws
	 */
	public static void redirectURL(RoutingContext rct, String redirectURL) {
		if (redirectURL.contains(".ftl")) {
			ENGINE.render(rct, redirectURL, res -> {
				if (res.succeeded()) {
					rct.response().end(res.result());
				} else {
					rct.fail(res.cause());
				}
			});
		} else {
			rct.response().sendFile(redirectURL);
		}
	}
	
}
