package io.vertPress.weixin.handle;

import io.vertPress.weixin.handle.impl.PostHandlerImpl;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public interface PostHandler extends Handler<RoutingContext> {

	static Handler<RoutingContext> create() {
		return  new PostHandlerImpl();
	}
}
