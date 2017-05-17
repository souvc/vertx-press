package io.vertPress.other;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupTest {

	public static void main(String[] args) throws IOException {
		Document jdSearch = Jsoup
				.connect(
						"http://search.jd.com/Search?keyword=惠普 彩色激光打印机 laserjet cp1025&enc=utf-8&wq=惠普 彩色激光打印机 laserjet cp1025&pvid=aca0d204783c47b2948fda8c42ff241f")
				.method(Connection.Method.GET).followRedirects(false).timeout(100000).get();

		Element content = jdSearch.getElementById("J_goodsList");
		Element li = content.getElementsByTag("li").get(0);
		Element price = li.getElementsByClass("p-price").get(0);
		Element i = price.getElementsByTag("i").get(0);
		System.out.println(i.text());
	}
}
