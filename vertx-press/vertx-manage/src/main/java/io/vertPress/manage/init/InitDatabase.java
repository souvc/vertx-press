package io.vertPress.manage.init;

import io.vertPress.manage.utils.ResultUtil;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.RoutingContext;

/**
 * @ClassName: InitDataBase
 * @Description: TODO 初始化数据库
 * @author FoamValue foamvalue@live.cn
 * @date 2017年2月26日 下午4:20:07
 * 
 */
public class InitDatabase {

	public void handleGetProduct(RoutingContext routingContext) {
		HttpServerResponse response = routingContext.response();
		SQLConnection conn = routingContext.get("conn");
		conn.execute(
				"CREATE TABLE IF NOT EXISTS wp_users (id bigint(20) NOT NULL AUTO_INCREMENT,user_login VARCHAR(60) DEFAULT NULL,user_pass VARCHAR(64) DEFAULT NULL,user_nicename VARCHAR(50) DEFAULT NULL,user_email VARCHAR(100) DEFAULT NULL,user_url VARCHAR(100) DEFAULT NULL,user_registered DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, user_activation_key VARCHAR(60) DEFAULT NULL,user_status int(1) DEFAULT 0,PRIMARY KEY (id)) ENGINE=InnoDB",
				ddl -> {
					if (ddl.failed()) {
						throw new RuntimeException(ddl.cause());
					}
				});
		ResultUtil.sendJSON("Initialize the database successfully!", response);

	}

}
