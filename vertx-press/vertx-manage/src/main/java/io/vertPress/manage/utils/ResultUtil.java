package io.vertPress.manage.utils;

import io.vertx.core.http.HttpServerResponse;

/**
 * @ClassName: ResultUtil
 * @Description: TODO
 * @author FoamValue foamvalue@live.cn
 * @date 2017年2月26日 下午4:33:13
 * 
 */
public final class ResultUtil {

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
	
}
