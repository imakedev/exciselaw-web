package com.exciselaw.law.form;

import java.io.Serializable;

import com.exciselaw.law.domain.MsMatrgroup;

public class MsMatrgroupForm extends CommonForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  Long statuteId;
	private Long submatrId;
	private MsMatrgroup msMatrgroup; 
	
	public MsMatrgroupForm(){
		msMatrgroup =new MsMatrgroup();
	}
	public Long getSubmatrId() {
		return submatrId;
	}
	public void setSubmatrId(Long submatrId) {
		this.submatrId = submatrId;
	}
	public Long getStatuteId() {
		return statuteId;
	}
	public void setStatuteId(Long statuteId) {
		this.statuteId = statuteId;
	}
	public MsMatrgroup getMsMatrgroup() {
		return msMatrgroup;
	}
	public void setMsMatrgroup(MsMatrgroup msMatrgroup) {
		this.msMatrgroup = msMatrgroup;
	}
	/*public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getIsSusses() {
		return isSusses;
	}
	public void setIsSusses(String isSusses) {
		this.isSusses = isSusses;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	*/

}
