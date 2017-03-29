package io.vertPress.manage.dto;

import java.io.Serializable;

/**
 * @ClassName: ConstantDTO
 * @Description: TODO 常量
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月29日 下午8:05:02
 * 
 */
public final class ConstantDTO implements Serializable {

	private static final long serialVersionUID = 6426922143464108667L;

	/**
	 * @Fields DEFAULT_USER_SESSION_KEY :  用户登陆状态
	 */
	public final static String DEFAULT_USER_SESSION_KEY = "_vertx_user_key";
	
	
	/**
	 * @Fields DEFAULT_RETURN_URL_PARAM : 登陆成功后回调请求
	 */
	public final static String DEFAULT_RETURN_URL_PARAM = "return_url";
}
