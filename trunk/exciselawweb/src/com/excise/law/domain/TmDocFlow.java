package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TM_DOC_FLOW database table.
 * 
 */
@Entity
@Table(name="TM_DOC_FLOW")
public class TmDocFlow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FLOW_ID")
	private long flowId;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="FLOW_DESC")
	private String flowDesc;

	@Column(name="FLOW_ORDER")
	private BigDecimal flowOrder;

	private String status;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    public TmDocFlow() {
    }

	public long getFlowId() {
		return this.flowId;
	}

	public void setFlowId(long flowId) {
		this.flowId = flowId;
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

	public String getFlowDesc() {
		return this.flowDesc;
	}

	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}

	public BigDecimal getFlowOrder() {
		return this.flowOrder;
	}

	public void setFlowOrder(BigDecimal flowOrder) {
		this.flowOrder = flowOrder;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}