package io.vertPress.weixin.handle.impl;

import io.vertPress.weixin.handle.PostHandler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class PostHandlerImpl implements PostHandler {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(PostHandlerImpl.class);

	@Override
	public void handle(RoutingContext event) {

		
		HttpServerResponse response = event.response();
		response.putHeader("content-type", "text/plain");
		response.end("POST");

	
	}

}
