package io.vertPress.com.utils;

public class HttpUtilTest {

	public static void main(String[] args) {
		System.out.println(HttpUtil.httpRequest("http://www.baidu.com", "GET", null));
	}

}
