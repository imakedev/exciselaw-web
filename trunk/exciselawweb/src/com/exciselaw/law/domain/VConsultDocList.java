package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_CONSULT_DOC_LIST database table.
 * 
 */
@Entity
@Table(name="V_CONSULT_DOC_LIST")
public class VConsultDocList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STATE_ID")
	private long stateId;
	
	private String agenda;

	@Column(name="ASSIGN_LAWYER")
	private String assignLawyer;

	@Column(name="ASSIGN_PROF")
	private String assignProf;

    @Temporal( TemporalType.DATE)
	@Column(name="BUREAU_REC_DATE")
	private Date bureauRecDate;

	@Column(name="BUREAU_REC_NO")
	private String bureauRecNo;

    @Temporal( TemporalType.DATE)
	@Column(name="BUREAU_SEND_DATE")
	private Date bureauSendDate;

	@Column(name="BUREAU_SEND_NO")
	private String bureauSendNo;

	private BigDecimal completed;

	@Column(name="CONSULT_DETAIL")
	private String consultDetail;

	@Column(name="CONSULT_ORG")
	private String consultOrg;

	@Column(name="CONSULT_ORG_NAME")
	private String consultOrgName;

	@Column(name="DOC_ATTACHMENT")
	private String docAttachment;
	
	@Column(name="DOC_COMMENT")
	private String docComment;

    @Temporal( TemporalType.DATE)
	@Column(name="DOC_DATE")
	private Date docDate;

	@Column(name="DOC_ID")
	private BigDecimal docId;

	@Column(name="DOC_NO")
	private String docNo;

    @Temporal( TemporalType.DATE)
	@Column(name="DOC_REF_DATE")
	private Date docRefDate;

	@Column(name="DOC_REF_NO")
	private String docRefNo;

    @Temporal( TemporalType.DATE)
	@Column(name="DOC_SEND_DATE")
	private Date docSendDate;

	@Column(name="DOC_SEND_NO")
	private String docSendNo;

	@Column(name="DOC_TYPE")
	private String docType;

	@Column(name="DOCCREATED_BY")
	private String doccreatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="DOCCREATED_DATE")
	private Date doccreatedDate;

	@Column(name="FLOW_DESC")
	private String flowDesc;

	@Column(name="FLOW_ID")
	private BigDecimal flowId;

	private String inform;

    @Temporal( TemporalType.DATE)
	@Column(name="OFFICE_REC_DATE")
	private Date officeRecDate;

	@Column(name="OFFICE_REC_NO")
	private String officeRecNo;

	@Column(name="REFER_TO")
	private String referTo;

    @Temporal( TemporalType.DATE)
	@Column(name="SECTION_REC_DATE")
	private Date sectionRecDate;

	@Column(name="SECTION_REC_NO")
	private String sectionRecNo;

    @Temporal( TemporalType.DATE)
	@Column(name="SECTION_SEND_DATE")
	private Date sectionSendDate;

	@Column(name="SECTION_SEND_NO")
	private String sectionSendNo;

	@Column(name="SECURITY_LEVEL")
	private BigDecimal securityLevel;

	@Column(name="STATECREATED_BY")
	private String statecreatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="STATECREATED_DATE")
	private Date statecreatedDate;

	private String status;

	@Column(name="LOCK_STATUS")
	private BigDecimal lockStatus;

	@Column(name="STATUS_DESC")
	private String statusDesc;

	@Column(name="STATUS_ID")
	private BigDecimal statusId;

	@Column(name="TO_SECTION")
	private BigDecimal toSection;

    public VConsultDocList() {
    }

	public String getAgenda() {
		return this.agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public String getAssignLawyer() {
		return this.assignLawyer!=null&&!"".equals(this.assignLawyer)?this.assignLawyer.trim():"";
	}

	public void setAssignLawyer(String assignLawyer) {
		this.assignLawyer = assignLawyer!=null&&!"".equals(assignLawyer)?assignLawyer.trim():"";
	}

	public String getAssignProf() {
		return this.assignProf!=null&&!"".equals(this.assignProf)?this.assignProf.trim():"";
	}

	public void setAssignProf(String assignProf) {
		this.assignProf = assignProf!=null&&!"".equals(assignProf)?assignProf.trim():"";;
	}

	public Date getBureauRecDate() {
		return this.bureauRecDate;
	}

	public void setBureauRecDate(Date bureauRecDate) {
		this.bureauRecDate = bureauRecDate;
	}

	public String getBureauRecNo() {
		return this.bureauRecNo;
	}

	public void setBureauRecNo(String bureauRecNo) {
		this.bureauRecNo = bureauRecNo;
	}

	public Date getBureauSendDate() {
		return this.bureauSendDate;
	}

	public void setBureauSendDate(Date bureauSendDate) {
		this.bureauSendDate = bureauSendDate;
	}

	public String getBureauSendNo() {
		return this.bureauSendNo;
	}

	public void setBureauSendNo(String bureauSendNo) {
		this.bureauSendNo = bureauSendNo;
	}

	public BigDecimal getCompleted() {
		return this.completed;
	}

	public void setCompleted(BigDecimal completed) {
		this.completed = completed;
	}

	public String getConsultDetail() {
		return this.consultDetail;
	}

	public void setConsultDetail(String consultDetail) {
		this.consultDetail = consultDetail;
	}

	public String getConsultOrg() {
		return this.consultOrg;
	}

	public void setConsultOrg(String consultOrg) {
		this.consultOrg = consultOrg;
	}

	public String getConsultOrgName() {
		return this.consultOrgName;
	}

	public void setConsultOrgName(String consultOrgName) {
		this.consultOrgName = consultOrgName;
	}

	public String getDocAttachment() {
		return docAttachment;
	}

	public void setDocAttachment(String docAttachment) {
		this.docAttachment = docAttachment;
	}

	public String getDocComment() {
		return this.docComment;
	}

	public void setDocComment(String docComment) {
		this.docComment = docComment;
	}

	public Date getDocDate() {
		return this.docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public BigDecimal getDocId() {
		return this.docId;
	}

	public void setDocId(BigDecimal docId) {
		this.docId = docId;
	}

	public String getDocNo() {
		return this.docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public Date getDocRefDate() {
		return this.docRefDate;
	}

	public void setDocRefDate(Date docRefDate) {
		this.docRefDate = docRefDate;
	}

	public String getDocRefNo() {
		return this.docRefNo;
	}

	public void setDocRefNo(String docRefNo) {
		this.docRefNo = docRefNo;
	}

	public Date getDocSendDate() {
		return this.docSendDate;
	}

	public void setDocSendDate(Date docSendDate) {
		this.docSendDate = docSendDate;
	}

	public String getDocSendNo() {
		return this.docSendNo;
	}

	public void setDocSendNo(String docSendNo) {
		this.docSendNo = docSendNo;
	}

	public String getDocType() {
		return this.docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDoccreatedBy() {
		return this.doccreatedBy;
	}

	public void setDoccreatedBy(String doccreatedBy) {
		this.doccreatedBy = doccreatedBy;
	}

	public Date getDoccreatedDate() {
		return this.doccreatedDate;
	}

	public void setDoccreatedDate(Date doccreatedDate) {
		this.doccreatedDate = doccreatedDate;
	}

	public String getFlowDesc() {
		return this.flowDesc;
	}

	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}

	public BigDecimal getFlowId() {
		return this.flowId;
	}

	public void setFlowId(BigDecimal flowId) {
		this.flowId = flowId;
	}

	public String getInform() {
		return this.inform;
	}

	public void setInform(String inform) {
		this.inform = inform;
	}

	public Date getOfficeRecDate() {
		return this.officeRecDate;
	}

	public void setOfficeRecDate(Date officeRecDate) {
		this.officeRecDate = officeRecDate;
	}

	public String getOfficeRecNo() {
		return this.officeRecNo;
	}

	public void setOfficeRecNo(String officeRecNo) {
		this.officeRecNo = officeRecNo;
	}

	public String getReferTo() {
		return this.referTo;
	}

	public void setReferTo(String referTo) {
		this.referTo = referTo;
	}

	public Date getSectionRecDate() {
		return this.sectionRecDate;
	}

	public void setSectionRecDate(Date sectionRecDate) {
		this.sectionRecDate = sectionRecDate;
	}

	public String getSectionRecNo() {
		return this.sectionRecNo;
	}

	public void setSectionRecNo(String sectionRecNo) {
		this.sectionRecNo = sectionRecNo;
	}

	public Date getSectionSendDate() {
		return this.sectionSendDate;
	}

	public void setSectionSendDate(Date sectionSendDate) {
		this.sectionSendDate = sectionSendDate;
	}

	public String getSectionSendNo() {
		return this.sectionSendNo;
	}

	public void setSectionSendNo(String sectionSendNo) {
		this.sectionSendNo = sectionSendNo;
	}

	public BigDecimal getSecurityLevel() {
		return this.securityLevel;
	}

	public void setSecurityLevel(BigDecimal securityLevel) {
		this.securityLevel = securityLevel;
	}

	public long getStateId() {
		return this.stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public String getStatecreatedBy() {
		return this.statecreatedBy;
	}

	public void setStatecreatedBy(String statecreatedBy) {
		this.statecreatedBy = statecreatedBy;
	}

	public Date getStatecreatedDate() {
		return this.statecreatedDate;
	}

	public void setStatecreatedDate(Date statecreatedDate) {
		this.statecreatedDate = statecreatedDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public BigDecimal getStatusId() {
		return this.statusId;
	}

	public void setStatusId(BigDecimal statusId) {
		this.statusId = statusId;
	}

	public BigDecimal getToSection() {
		return this.toSection;
	}

	public void setToSection(BigDecimal toSection) {
		this.toSection = toSection;
	}

	public BigDecimal getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(BigDecimal lockStatus) {
		this.lockStatus = lockStatus;
	}
}