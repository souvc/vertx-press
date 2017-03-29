package io.vertPress.manage.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

import io.vertPress.manage.dto.UserDTO;
import io.vertPress.manage.init.DatabaseUtil;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * @ClassName: AuthUtil
 * @Description: TODO 授权工具
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月29日 下午2:10:08
 * 
 */
public final class AuthUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthUtil.class);

	private final static String DEFAULT_AUTHENTICATE_QUERY = "select user_pass as password, null as password_salt from wp_users where user_login = 'USER_LOGIN'";

	public static void authorise(JsonObject authInfo, Handler<AsyncResult<UserDTO>> resultHandler) {
		String userName = authInfo.getString("username");
		if (userName == null) {
			resultHandler.handle(Future.failedFuture("authInfo must contain username in 'username' field"));
			return;
		}
		String password = authInfo.getString("password");
		if (password == null) {
			resultHandler.handle(Future.failedFuture("authInfo must contain password in 'password' field"));
			return;
		}
		try {
			executeQuery(DEFAULT_AUTHENTICATE_QUERY, new JsonArray().add(userName), resultHandler, rs -> {
				try {
					if (rs.next()) {
						String hashedStoredPwd = rs.getString("password");
						if (hashedStoredPwd.equals(password)) {
							resultHandler.handle(Future.succeededFuture(new UserDTO(userName)));
						} else {
							resultHandler.handle(Future.failedFuture("Invalid username/password"));
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
					resultHandler.handle(Future.failedFuture("exception fail"));
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
	}

	protected static <T> void executeQuery(String query, JsonArray params, Handler<AsyncResult<T>> resultHandler,
			Consumer<ResultSet> resultSetConsumer) throws Exception {
		final Connection conn = DatabaseUtil.getConn();
		final String sql = DEFAULT_AUTHENTICATE_QUERY.replace("USER_LOGIN", params.getString(0));
		LOGGER.info("sql:" + sql);
		ResultSet resultSet = conn.createStatement().executeQuery(sql);
		resultSetConsumer.accept(resultSet);
	}

}
