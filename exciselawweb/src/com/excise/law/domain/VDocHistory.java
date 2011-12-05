package com.excise.law.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the V_DOC_HISTORY database table.
 * 
 */
@Entity
@Table(name="V_DOC_HISTORY")
public class VDocHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STATE_ID")
	private long stateId;
	
	@Column(name="DOC_ID")
	private long docId;

	@Column(name="FLOW_DESC")
	private String flowDesc;

	@Column(name="ORG_NAME")
	private String orgName;

	@Column(name="USERNAME")
	private String username;

	@Column(name="STATUS_DESC")
	private String statusDesc;
	
    public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public VDocHistory() {
    }

	public long getDocId() {
		return this.docId;
	}

	public void setDocId(long docId) {
		this.docId = docId;
	}

	public String getFlowDesc() {
		return this.flowDesc;
	}

	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}