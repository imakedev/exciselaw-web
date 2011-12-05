package com.excise.law.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TsRelStatutePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/*@Column(name="LEGALITEM_ID")
	private Long legalitemId;*/
	@ManyToOne
	@JoinColumn(name="STATUTE_ID")
	private MsStatute msStatute;
	/*
	@Column(name="STATUTESUB_ID")
	private Long statutesubId;
 */
	/*@Column(name="STATUTE_ID")
	private Long statuteId;*/
	@ManyToOne
	@JoinColumn(name="ST_STATUTESUB_ID")
	private StStatutesub stStatutesub;
	public MsStatute getMsStatute() {
		return msStatute;
	}
	public void setMsStatute(MsStatute msStatute) {
		this.msStatute = msStatute;
	}
	public StStatutesub getStStatutesub() {
		return stStatutesub;
	}
	public void setStStatutesub(StStatutesub stStatutesub) {
		this.stStatutesub = stStatutesub;
	}

}
