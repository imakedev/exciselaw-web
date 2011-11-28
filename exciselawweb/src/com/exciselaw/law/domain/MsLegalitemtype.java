package com.exciselaw.law.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MS_LEGALITEMTYPE database table.
 * 
 */
@Entity
@Table(name="MS_LEGALITEMTYPE")
public class MsLegalitemtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LEGALITEMTYPE_ID")
	private Long legalitemtypeId;

	private String name;

    public MsLegalitemtype() {
    }

	public Long getLegalitemtypeId() {
		return this.legalitemtypeId;
	}

	public void setLegalitemtypeId(Long legalitemtypeId) {
		this.legalitemtypeId = legalitemtypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}