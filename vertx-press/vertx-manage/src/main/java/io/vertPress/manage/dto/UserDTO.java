package io.vertPress.manage.dto;

import java.io.Serializable;

/**
 * @ClassName: UserDTO
 * @Description: TODO 用户对象
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月29日 下午8:07:13
 * 
 */
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -8203307196906807650L;

	private String userName;

	private String password;

	public UserDTO(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
