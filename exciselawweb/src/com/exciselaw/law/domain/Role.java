package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ROLE database table.
 * 
 */
@Entity
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_ID")
	private String roleId;

	@Column(name="ROLE_NAME")
	private String roleName;

	@Column(name="USER_ID")
	private String userId;

    public Role() {
    }

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}