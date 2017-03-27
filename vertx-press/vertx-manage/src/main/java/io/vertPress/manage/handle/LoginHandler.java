package io.vertPress.manage.handle;

import io.vertPress.manage.handle.impl.LoginHandlerImpl;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public interface LoginHandler extends Handler<RoutingContext> {

	/**
	 * @Fields DEFAULT_USERNAME_PARAM : 用户名
	 */
	String DEFAULT_USERNAME_PARAM = "userName";

	/**
	 * @Fields DEFAULT_PASSWORD_PARAM : 密码
	 */
	String DEFAULT_PASSWORD_PARAM = "password";

	/**
	 * @Fields DEFAULT_RETURN_URL_PARAM : 默认返回 url
	 */
	String DEFAULT_RETURN_URL_PARAM = "return_url";

	static LoginHandler create() {
		return new LoginHandlerImpl(DEFAULT_USERNAME_PARAM, DEFAULT_PASSWORD_PARAM, DEFAULT_RETURN_URL_PARAM, null);
	}

	static LoginHandler create(String usernameParam, String passwordParam, String returnURLParam, String directLoggedInOKURL) {
		return new LoginHandlerImpl(usernameParam, passwordParam, returnURLParam, directLoggedInOKURL);
	}

	LoginHandler setUserNameParam(String userNameParam);

	LoginHandler setPasswordParam(String passwordParam);

	LoginHandler setReturnURLParam(String returnURLParam);

	LoginHandler setDirectLoggedInOKURL(String directLoggedInOKURL);
}
