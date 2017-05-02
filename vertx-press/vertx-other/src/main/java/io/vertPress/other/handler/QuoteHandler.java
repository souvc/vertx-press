package io.vertPress.other.handler;

import io.vertPress.other.handler.impl.QuoteHandlerImpl;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * @ClassName: QuoteHandler
 * @Description: TODO 报价接口
 * @author FoamValue foamvalue@live.cn
 * @date 2017年5月2日 下午5:25:12
 * 
 */
public interface QuoteHandler extends Handler<RoutingContext> {
	
	/**
	 * @Fields HP_SEARCH : 惠普商城查询
	 */
	final static String HP_SEARCH = "http://b2b.nbdeli.com/Goods/Search.aspx?SortField=SaleQtyForSort&IsSortASC=0&KeyWord=PRODUCTBRAND PRODUCTNAME PRODUCTMODEL";
	
	/**
	 * @Fields STAPLES_SEARCH : 史泰博查询
	 */
	final static 	String STAPLES_SEARCH = "http://www.staples.cn/word/PRODUCTBRAND&31PRODUCTNAME&31PRODUCTMODEL?searchFlag=0";
	
	/**
	 * @Fields ZJMI_SEARCH : 浙江物产商城查询
	 */
	final static String ZJMI_SEARCH = "https://www.zjmi-mall.com/gd/search/index.do?goods_name=惠普 彩色激光打印机 laserjet cp1025";
	
	/**
	 * @Fields JD_SEARCH : 京东商城查询
	 */
	final static String JD_SEARCH = "https://search.jd.com/Search?keyword=惠普 彩色激光打印机 laserjet cp1025&enc=utf-8&wq=惠普 彩色激光打印机 laserjet cp1025&pvid=a53fb1023424473a88013861f449489a";
	
	/**
	 * @Fields TMALL_SEARCH : 天猫商城查询
	 */
	final static String TMALL_SEARCH = "https://list.tmall.com/search_product.htm?q=惠普 彩色激光打印机 laserjet cp1025&type=p&vmarket=&spm=875.7931836/B.a2227oh.d100&from=mallfp..pc_1_searchbutton";
	
	/**
	 * @Fields NBDELI_SEARCH : 得力商城查询
	 */
	final static String NBDELI_SEARCH = "http://b2b.nbdeli.com/Goods/Search.aspx?SortField=SaleQtyForSort&IsSortASC=0&KeyWord=惠普 彩色激光打印机 laserjet cp1025";
	

	static Handler<RoutingContext> getPrice() {
		return new QuoteHandlerImpl();
	}
}
