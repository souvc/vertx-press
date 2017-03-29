package io.vertPress.manage.handle;

import io.vertPress.manage.handle.impl.RedirectAuthHandlerImpl;

public interface RedirectAuthHandler extends AuthHandler {

	/**
	 * @Fields DEFAULT_RETURN_URL_PARAM : 默认返回 url
	 */
	String DEFAULT_RETURN_URL_PARAM = "return_url";

	static AuthHandler create(String loginRedirectURL) {
		return new RedirectAuthHandlerImpl(loginRedirectURL, DEFAULT_RETURN_URL_PARAM);
	}

	static AuthHandler create(String loginRedirectURL, String returnURLParam) {
		return new RedirectAuthHandlerImpl(loginRedirectURL, returnURLParam);
	}
}
