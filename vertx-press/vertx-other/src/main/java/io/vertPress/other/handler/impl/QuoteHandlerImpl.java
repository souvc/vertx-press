package io.vertPress.other.handler.impl;

import org.apache.commons.lang3.StringUtils;

import io.vertPress.other.handler.QuoteHandler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

/**
 * @ClassName: QuoteHandlerImpl
 * @Description: TODO 报价接口实现类
 * @author FoamValue foamvalue@live.cn
 * @date 2017年5月2日 下午5:25:20
 * 
 */
public class QuoteHandlerImpl implements QuoteHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger(QuoteHandlerImpl.class);

	@Override
	public void handle(RoutingContext event) {

		String productName = event.request().getParam("productName");
		String productModel = event.request().getParam("productModel");
		String productBrand = event.request().getParam("productBrand");

		if (StringUtils.isBlank(productName) || StringUtils.isBlank(productModel)
				|| StringUtils.isBlank(productBrand)) {
			throw new IllegalArgumentException("params is required for GET verify!");
		}

		LOGGER.debug("productName:" + productName + ",productModel:" + productModel + ", productBrand:" + productBrand);

		HttpServerResponse response = event.response();
		response.putHeader("content-type", "text/plain");
		response.end("null");

	}

}
