package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER_INFO database table.
 * 
 */
@Entity
@Table(name="USER_INFO")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private String userId;

	@Column(name="APPLICATION_ID")
	private String applicationId;

	@Column(name="CN_NAME")
	private String cnName;

	private String email;

	@Column(name="OFFICE_ID")
	private String officeId;

	private String password;

	@Column(name="TELEPHONE_NO")
	private String telephoneNo;

	private String title;

	@Column(name="USER_ENG_NAME")
	private String userEngName;

	@Column(name="USER_ENG_SURNAME")
	private String userEngSurname;

	@Column(name="USER_THAI_ID")
	private String userThaiId;

	@Column(name="USER_THAI_NAME")
	private String userThaiName;

	@Column(name="USER_THAI_SURNAME")
	private String userThaiSurname;

    public UserInfo() {
    }

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCnName() {
		return this.cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficeId() {
		return this.officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephoneNo() {
		return this.telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserEngName() {
		return this.userEngName;
	}

	public void setUserEngName(String userEngName) {
		this.userEngName = userEngName;
	}

	public String getUserEngSurname() {
		return this.userEngSurname;
	}

	public void setUserEngSurname(String userEngSurname) {
		this.userEngSurname = userEngSurname;
	}

	public String getUserThaiId() {
		return this.userThaiId;
	}

	public void setUserThaiId(String userThaiId) {
		this.userThaiId = userThaiId;
	}

	public String getUserThaiName() {
		return this.userThaiName;
	}

	public void setUserThaiName(String userThaiName) {
		this.userThaiName = userThaiName;
	}

	public String getUserThaiSurname() {
		return this.userThaiSurname;
	}

	public void setUserThaiSurname(String userThaiSurname) {
		this.userThaiSurname = userThaiSurname;
	}

}