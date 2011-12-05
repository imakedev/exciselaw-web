package com.excise.law.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The primary key class for the TS_STATUTESUB_LEGALITEM database table.
 * 
 */
@Embeddable
public class TsStatutesubLegalitemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	//bi-directional many-to-one association to MsLegalitem
    @ManyToOne
	@JoinColumn(name="LEGALITEM_ID")
	private MsLegalitem msLegalitem;

	//bi-directional many-to-one association to StStatutesub
    @ManyToOne
	@JoinColumn(name="ST_STATUTESUB_ID")
	private StStatutesub stStatutesub;

	/*@Column(name="LEGALITEM_ID")
	private Long legalitemId;

	@Column(name="ST_STATUTESUB_ID")
	private Long stStatutesubId;*/

    public TsStatutesubLegalitemPK() {
    }
    /*
	public Long getLegalRelId() {
		return this.legalRelId;
	}
	public void setLegalRelId(Long legalRelId) {
		this.legalRelId = legalRelId;
	}
	*/
	/*public Long getLegalitemId() {
		return this.legalitemId;
	}
	public void setLegalitemId(Long legalitemId) {
		this.legalitemId = legalitemId;
	}
	public Long getStStatutesubId() {
		return this.stStatutesubId;
	}
	public void setStStatutesubId(Long stStatutesubId) {
		this.stStatutesubId = stStatutesubId;
	}
*/
    
	public MsLegalitem getMsLegalitem() {
		return msLegalitem;
	}
	public void setMsLegalitem(MsLegalitem msLegalitem) {
		this.msLegalitem = msLegalitem;
	}
	public StStatutesub getStStatutesub() {
		return stStatutesub;
	}
	public void setStStatutesub(StStatutesub stStatutesub) {
		this.stStatutesub = stStatutesub;
	}
	
}