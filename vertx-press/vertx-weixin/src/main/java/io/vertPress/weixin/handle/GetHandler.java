package io.vertPress.weixin.handle;

import io.vertPress.weixin.handle.impl.GetHandlerImpl;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public interface GetHandler  extends Handler<RoutingContext> {

	static Handler<RoutingContext> create() {
		return  new GetHandlerImpl();
	}
	
	

}
