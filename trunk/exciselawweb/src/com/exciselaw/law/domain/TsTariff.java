package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TS_TARIFF database table.
 * 
 */
@Entity
@Table(name="TS_TARIFF")
public class TsTariff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TARIFF_SEQ", sequenceName="TARIFF_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TARIFF_SEQ")
	@Column(name="TARIFF_ID")
	private long tariffId;

	@Column(name="ARTICLE_HEADER_ID")
	private java.math.BigDecimal articleHeaderId;

	@Column(name="TARIFF_KEYWORD")
	private String tariffKeyword;

    @Lob()
	@Column(name="TARIFF_REMARK")
	private String tariffRemark;

    @Lob()
	@Column(name="TARIFF_TABLE")
	private String tariffTable;

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

    public TsTariff() {
    }

	public long getTariffId() {
		return this.tariffId;
	}

	public void setTariffId(long tariffId) {
		this.tariffId = tariffId;
	}

	public java.math.BigDecimal getArticleHeaderId() {
		return this.articleHeaderId;
	}

	public void setArticleHeaderId(java.math.BigDecimal articleHeaderId) {
		this.articleHeaderId = articleHeaderId;
	}

	public String getTariffKeyword() {
		return this.tariffKeyword;
	}

	public void setTariffKeyword(String tariffKeyword) {
		this.tariffKeyword = tariffKeyword;
	}

	public String getTariffRemark() {
		return this.tariffRemark;
	}

	public void setTariffRemark(String tariffRemark) {
		this.tariffRemark = tariffRemark;
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
		this.updatedDate = createdDate;
	}

	public String getTariffTable() {
		return this.tariffTable;
	}

	public void setTariffTable(String tariffTable) {
		this.tariffTable = tariffTable;
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