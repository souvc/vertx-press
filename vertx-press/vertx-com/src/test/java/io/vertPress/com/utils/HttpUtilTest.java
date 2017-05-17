package io.vertPress.com.utils;

public class HttpUtilTest {

	public static void main(String[] args) {
		System.out.println(HttpUtil.httpRequest("http://search.jd.com/Search?keyword=惠普 彩色激光打印机 laserjet cp1025&enc=utf-8&wq=惠普 彩色激光打印机 laserjet cp1025&pvid=aca0d204783c47b2948fda8c42ff241f", "GET", null));
	}
	
	

}
