package io.vertPress.manage.handle;

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
	
	final static String DEFAULT_USER_SESSION_KEY = "_vertx_user_key";
}
