package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the TS_PUBLISHED_GOODS database table.
 * 
 */
@Entity
@Table(name="TS_PUBLISHED_GOODS")
public class TsPublishedGood implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PUBLISHED_GOODS_SEQ", sequenceName="PUBLISHED_GOODS_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PUBLISHED_GOODS_SEQ")	
	@Column(name="PUBLISHED_GOODS_ID")
	private long publishedGoodsId;

	@Column(name="DUTY_GOODS_ID")
	private String dutyGoodsId;

	@Column(name="PUBLISHED_ID")
	private BigDecimal publishedId;

    public TsPublishedGood() {
    }

	public long getPublishedGoodsId() {
		return this.publishedGoodsId;
	}

	public void setPublishedGoodsId(long publishedGoodsId) {
		this.publishedGoodsId = publishedGoodsId;
	}

	public String getDutyGoodsId() {
		return this.dutyGoodsId;
	}

	public void setDutyGoodsId(String dutyGoodsId) {
		this.dutyGoodsId = dutyGoodsId;
	}

	public BigDecimal getPublishedId() {
		return this.publishedId;
	}

	public void setPublishedId(BigDecimal publishedId) {
		this.publishedId = publishedId;
	}

}