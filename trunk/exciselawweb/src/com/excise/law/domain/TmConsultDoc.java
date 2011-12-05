package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TM_CONSULT_DOC database table.
 * 
 */
@Entity
@Table(name="TM_CONSULT_DOC")
public class TmConsultDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONSULT_DOC_SEQ", sequenceName="CONSULT_DOC_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONSULT_DOC_SEQ")
	@Column(name="DOC_ID")
	private long docId;

	private String agenda;

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

	private BigDecimal completed = BigDecimal.valueOf(0);

	@Column(name="CONSULT_DETAIL")
	private String consultDetail;

	@Column(name="CONSULT_ORG")
	private String consultOrg;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;
    
	@Column(name="DOC_ATTACHMENT")
	private String docAttachment;
    
    @Temporal( TemporalType.DATE)
	@Column(name="DOC_DATE")
	private Date docDate;

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

	private String inform;

	@Column(name="LOCK_STATUS")
	private BigDecimal lockStatus = BigDecimal.valueOf(0);

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

	private String status = "A";

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TmConsultDoc() {
    }

	public long getDocId() {
		return this.docId;
	}

	public void setDocId(long docId) {
		this.docId = docId;
	}

	public String getDocAttachment() {
		return docAttachment;
	}

	public void setDocAttachment(String docAttachment) {
		this.docAttachment = docAttachment;
	}

	public String getAgenda() {
		return this.agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
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

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDocDate() {
		return this.docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
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

	public String getInform() {
		return this.inform;
	}

	public void setInform(String inform) {
		this.inform = inform;
	}

	public BigDecimal getLockStatus() {
		return this.lockStatus;
	}

	public void setLockStatus(BigDecimal lockStatus) {
		this.lockStatus = lockStatus;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}