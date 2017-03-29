package io.vertPress.manage.handle.impl;

import io.vertPress.manage.dto.ConstantDTO;
import io.vertPress.manage.dto.UserDTO;
import io.vertPress.manage.handle.LoginHandler;
import io.vertPress.manage.utils.AuthUtil;
import io.vertPress.manage.utils.ResultUtil;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;

/**
 * @ClassName: LoginHandlerImpl
 * @Description: TODO 登陆接口实现类
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月29日 下午8:07:46
 * 
 */
public class LoginHandlerImpl implements LoginHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger(LoginHandlerImpl.class);

	private String userNameParam;
	private String passwordParam;
	private String returnURLParam;
	private String directLoggedInOKURL;

	@Override
	public LoginHandler setUserNameParam(String userNameParam) {
		this.userNameParam = userNameParam;
		return this;
	}

	@Override
	public LoginHandler setPasswordParam(String passwordParam) {
		this.passwordParam = passwordParam;
		return this;
	}

	@Override
	public LoginHandler setReturnURLParam(String returnURLParam) {
		this.returnURLParam = returnURLParam;
		return this;
	}

	@Override
	public LoginHandler setDirectLoggedInOKURL(String directLoggedInOKURL) {
		this.directLoggedInOKURL = directLoggedInOKURL;
		return this;
	}

	public LoginHandlerImpl(String usernameParam, String passwordParam, String returnURLParam,
			String directLoggedInOKURL) {
		this.userNameParam = usernameParam;
		this.passwordParam = passwordParam;
		this.returnURLParam = returnURLParam;
		this.directLoggedInOKURL = directLoggedInOKURL;
	}

	@Override
	public void handle(RoutingContext context) {
		HttpServerRequest req = context.request();
		if (req.method() != HttpMethod.POST) {
			context.fail(405); // Must be a POST
		} else {
			if (!req.isExpectMultipart()) {
				throw new IllegalStateException("Form body not parsed - do you forget to include a BodyHandler?");
			}
			MultiMap params = req.formAttributes();
			String username = params.get(userNameParam);
			String password = params.get(passwordParam);
			if (username == null || password == null) {
				LOGGER.warn("No username or password provided in form - did you forget to include a BodyHandler?");
				context.fail(400);
			} else {
				Session session = context.session();
				JsonObject authInfo = new JsonObject().put("username", username).put("password", password);
				
				AuthUtil.authorise(authInfo, res -> {
					if (res.succeeded()) {
						UserDTO user = res.result();
						LOGGER.info("userDTO: " + user);
						if (session != null) {
							session.put(ConstantDTO.DEFAULT_USER_SESSION_KEY, user);
							String returnURL = session.remove(returnURLParam);
							if (returnURL != null) {
								ResultUtil.redirectURL(context.response(), returnURL);
								return;
							}
						}
						if (directLoggedInOKURL != null) {
							ResultUtil.redirectURL(context.response(), directLoggedInOKURL);
						} else {
							req.response().end(DEFAULT_DIRECT_LOGGED_IN_OK_PAGE);
						}
					} else {
						context.fail(403); // Failed login
					}
				});
			}
		}
	}

	private static final String DEFAULT_DIRECT_LOGGED_IN_OK_PAGE = ""
			+ "<html><body><h1>Login successful</h1></body></html>";
}
