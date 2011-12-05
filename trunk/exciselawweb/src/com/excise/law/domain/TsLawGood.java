package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TS_LAW_GOOD database table.
 * 
 */
@Entity
@Table(name="TS_LAW_GOOD")
public class TsLawGood implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LAW_GOOD_SEQ", sequenceName="LAW_GOOD_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LAW_GOOD_SEQ")
	@Column(name="LAW_GOOD_ID")
	private long lawGoodId;

	@Column(name="DUTY_GOODS_ID")
	private String dutyGoodsId;

	@Column(name="LAW_ID")
	private java.math.BigDecimal lawId;

    public TsLawGood() {
    }

	public long getLawGoodId() {
		return this.lawGoodId;
	}

	public void setLawGoodId(long lawGoodId) {
		this.lawGoodId = lawGoodId;
	}

	public String getDutyGoodsId() {
		return this.dutyGoodsId;
	}

	public void setDutyGoodsId(String dutyGoodsId) {
		this.dutyGoodsId = dutyGoodsId;
	}

	public java.math.BigDecimal getLawId() {
		return this.lawId;
	}

	public void setLawId(java.math.BigDecimal lawId) {
		this.lawId = lawId;
	}

}