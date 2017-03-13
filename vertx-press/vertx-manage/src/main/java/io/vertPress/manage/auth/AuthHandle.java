package io.vertPress.manage.auth;

import org.apache.commons.lang3.StringUtils;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;

/**
 * @ClassName: AuthHandle
 * @Description: TODO 简单用户权限
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月13日 下午7:47:57
 * 
 */
public class AuthHandle {
	
	/**
	 * 	存储密码的步骤：
	 * 	使用 CSPRNG 生成足够长的随机盐值。
	 * 	将盐值混入密码，并使用标准的密码哈希函数进行加密，如Argon2、 bcrypt 、 scrypt 或 PBKDF2 。
	 * 	将盐值和对应的哈希值一起存入用户数据库。
	 * 	校验密码的步骤：
	 * 	从数据库检索出用户的盐值和对应的哈希值。
	 * 	将盐值混入用户输入的密码，并且使用通用的哈希函数进行加密。
	 * 	比较上一步的结果，是否和数据库存储的哈希值相同。如果它们相同，则表明密码是正确的；否则，该密码错误。
	*/

	/**
	 * @Fields ENGINE : 模版引擎
	 */
	private final static FreeMarkerTemplateEngine ENGINE = FreeMarkerTemplateEngine.create();

	public void handleGetProduct(RoutingContext routingContext) {
		Session session = routingContext.session();
		if (session != null) {
			String userNo = session.get("userNo");
			if (StringUtils.isNotBlank(userNo)) {
				routingContext.next();
			} else {
				ENGINE.render(routingContext, "templates/index.ftl", res -> {
					if (res.succeeded()) {
						routingContext.response().end(res.result());
					} else {
						routingContext.fail(res.cause());
					}
				});
			}
		} else {
			routingContext.fail(new NullPointerException("No session - did you forget to include a SessionHandler?"));
		}

	}
}
