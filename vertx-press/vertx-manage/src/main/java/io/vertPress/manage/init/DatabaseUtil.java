package io.vertPress.manage.init;

import java.sql.Connection;
import java.sql.DriverManager;

import io.vertx.core.json.JsonObject;

/**
 * @ClassName: DatabaseUtil
 * @Description: TODO 数据库工具类
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月23日 下午8:34:15
 * 
 */
public final class DatabaseUtil {

	private static ResourceLoader LOADER = ResourceLoader.getInstance();

	public static JsonObject getJsonObject() throws Exception {
		final JsonObject result = new JsonObject();

		result.put("host", LOADER.getString("vertx.db.host"));
		result.put("port", LOADER.getInt("vertx.db.port"));
		result.put("maxPoolSize", LOADER.getInt("vertx.db.max.pool.size"));
		result.put("username", LOADER.getString("vertx.db.user"));
		result.put("password", LOADER.getString("vertx.db.pwd"));
		result.put("database", LOADER.getString("vertx.db.database"));
		result.put("charset", LOADER.getString("vertx.db.charset"));
		result.put("queryTimeout", LOADER.getInt("vertx.db.timeout"));
		return result;
	}

	public static Connection getConn() throws Exception {
		final Connection conn = DriverManager.getConnection(LOADER.getString("vertx.db.url"), LOADER.getString("vertx.db.user"), LOADER.getString("vertx.db.pwd"));
		return conn;
	}
}
