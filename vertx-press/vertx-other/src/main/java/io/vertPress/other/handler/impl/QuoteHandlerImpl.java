package io.vertPress.other.handler.impl;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.vertPress.com.utils.HttpUtil;
import io.vertPress.com.utils.JsonUtil;
import io.vertPress.other.handler.QuoteHandler;
import io.vertPress.other.utils.ResourceUtil;
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

	private final static String requestMethod = "GET";

	private final static ResourceUtil util = ResourceUtil.getInstance();

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

		// JD
		String jdSearchUrl = util.getString("jd.search");
		if (StringUtils.isNotBlank(jdSearchUrl)) {
			jdSearchUrl = jdSearchUrl.replace("PRODUCTNAME", productName);
			jdSearchUrl = jdSearchUrl.replace("PRODUCTMODEL", productModel);
			jdSearchUrl = jdSearchUrl.replace("PRODUCTBRAND", productBrand);
			LOGGER.debug(JsonUtil.toJson(jdSearchUrl));
			Document jdSearch = Jsoup.parse(HttpUtil.httpsRequest(jdSearchUrl, requestMethod, null));
			LOGGER.debug(JsonUtil.toJson(jdSearch));
			if (jdSearch != null) {
				Element content = jdSearch.getElementById("J_goodsList");
				LOGGER.debug(JsonUtil.toJson(content));
				Element li = content.getElementsByTag("li").get(0);
				LOGGER.debug(JsonUtil.toJson(li));
			}
		}

		HttpServerResponse response = event.response();
		response.putHeader("content-type", "text/plain");
		response.end("null");

	}

}
