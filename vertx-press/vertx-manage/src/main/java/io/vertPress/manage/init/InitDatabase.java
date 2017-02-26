package io.vertPress.manage.init;

import io.vertPress.manage.utils.ResultUtil;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

/**
 * @ClassName: InitDataBase
 * @Description: TODO 初始化数据库
 * @author FoamValue foamvalue@live.cn
 * @date 2017年2月26日 下午4:20:07
 * 
 */
public class InitDatabase {

	public void handleGetProduct(RoutingContext routingContext) {
		HttpServerResponse response = routingContext.response();
		ResultUtil.sendJSON("Initialize the database successfully!", response);
		
	}

}
