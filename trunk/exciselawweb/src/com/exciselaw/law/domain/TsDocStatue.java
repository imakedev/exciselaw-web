package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TS_DOC_STATUE database table.
 * 
 */
@Entity
@Table(name="TS_DOC_STATUE")
public class TsDocStatue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOC_STATUE_SEQ", sequenceName="DOC_STATUE_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOC_STATUE_SEQ")
	@Column(name="DS_ID")
	private long dsId;

	@Column(name="DOC_ID")
	private java.math.BigDecimal docId;

	@Column(name="STATUE_ID")
	private java.math.BigDecimal statueId;

    public TsDocStatue() {
    }

	public long getDsId() {
		return this.dsId;
	}

	public void setDsId(long dsId) {
		this.dsId = dsId;
	}

	public java.math.BigDecimal getDocId() {
		return this.docId;
	}

	public void setDocId(java.math.BigDecimal docId) {
		this.docId = docId;
	}

	public java.math.BigDecimal getStatueId() {
		return this.statueId;
	}

	public void setStatueId(java.math.BigDecimal statueId) {
		this.statueId = statueId;
	}

}