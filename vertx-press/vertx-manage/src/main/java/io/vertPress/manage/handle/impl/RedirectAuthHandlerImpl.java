package io.vertPress.manage.handle.impl;

import org.apache.commons.lang3.StringUtils;

import io.vertPress.manage.utils.ResultUtil;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;

/**
 * @ClassName: RedirectAuthHandlerImpl
 * @Description: TODO 权限跳转接口实现类
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月15日 下午4:26:42
 * 
 */
public class RedirectAuthHandlerImpl extends AuthHandlerImpl {

	

	private final String loginRedirectURL;

	public RedirectAuthHandlerImpl(String loginRedirectURL) {
		this.loginRedirectURL = loginRedirectURL;
	}

	@Override
	public void handle(RoutingContext event) {
		Session session = event.session();
		if (session != null) {
			String userNo = session.get("userNo");
			if (StringUtils.isNotBlank(userNo)) {
				event.next();
			} else {
				ResultUtil.redirectURL(event, loginRedirectURL);
			}
		} else {
			event.fail(new NullPointerException("No session - did you forget to include a SessionHandler?"));
		}
	}

}
