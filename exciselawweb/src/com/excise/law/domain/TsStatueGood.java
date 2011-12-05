package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the TS_STATUE_GOODS database table.
 * 
 */
@Entity
@Table(name="TS_STATUE_GOODS")
public class TsStatueGood implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATUE_GOOD_SEQ", sequenceName="STATUE_GOOD_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATUE_GOOD_SEQ")
	@Column(name="STATUE_GOODS_ID")
	private long statueGoodsId;

	@Column(name="DUTY_GROUP_ID")
	private String dutyGroupId;

	@Column(name="STATUE_ID")
	private BigDecimal statueId;

    public TsStatueGood() {
    }

	public long getStatueGoodsId() {
		return this.statueGoodsId;
	}

	public void setStatueGoodsId(long statueGoodsId) {
		this.statueGoodsId = statueGoodsId;
	}

	public String getDutyGroupId() {
		return this.dutyGroupId;
	}

	public void setDutyGroupId(String dutyGroupId) {
		this.dutyGroupId = dutyGroupId;
	}

	public BigDecimal getStatueId() {
		return this.statueId;
	}

	public void setStatueId(BigDecimal statueId) {
		this.statueId = statueId;
	}

}