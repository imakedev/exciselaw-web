package com.exciselaw.law.domain;

import java.io.Serializable;

public class TsRelDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long trId;
 
	private String trKey;
 
	private String trTableName;
	
	private String trTitle;

	//
	private String rmId;
	private String rmName;
	public Long getTrId() {
		return trId;
	}

	public void setTrId(Long trId) {
		this.trId = trId;
	}

	public String getTrKey() {
		return trKey;
	}

	public void setTrKey(String trKey) {
		this.trKey = trKey;
	}

	public String getTrTableName() {
		return trTableName;
	}

	public void setTrTableName(String trTableName) {
		this.trTableName = trTableName;
	}

	public String getTrTitle() {
		return trTitle;
	}

	public void setTrTitle(String trTitle) {
		this.trTitle = trTitle;
	}

	public String getRmId() {
		return rmId;
	}

	public void setRmId(String rmId) {
		this.rmId = rmId;
	}

	public String getRmName() {
		return rmName;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

}
