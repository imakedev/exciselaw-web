package com.exciselaw.law.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the MS_SUBMATR database table.
 * 
 */
@Entity
@Table(name="MS_SUBMATR")
public class MsSubmatr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MS_SUBMATR_SEQ" ,sequenceName="MS_SUBMATR_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MS_SUBMATR_SEQ")
	@Column(name="SUBMATR_ID")
	private Long submatrId;

	private String name;

	@Column(name="SUBMATR_ORDER")
	private BigDecimal submatrOrder;

	@Column(name="SUIT_ORDER")
	private BigDecimal suitOrder;

	//bi-directional many-to-one association to MsMatrgroup
	/*@OneToMany(mappedBy="msSubmatr")
	private Set<MsMatrgroup> msMatrgroups;*/

	//bi-directional many-to-one association to StMatr
	/*
	@OneToMany(mappedBy="msSubmatr")
	private Set<StMatr> stMatrs;
	*/
    public MsSubmatr() {
    }

	public Long getSubmatrId() {
		return this.submatrId;
	}

	public void setSubmatrId(Long submatrId) {
		this.submatrId = submatrId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSubmatrOrder() {
		return this.submatrOrder;
	}

	public void setSubmatrOrder(BigDecimal submatrOrder) {
		this.submatrOrder = submatrOrder;
	}

	public BigDecimal getSuitOrder() {
		return this.suitOrder;
	}

	public void setSuitOrder(BigDecimal suitOrder) {
		this.suitOrder = suitOrder;
	}

	/*public Set<MsMatrgroup> getMsMatrgroups() {
		return this.msMatrgroups;
	}

	public void setMsMatrgroups(Set<MsMatrgroup> msMatrgroups) {
		this.msMatrgroups = msMatrgroups;
	}*/
	/*	
	public Set<StMatr> getStMatrs() {
		return this.stMatrs;
	}

	public void setStMatrs(Set<StMatr> stMatrs) {
		this.stMatrs = stMatrs;
	}
	*/
}