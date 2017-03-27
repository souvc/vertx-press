package io.vertPress.manage.handle.impl;

import io.vertPress.manage.dto.UserDTO;
import io.vertPress.manage.handle.LoginHandler;
import io.vertPress.manage.utils.ResultUtil;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;

public class LoginHandlerImpl implements LoginHandler {

	private static final Logger log = LoggerFactory.getLogger(LoginHandlerImpl.class);

	private static final String SESSION_USER_HOLDER_KEY = "__vertx.press.userHolder";

	private static final String DEFAULT_DIRECT_LOGGED_IN_OK_PAGE = "<html><body><h1>Login successful</h1></body></html>";

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

	public LoginHandlerImpl(String userNameParam, String passwordParam, String returnURLParam, String directLoggedInOKURL) {
		this.userNameParam = userNameParam;
		this.passwordParam = passwordParam;
		this.returnURLParam = returnURLParam;
		this.directLoggedInOKURL = directLoggedInOKURL;
	}

	@Override
	public void handle(RoutingContext event) {
		HttpServerRequest req = event.request();
		if (req.method() != HttpMethod.POST) {
			event.fail(405); // Must be a POST
		} else {
			if (!req.isExpectMultipart()) {
				throw new IllegalStateException("Form body not parsed - do you forget to include a BodyHandler?");
			}
			MultiMap params = req.formAttributes();
			String userName = params.get(userNameParam);
			String password = params.get(passwordParam);
			if (userName == null || password == null) {
				log.warn("No username or password provided in form - did you forget to include a BodyHandler?");
				event.fail(400);
			} else {
				Session session = event.session();
				session.put(SESSION_USER_HOLDER_KEY, new UserDTO(userName, password));
				if (session != null) {
					String returnURL = session.remove(returnURLParam);
					if (returnURL != null) {
						ResultUtil.redirectURL(req.response(), returnURL);
						return;
					}
				}
				if (directLoggedInOKURL != null) {
					ResultUtil.redirectURL(req.response(), directLoggedInOKURL);
				} else {
					req.response().end(DEFAULT_DIRECT_LOGGED_IN_OK_PAGE);
				}
			}
		}
	}
}
