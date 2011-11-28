package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TM_JUDGEMENT_TYPE database table.
 * 
 */
@Entity
@Table(name="TM_JUDGEMENT_TYPE")
public class TmJudgementType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="JUDGEMENT_TYPE_SEQ", sequenceName="JUDGEMENT_TYPE_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JUDGEMENT_TYPE_SEQ")
	@Column(name="JUDGEMENT_TYPE_ID")
	private long judgementTypeId;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="GROUP_DESCRIPTION")
	private String groupDescription;

	@Column(name="JUDGEMENT_GROUP")
	private BigDecimal judgementGroup;

	@Column(name="JUDGEMENT_SUBGROUP")
	private BigDecimal judgementSubgroup;

	@Column(name="JUDGEMENT_TYPE_DESCRIPTION")
	private String judgementTypeDescription;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TmJudgementType() {
    }

	public long getJudgementTypeId() {
		return this.judgementTypeId;
	}

	public void setJudgementTypeId(long judgementTypeId) {
		this.judgementTypeId = judgementTypeId;
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

	public String getGroupDescription() {
		return this.groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public BigDecimal getJudgementGroup() {
		return this.judgementGroup;
	}

	public void setJudgementGroup(BigDecimal judgementGroup) {
		this.judgementGroup = judgementGroup;
	}

	public BigDecimal getJudgementSubgroup() {
		return this.judgementSubgroup;
	}

	public void setJudgementSubgroup(BigDecimal judgementSubgroup) {
		this.judgementSubgroup = judgementSubgroup;
	}

	public String getJudgementTypeDescription() {
		return this.judgementTypeDescription;
	}

	public void setJudgementTypeDescription(String judgementTypeDescription) {
		this.judgementTypeDescription = judgementTypeDescription;
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