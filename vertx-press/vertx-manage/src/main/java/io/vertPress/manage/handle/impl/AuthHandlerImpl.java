package io.vertPress.manage.handle.impl;

import io.vertPress.manage.dto.UserDTO;
import io.vertPress.manage.handle.AuthHandler;
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
	 * @Fields SESSION_USER_HOLDER_KEY : TODO 缓存属性
	 */
	private static final String SESSION_USER_HOLDER_KEY = "__vertx.press.userHolder";

	@Override
	public void handle(RoutingContext event) {
		Session session = event.session();
		if (session != null) {
			UserDTO holder = session.get(SESSION_USER_HOLDER_KEY);
			if (holder != null) {
				event.next();
			} else {
				session.put(SESSION_USER_HOLDER_KEY, new UserHolder(event));
			}
		}
		event.next();
	}

}
