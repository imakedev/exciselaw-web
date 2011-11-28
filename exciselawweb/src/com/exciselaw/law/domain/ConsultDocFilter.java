package com.exciselaw.law.domain;

import java.io.Serializable;

public class ConsultDocFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String agenda;

	private String bureauRecDateStr;

	private String bureauRecNo;

	private String consultOrg;

	private String docDateStr;

	private String docNo;

	private String docSendDateStr;

	private String docSendNo;

	private String docType;
	
	private String flowDesc;

	private String officeRecDateStr;
	
	private String officeRecNo;

	private String sectionRecDateStr;
	
	private String sectionRecNo;
	
	private String status;

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public String getBureauRecDateStr() {
		return bureauRecDateStr;
	}

	public void setBureauRecDateStr(String bureauRecDateStr) {
		this.bureauRecDateStr = bureauRecDateStr;
	}

	public String getBureauRecNo() {
		return bureauRecNo;
	}

	public void setBureauRecNo(String bureauRecNo) {
		this.bureauRecNo = bureauRecNo;
	}

	public String getConsultOrg() {
		return consultOrg;
	}

	public void setConsultOrg(String consultOrg) {
		this.consultOrg = consultOrg;
	}

	public String getDocDateStr() {
		return docDateStr;
	}

	public void setDocDateStr(String docDateStr) {
		this.docDateStr = docDateStr;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDocSendDateStr() {
		return docSendDateStr;
	}

	public void setDocSendDateStr(String docSendDateStr) {
		this.docSendDateStr = docSendDateStr;
	}

	public String getDocSendNo() {
		return docSendNo;
	}

	public void setDocSendNo(String docSendNo) {
		this.docSendNo = docSendNo;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getFlowDesc() {
		return flowDesc;
	}

	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}

	public String getOfficeRecDateStr() {
		return officeRecDateStr;
	}

	public void setOfficeRecDateStr(String officeRecDateStr) {
		this.officeRecDateStr = officeRecDateStr;
	}

	public String getOfficeRecNo() {
		return officeRecNo;
	}

	public void setOfficeRecNo(String officeRecNo) {
		this.officeRecNo = officeRecNo;
	}

	public String getSectionRecDateStr() {
		return sectionRecDateStr;
	}

	public void setSectionRecDateStr(String sectionRecDateStr) {
		this.sectionRecDateStr = sectionRecDateStr;
	}

	public String getSectionRecNo() {
		return sectionRecNo;
	}

	public void setSectionRecNo(String sectionRecNo) {
		this.sectionRecNo = sectionRecNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}