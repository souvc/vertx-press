package io.vertPress.weixin.handle.impl;

import org.apache.commons.lang3.StringUtils;

import io.vertPress.weixin.handle.GetHandler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class GetHandlerImpl implements GetHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger(GetHandlerImpl.class);

	@Override
	public void handle(RoutingContext event) {
		String signature = event.request().getParam("signature");
		String timestamp = event.request().getParam("timestamp");
		String nonce = event.request().getParam("nonce");
		String echostr = event.request().getParam("echostr");

		if (StringUtils.isBlank(echostr)) {
			throw new IllegalArgumentException("echostr is required for GET verify!");
		}

		LOGGER.info("signature:" + signature + ",timestamp:" + timestamp + ", nonce:" + nonce + ", echostr:" + echostr);
		
		

		HttpServerResponse response = event.response();
		response.putHeader("content-type", "text/plain");
		response.end(echostr);

	}

}
