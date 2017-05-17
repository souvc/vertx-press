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
	
	static Handler<RoutingContext> getPrice() {
		return new QuoteHandlerImpl();
	}
}
