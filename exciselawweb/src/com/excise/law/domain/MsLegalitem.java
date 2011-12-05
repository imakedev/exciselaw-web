package com.excise.law.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MS_LEGALITEM database table.
 * 
 */
@Entity
@Table(name="MS_LEGALITEM")
public class MsLegalitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LEGALITEM_ID")
	private Long legalitemId;

	@Column(name="LEGALITEM_ORDER")
	private BigDecimal legalitemOrder;

	/*@Column(name="LEGALITEMTYPE_ID")
	private BigDecimal legalitemtypeId;*/
	
	@ManyToOne
	@JoinColumn(name="LEGALITEMTYPE_ID")
	private MsLegalitemtype msLegalitemtype;

	private String name;

	//bi-directional many-to-one association to TsStatutesubLegalitem
/*
	@OneToMany(mappedBy="msLegalitem")
	private Set<TsStatutesubLegalitem> tsStatutesubLegalitems;

	//bi-directional many-to-one association to TsStatuteLegalitem
	@OneToMany(mappedBy="msLegalitem")
	private Set<TsStatuteLegalitem> tsStatuteLegalitems;
*/
    public MsLegalitem() {
    }

	public Long getLegalitemId() {
		return this.legalitemId;
	}

	public void setLegalitemId(Long legalitemId) {
		this.legalitemId = legalitemId;
	}

	public BigDecimal getLegalitemOrder() {
		return this.legalitemOrder;
	}

	public void setLegalitemOrder(BigDecimal legalitemOrder) {
		this.legalitemOrder = legalitemOrder;
	}

	/*public BigDecimal getLegalitemtypeId() {
		return this.legalitemtypeId;
	}

	public void setLegalitemtypeId(BigDecimal legalitemtypeId) {
		this.legalitemtypeId = legalitemtypeId;
	}*/

	public String getName() {
		return this.name;
	}

	public MsLegalitemtype getMsLegalitemtype() {
		return msLegalitemtype;
	}

	public void setMsLegalitemtype(MsLegalitemtype msLegalitemtype) {
		this.msLegalitemtype = msLegalitemtype;
	}

	public void setName(String name) {
		this.name = name;
	}
/*
	public Set<TsStatutesubLegalitem> getTsStatutesubLegalitems() {
		return this.tsStatutesubLegalitems;
	}

	public void setTsStatutesubLegalitems(Set<TsStatutesubLegalitem> tsStatutesubLegalitems) {
		this.tsStatutesubLegalitems = tsStatutesubLegalitems;
	}
	
	public Set<TsStatuteLegalitem> getTsStatuteLegalitems() {
		return this.tsStatuteLegalitems;
	}

	public void setTsStatuteLegalitems(Set<TsStatuteLegalitem> tsStatuteLegalitems) {
		this.tsStatuteLegalitems = tsStatuteLegalitems;
	}
	*/
}