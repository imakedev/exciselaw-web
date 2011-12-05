package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TS_DOC_STATE database table.
 * 
 */
@Entity
@Table(name="TS_DOC_STATE")
public class TsDocState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOC_STATE_SEQ", sequenceName="DOC_STATE_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOC_STATE_SEQ")
	@Column(name="STATE_ID")
	private long stateId;

	@Column(name="ASSIGN_LAWYER")
	private String assignLawyer;

	@Column(name="ASSIGN_PROF")
	private String assignProf;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="DOC_COMMENT")
	private String docComment;

	@Column(name="DOC_ID")
	private BigDecimal docId;

	@Column(name="FLOW_ID")
	private BigDecimal flowId;

	@Column(name="PERSON_INCHARGE")
	private String personIncharge;
	
	@Column(name="STATUS_ID")
	private BigDecimal statusId;

	@Column(name="TO_SECTION")
	private BigDecimal toSection;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsDocState() {
    }

	public long getStateId() {
		return this.stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
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
		this.assignProf = assignProf!=null&&!"".equals(assignProf)?assignProf.trim():"";
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

	public String getDocComment() {
		return this.docComment;
	}

	public void setDocComment(String docComment) {
		this.docComment = docComment;
	}

	public BigDecimal getDocId() {
		return this.docId;
	}

	public void setDocId(BigDecimal docId) {
		this.docId = docId;
	}

	public BigDecimal getFlowId() {
		return this.flowId;
	}

	public void setFlowId(BigDecimal flowId) {
		this.flowId = flowId;
	}

	public String getPersonIncharge() {
		return personIncharge!=null&&!"".equals(personIncharge)?personIncharge.trim():"";
	}

	public void setPersonIncharge(String personIncharge) {
		this.personIncharge = personIncharge!=null&&!"".equals(personIncharge)?personIncharge.trim():"";;
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