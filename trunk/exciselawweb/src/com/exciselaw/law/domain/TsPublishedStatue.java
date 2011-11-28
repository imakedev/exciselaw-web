package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the TS_PUBLISHED_STATUE database table.
 * 
 */
@Entity
@Table(name="TS_PUBLISHED_STATUE")
public class TsPublishedStatue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PUBLISHED_STATUE_SEQ", sequenceName="PUBLISHED_STATUE_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PUBLISHED_STATUE_SEQ")
	@Column(name="PUBLISHED_STATUE_ID")
	private long publishedStatueId;

	@Column(name="PUBLISHED_ID")
	private BigDecimal publishedId;

	@Column(name="STATUE_ID")
	private BigDecimal statueId;

    public TsPublishedStatue() {
    }

	public long getPublishedStatueId() {
		return this.publishedStatueId;
	}

	public void setPublishedStatueId(long publishedStatueId) {
		this.publishedStatueId = publishedStatueId;
	}

	public BigDecimal getPublishedId() {
		return this.publishedId;
	}

	public void setPublishedId(BigDecimal publishedId) {
		this.publishedId = publishedId;
	}

	public BigDecimal getStatueId() {
		return this.statueId;
	}

	public void setStatueId(BigDecimal statueId) {
		this.statueId = statueId;
	}

}