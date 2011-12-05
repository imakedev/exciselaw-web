package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TS_STATUTE_LEGALITEM database table.
 * 
 */
@Entity
@Table(name="TS_STATUTE_LEGALITEM")
public class TsStatuteLegalitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TsStatuteLegalitemPK id;

	/*//bi-directional many-to-one association to MsLegalitem
    @ManyToOne
	@JoinColumn(name="LEGALITEM_ID" , insertable=false,updatable=false)
	private MsLegalitem msLegalitem;

	//bi-directional many-to-one association to MsStatute
    @ManyToOne
	@JoinColumn(name="STATUTE_ID", insertable=false,updatable=false)
	private MsStatute msStatute;
*/
    public TsStatuteLegalitem() {
    }

	public TsStatuteLegalitemPK getId() {
		return this.id;
	}

	public void setId(TsStatuteLegalitemPK id) {
		this.id = id;
	}
	
	/*public MsLegalitem getMsLegalitem() {
		return this.msLegalitem;
	}

	public void setMsLegalitem(MsLegalitem msLegalitem) {
		this.msLegalitem = msLegalitem;
	}
	
	public MsStatute getMsStatute() {
		return this.msStatute;
	}

	public void setMsStatute(MsStatute msStatute) {
		this.msStatute = msStatute;
	}
	*/
}