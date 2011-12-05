package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TM_USERINFO database table.
 * 
 */
@Entity
@Table(name="TM_USERINFO")
public class TmUserinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CARD_NO")
	private String cardNo;

	@Column(name="ROLE_ID")
	private String roleId;

	@Column(name="USER_ID")
	private String userId;

	@Column(name="USER_PASSWORD")
	private String userPassword;
	
	@Column(name="USER_NAME")
	private String userName;

    public TmUserinfo() {
    }

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return this.userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}