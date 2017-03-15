package io.vertPress.manage.handle;

import io.vertPress.manage.handle.impl.RedirectAuthHandlerImpl;

/**
 * @ClassName: RedirectAuthHandler
 * @Description: TODO 权限跳转接口
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月15日 下午4:25:19
 * 
 */
public interface RedirectAuthHandler extends AuthHandler {

	/**
	 * @Title: check
	 * @Description: TODO 检查权限接口
	 * @param @param loginRedirectURL
	 * @param @return
	 * @return AuthHandler
	 * @throws
	 */
	static AuthHandler check(String loginRedirectURL) {
		return new RedirectAuthHandlerImpl(loginRedirectURL);
	}

}
