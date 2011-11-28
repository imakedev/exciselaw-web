package com.exciselaw.law.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the MS_MATRGROUP database table.
 * 
 */
@Entity
@Table(name="MS_MATRGROUP")
public class MsMatrgroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MS_MATRGROUP_SEQ" ,sequenceName="MS_MATRGROUP_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MS_MATRGROUP_SEQ")
	@Column(name="MATRGROUP_ID")
	private Long matrgroupId;

	@Column(name="MATRGROUP_ORDER")
	private BigDecimal matrgroupOrder;

	private String name;

	//bi-directional many-to-one association to MsStatute
    @ManyToOne
	@JoinColumn(name="STATUTE_ID")
	private MsStatute msStatute;

	//bi-directional many-to-one association to MsSubmatr
    @ManyToOne
	@JoinColumn(name="SUBMATR_ID")
	private MsSubmatr msSubmatr;

	//bi-directional many-to-one association to StMatr
	//@OneToMany(mappedBy="msMatrgroup")
	//private Set<StMatr> stMatrs;

    public MsMatrgroup() {
    }

	public Long getMatrgroupId() {
		return this.matrgroupId;
	}

	public void setMatrgroupId(Long matrgroupId) {
		this.matrgroupId = matrgroupId;
	}

	public BigDecimal getMatrgroupOrder() {
		return this.matrgroupOrder;
	}

	public void setMatrgroupOrder(BigDecimal matrgroupOrder) {
		this.matrgroupOrder = matrgroupOrder;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MsStatute getMsStatute() {
		return this.msStatute;
	}

	public void setMsStatute(MsStatute msStatute) {
		this.msStatute = msStatute;
	}
	
	public MsSubmatr getMsSubmatr() {
		return this.msSubmatr;
	}

	public void setMsSubmatr(MsSubmatr msSubmatr) {
		this.msSubmatr = msSubmatr;
	}
	
/*	public Set<StMatr> getStMatrs() {
		return this.stMatrs;
	}

	public void setStMatrs(Set<StMatr> stMatrs) {
		this.stMatrs = stMatrs;
	}*/
	
}