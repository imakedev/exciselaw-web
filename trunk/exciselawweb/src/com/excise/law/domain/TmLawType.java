package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TM_LAW_TYPE database table.
 * 
 */
@Entity
@Table(name="TM_LAW_TYPE")
public class TmLawType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LAW_TYPE_SEQ", sequenceName="LAW_TYPE_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LAW_TYPE_SEQ")
	@Column(name="LAW_TYPE_ID")
	private long lawTypeId;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="LAW_TYPE_NAME")
	private String lawTypeName;

	@Column(name="LAW_TYPE_ORDER")
	private BigDecimal lawTypeOrder;

	@Column(name="LAW_TYPE_PRIORITY")
	private BigDecimal lawTypePriority;

	@Column(name="LAW_TYPE_TYPE")
	private String lawTypeType;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TmLawType() {
    }

	public long getLawTypeId() {
		return this.lawTypeId;
	}

	public void setLawTypeId(long lawTypeId) {
		this.lawTypeId = lawTypeId;
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

	public String getLawTypeName() {
		return this.lawTypeName;
	}

	public void setLawTypeName(String lawTypeName) {
		this.lawTypeName = lawTypeName;
	}

	public BigDecimal getLawTypeOrder() {
		return this.lawTypeOrder;
	}

	public void setLawTypeOrder(BigDecimal lawTypeOrder) {
		this.lawTypeOrder = lawTypeOrder;
	}

	public BigDecimal getLawTypePriority() {
		return this.lawTypePriority;
	}

	public void setLawTypePriority(BigDecimal lawTypePriority) {
		this.lawTypePriority = lawTypePriority;
	}

	public String getLawTypeType() {
		return this.lawTypeType;
	}

	public void setLawTypeType(String lawTypeType) {
		this.lawTypeType = lawTypeType;
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