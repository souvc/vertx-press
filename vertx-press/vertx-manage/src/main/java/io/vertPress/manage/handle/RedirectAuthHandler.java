package io.vertPress.manage.handle;

import io.vertPress.manage.dto.ConstantDTO;
import io.vertPress.manage.handle.impl.RedirectAuthHandlerImpl;

/**
 * @ClassName: RedirectAuthHandler
 * @Description: TODO 请求跳转接口
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月29日 下午8:07:59
 * 
 */
public interface RedirectAuthHandler extends AuthHandler {

	static AuthHandler create(String loginRedirectURL) {
		return new RedirectAuthHandlerImpl(loginRedirectURL, ConstantDTO.DEFAULT_RETURN_URL_PARAM);
	}

	static AuthHandler create(String loginRedirectURL, String returnURLParam) {
		return new RedirectAuthHandlerImpl(loginRedirectURL, returnURLParam);
	}
}
