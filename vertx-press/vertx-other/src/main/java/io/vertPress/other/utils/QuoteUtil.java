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
			result = i.text();
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			LOGGER.error(fullStackTrace);
		}
		return result;
	}
	
	public final static String getNbdeli(String url) {
		String result = null;
		try {
			Document jdSearch = Jsoup.connect(url).method(Connection.Method.GET).followRedirects(false).timeout(100000)
					.get();
			Element content = jdSearch.getElementById("search_re_new");
			Element price = content.getElementsByClass("recommen_goods_li").get(0);
			Element span = price.getElementsByClass("recommen_goods_sku").get(0);
			
			// TODO 需要额外
			
			result = span.text();
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			LOGGER.error(fullStackTrace);
		}
		return result;
	}
	
	public final static String getTmall(String url) {
		String result = null;
		try {
			Document jdSearch = Jsoup.connect(url).method(Connection.Method.GET).followRedirects(false).timeout(100000)
					.get();
			System.out.println(jdSearch);
			Element content = jdSearch.getElementById("J_ItemList");
			System.out.println(content);
			Element price = content.getElementsByClass("product-iWrap").get(0);
			Element span = price.getElementsByTag("em").get(0);
			// TODO 需要额外
			result = span.text();
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			LOGGER.error(fullStackTrace);
		}
		return result;
	}
	
	
	public final static String getStaples(String url) {
		String result = null;
		try {
			Document jdSearch = Jsoup.connect(url).method(Connection.Method.GET).followRedirects(false).timeout(100000)
					.get();
			Element content = jdSearch.getElementById("productList_search_list");
			Element price = content.getElementsByClass("cg_pro").get(0);
			Element span = price.getElementsByClass("pro_price").get(0);
			Element strong = span.getElementsByTag("strong").get(0);
			result = strong.text();
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			LOGGER.error(fullStackTrace);
		}
		return result;
	}
	
	
	
	public final static String getZjmiMall(String url) {
		String result = null;
		try {
			Document jdSearch = Jsoup.connect(url).method(Connection.Method.POST).followRedirects(false).timeout(100000)
					.get();
			//System.out.println(jdSearch);
			Element content = jdSearch.getElementsByClass("Product_b").get(0);
			System.out.println(content);
			Element price = content.getElementsByTag("li").get(0);
			//System.out.println(price);
			Element span = price.getElementsByClass("pro_price").get(0);
			//System.out.println(price);
			Element strong = span.getElementsByTag("strong").get(0);
			result = strong.text();
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			LOGGER.error(fullStackTrace);
		}
		return result;
	}
}
