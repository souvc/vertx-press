package io.vertPress.other.dto;

import java.io.Serializable;

/**
 * @ClassName: QuoteDTO
 * @Description: TODO JSON 对象
 * @author FoamValue foamvalue@live.cn
 * @date 2017年5月4日 下午4:42:27
 * 
 */
public class QuoteDTO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 4007317258862536870L;

	public Double jdPrice;

	public Double getJdPrice() {
		return jdPrice;
	}

	public void setJdPrice(Double jdPrice) {
		this.jdPrice = jdPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
