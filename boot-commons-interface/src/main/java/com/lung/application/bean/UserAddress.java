package com.lung.application.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户地址
 * @author lfy
 *
 */
@Data
public class UserAddress implements Serializable {
	
	private Integer id;

	/**
	 * 用户地址
	 */
    private String userAddress;
	/**
	 * 用户id
	 */
    private String userId;
	/**
	 * 收货人
	 */
    private String consignee;
	/**
	 * 电话号码
	 */
    private String phoneNum;
	/**
	 * 是否为默认地址    Y-是     N-否
	 */
    private String isDefault;

	public UserAddress() {
	}

	public UserAddress(Integer id, String userAddress, String userId, String consignee, String phoneNum, String isDefault) {
		this.id = id;
		this.userAddress = userAddress;
		this.userId = userId;
		this.consignee = consignee;
		this.phoneNum = phoneNum;
		this.isDefault = isDefault;
	}
}
