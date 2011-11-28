package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TS_LAW_STATUE database table.
 * 
 */
@Entity
@Table(name="TS_LAW_STATUE")
public class TsLawStatue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LAW_STATUE_SEQ", sequenceName="LAW_STATUE_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LAW_STATUE_SEQ")
	@Column(name="LAW_STATUE_ID")
	private long lawStatueId;

	@Column(name="LAW_ID")
	private java.math.BigDecimal lawId;

	@Column(name="STATUE_ID")
	private java.math.BigDecimal statueId;

    public TsLawStatue() {
    }

	public long getLawStatueId() {
		return this.lawStatueId;
	}

	public void setLawStatueId(long lawStatueId) {
		this.lawStatueId = lawStatueId;
	}

	public java.math.BigDecimal getLawId() {
		return this.lawId;
	}

	public void setLawId(java.math.BigDecimal lawId) {
		this.lawId = lawId;
	}

	public java.math.BigDecimal getStatueId() {
		return this.statueId;
	}

	public void setStatueId(java.math.BigDecimal statueId) {
		this.statueId = statueId;
	}

}