package io.vertPress.manage.handle;

import io.vertPress.manage.dto.ConstantDTO;
import io.vertPress.manage.handle.impl.LoginHandlerImpl;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * @ClassName: LoginHandler
 * @Description: TODO 登陆接口
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月29日 下午8:07:34
 * 
 */
public interface LoginHandler extends Handler<RoutingContext> {

	String DEFAULT_USERNAME_PARAM = "userName";

	String DEFAULT_PASSWORD_PARAM = "password";

	static LoginHandler create() {
		return new LoginHandlerImpl(DEFAULT_USERNAME_PARAM, DEFAULT_PASSWORD_PARAM, ConstantDTO.DEFAULT_RETURN_URL_PARAM, null);
	}

	static LoginHandler create(String usernameParam, String passwordParam, String returnURLParam, String directLoggedInOKURL) {
		return new LoginHandlerImpl(usernameParam, passwordParam, returnURLParam, directLoggedInOKURL);
	}

	LoginHandler setUserNameParam(String userNameParam);

	LoginHandler setPasswordParam(String passwordParam);

	LoginHandler setReturnURLParam(String returnURLParam);

	LoginHandler setDirectLoggedInOKURL(String directLoggedInOKURL);
}
