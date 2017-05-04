package io.vertPress.other.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * @ClassName: QuoteUtil
 * @Description: 爬取价格工具类
 * @author FoamValue foamvalue@live.cn
 * @date 2017年5月4日 下午4:48:33
 * 
 */
public final class QuoteUtil {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(QuoteUtil.class);

	/**
	 * @Title: getJDPrice
	 * @Description: 获取京东价格
	 * @param @param url
	 * @param @return
	 * @return String
	 * @throws
	 */
	public final static String getJDPrice(String url) {
		String result = null;
		try {
			Document jdSearch = Jsoup.connect(url).method(Connection.Method.GET).followRedirects(false).timeout(100000)
					.get();

			Element content = jdSearch.getElementById("J_goodsList");
			Element li = content.getElementsByTag("li").get(0);
			Element price = li.getElementsByClass("p-price").get(0);
			Element i = price.getElementsByTag("i").get(0);
			result = i.toString();
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			LOGGER.error(fullStackTrace);
		}
		return result;
	}
}
