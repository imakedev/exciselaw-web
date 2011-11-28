package com.exciselaw.law.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The primary key class for the TS_STATUTE_LEGALITEM database table.
 * 
 */
@Embeddable
public class TsStatuteLegalitemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/*@Column(name="LEGALITEM_ID")
	private Long legalitemId;*/
	@ManyToOne
	@JoinColumn(name="LEGALITEM_ID")
	private MsLegalitem msLegalitem;
	/*
	@Column(name="STATUTESUB_ID")
	private Long statutesubId;
 */
	/*@Column(name="STATUTE_ID")
	private Long statuteId;*/
	@ManyToOne
	@JoinColumn(name="STATUTE_ID")
	private MsStatute msStatute;
    public TsStatuteLegalitemPK() {
    }
	/*public Long getLegalitemId() {
		return this.legalitemId;
	}
	public void setLegalitemId(Long legalitemId) {
		this.legalitemId = legalitemId;
	}*/
	/*
	public Long getStatutesubId() {
		return this.statutesubId;
	} 

	public void setStatutesubId(Long statutesubId) {
		this.statutesubId = statutesubId;
	}	
	*/
	/*public Long getStatuteId() {
		return this.statuteId;
	}
	public void setStatuteId(Long statuteId) {
		this.statuteId = statuteId;
	}*/
	public MsLegalitem getMsLegalitem() {
		return msLegalitem;
	}
	public void setMsLegalitem(MsLegalitem msLegalitem) {
		this.msLegalitem = msLegalitem;
	}
	public MsStatute getMsStatute() {
		return msStatute;
	}
	public void setMsStatute(MsStatute msStatute) {
		this.msStatute = msStatute;
	}

	 
}