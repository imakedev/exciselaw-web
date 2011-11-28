package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the TS_LOGIN_LOG database table.
 * 
 */
@Entity
@Table(name="TS_LOGIN_LOG")
public class TsLoginLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOGIN_LOG_SEQ", sequenceName="LOGIN_LOG_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOGIN_LOG_SEQ")
	@Column(name="LOG_ID")
	private long logId;

	@Column(name="LOGIN_DATETIME")
	private Timestamp loginDatetime;

	@Column(name="LOGOUT_DATETIME")
	private Timestamp logoutDatetime;

	@Column(name="USER_ID")
	private String userId;

    public TsLoginLog() {
    }

	public long getLogId() {
		return this.logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public Timestamp getLoginDatetime() {
		return this.loginDatetime;
	}

	public void setLoginDatetime(Timestamp loginDatetime) {
		this.loginDatetime = loginDatetime;
	}

	public Timestamp getLogoutDatetime() {
		return this.logoutDatetime;
	}

	public void setLogoutDatetime(Timestamp logoutDatetime) {
		this.logoutDatetime = logoutDatetime;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}