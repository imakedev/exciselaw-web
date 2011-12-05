package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TS_JUDGEMENT_STATUE database table.
 * 
 */
@Entity
@Table(name="TS_JUDGEMENT_STATUE")
public class TsJudgementStatue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="JUDGEMENT_STATUE_SEQ", sequenceName="JUDGEMENT_STATUE_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JUDGEMENT_STATUE_SEQ")
	@Column(name="JUDGEMENT_STATUE_ID")
	private long judgementStatueId;

	@Column(name="JUDGEMENT_ID")
	private java.math.BigDecimal judgementId;

	@Column(name="STATUE_ID")
	private java.math.BigDecimal statueId;

    public TsJudgementStatue() {
    }

	public long getJudgementStatueId() {
		return this.judgementStatueId;
	}

	public void setJudgementStatueId(long judgementStatueId) {
		this.judgementStatueId = judgementStatueId;
	}

	public java.math.BigDecimal getJudgementId() {
		return this.judgementId;
	}

	public void setJudgementId(java.math.BigDecimal judgementId) {
		this.judgementId = judgementId;
	}

	public java.math.BigDecimal getStatueId() {
		return this.statueId;
	}

	public void setStatueId(java.math.BigDecimal statueId) {
		this.statueId = statueId;
	}

}