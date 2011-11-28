package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TS_STATUTESUB_LEGALITEM database table.
 * 
 */
@Entity
@Table(name="TS_STATUTESUB_LEGALITEM")
public class TsStatutesubLegalitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TsStatutesubLegalitemPK id;

	/*
	//bi-directional many-to-one association to MsLegalitem
    @ManyToOne
	@JoinColumn(name="LEGALITEM_ID", insertable=false,updatable=false)
	private MsLegalitem msLegalitem;

	//bi-directional many-to-one association to StStatutesub
    @ManyToOne
	@JoinColumn(name="ST_STATUTESUB_ID"
	, insertable=false,updatable=false)
	private StStatutesub stStatutesub;
*/
    public TsStatutesubLegalitem() {
    }

	public TsStatutesubLegalitemPK getId() {
		return this.id;
	}

	public void setId(TsStatutesubLegalitemPK id) {
		this.id = id;
	}
	
	/*public MsLegalitem getMsLegalitem() {
		return this.msLegalitem;
	}

	public void setMsLegalitem(MsLegalitem msLegalitem) {
		this.msLegalitem = msLegalitem;
	}
	
	public StStatutesub getStStatutesub() {
		return this.stStatutesub;
	}

	public void setStStatutesub(StStatutesub stStatutesub) {
		this.stStatutesub = stStatutesub;
	}
	*/
}