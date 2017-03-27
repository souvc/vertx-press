package io.vertPress.manage.handle;

import io.vertPress.manage.handle.impl.AuthHandlerImpl;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * @ClassName: AuthHandler
 * @Description: TODO 权限根接口
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月15日 上午10:46:32
 * 
 */
public interface AuthHandler extends Handler<RoutingContext> {

	/**
	 * @Fields DEFAULT_RETURN_URL_PARAM : 默认返回 url
	 */
	String DEFAULT_RETURN_URL_PARAM = "return_url";

	static AuthHandler create(String loginRedirectURL) {
		return new AuthHandlerImpl(loginRedirectURL, DEFAULT_RETURN_URL_PARAM);
	}

	static AuthHandler create(String loginRedirectURL, String returnURLParam) {
		return new AuthHandlerImpl(loginRedirectURL, returnURLParam);
	}
	
	
}
