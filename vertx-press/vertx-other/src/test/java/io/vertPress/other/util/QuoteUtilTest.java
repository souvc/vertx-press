package io.vertPress.other.util;

import io.vertPress.other.utils.QuoteUtil;

public class QuoteUtilTest {

	
	public static void main(String[] args) {
		
		// GOOD
		// System.out.println(QuoteUtil.getJDPrice("http://search.jd.com/Search?keyword=惠普 彩色激光打印机 laserjet cp1025&enc=utf-8&wq=惠普 彩色激光打印机 laserjet cp1025&pvid=aca0d204783c47b2948fda8c42ff241f"));
		System.out.println(QuoteUtil.getStaples("http://www.staples.cn/word/惠普 彩色激光打印机 laserjet cp1025?searchFlag=0"));
		
		// TODO error
		//System.out.println(QuoteUtil.getNbdeli("http://b2b.nbdeli.com/Goods/Search.aspx?SortField=SaleQtyForSort&IsSortASC=0&KeyWord=%u60E0%u666E%20%u9ED1%u767D%u6FC0%u5149%u6253%u5370%u673A%201020plus"));
		//System.out.println(QuoteUtil.getTmall("https://list.tmall.com/search_product.htm?q=惠普 彩色激光打印机 laserjet cp1025&type=p"));
		
//		System.out.println(QuoteUtil.getZjmiMall("https://www.zjmi-mall.com/gd/search/index.do?goods_name=%E6%83%A0%E6%99%AE"));
	}
}
