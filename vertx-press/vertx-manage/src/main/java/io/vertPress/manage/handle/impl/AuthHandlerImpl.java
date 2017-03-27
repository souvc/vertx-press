package io.vertPress.manage.handle.impl;

import io.vertPress.manage.handle.AuthHandler;
import io.vertPress.manage.utils.ResultUtil;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.impl.UserHolder;

/**
 * @ClassName: UserHandleImpl
 * @Description: TODO 权限根接口实现类
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月15日 上午10:46:46
 * 
 */
public class AuthHandlerImpl implements AuthHandler {

	/**
	 * @Fields SESSION_USER_HOLDER_KEY : 缓存键名
	 */
	private static final String SESSION_USER_HOLDER_KEY = "__vertx.press.userHolder";

	private final String loginRedirectURL;

	private final String returnURLParam;

	public AuthHandlerImpl(String loginRedirectURL, String returnURLParam) {
		this.loginRedirectURL = loginRedirectURL;
		this.returnURLParam = returnURLParam;
	}

	@Override
	public void handle(RoutingContext event) {
		Session session = event.session();
		if (session != null) {
			if (session.get(SESSION_USER_HOLDER_KEY) != null) {
				event.next();
			} else {
				session.put(SESSION_USER_HOLDER_KEY, new UserHolder(event));
				session.put(returnURLParam, event.request().uri());
				ResultUtil.redirectURL(event.response(), loginRedirectURL);
			}
		} else {
			event.fail(new NullPointerException("No session - did you forget to include a SessionHandler?"));
		}
	}

}
