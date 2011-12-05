package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TM_STATUE database table.
 * 
 */
@Entity
@Table(name="TM_STATUE")
public class TmStatue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATUE_SEQ", sequenceName="STATUE_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATUE_SEQ")
	@Column(name="STATUE_ID")
	private long statueId;

	@Column(name="STATUE_NAME")
	private String statueName;

	@Column(name="STATUE_ORDER")
	private BigDecimal statueOrder;

	@Column(name="STATUE_STATUS")
	private String statueStatus;

	@Column(name="STATUE_TYPE")
	private String statueType;
	
	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TmStatue() {
    }

	public long getStatueId() {
		return this.statueId;
	}

	public void setStatueId(long statueId) {
		this.statueId = statueId;
	}

	public String getStatueName() {
		return this.statueName;
	}

	public void setStatueName(String statueName) {
		this.statueName = statueName;
	}

	public BigDecimal getStatueOrder() {
		return this.statueOrder;
	}

	public void setStatueOrder(BigDecimal statueOrder) {
		this.statueOrder = statueOrder;
	}

	public String getStatueStatus() {
		return this.statueStatus;
	}

	public void setStatueStatus(String statueStatus) {
		this.statueStatus = statueStatus;
	}

	public String getStatueType() {
		return this.statueType;
	}

	public void setStatueType(String statueType) {
		this.statueType = statueType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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