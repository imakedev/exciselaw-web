package com.excise.law.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the TS_REL_STATUTE database table.
 * 
 */
@Entity
@Table(name="TS_REL_STATUTE")
public class TsRelStatute implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TsRelStatutePK id;
    public TsRelStatute() {
    }
	public TsRelStatutePK getId() {
		return id;
	}
	public void setId(TsRelStatutePK id) {
		this.id = id;
	}
 
}