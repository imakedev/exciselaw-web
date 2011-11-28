package com.exciselaw.law.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the MS_STATUTE database table.
 * 
 */
@Entity
@Table(name="MS_STATUTE")
public class MsStatute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MS_STATUTE_SEQ" ,sequenceName="MS_STATUTE_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MS_STATUTE_SEQ")
	@Column(name="STATUTE_ID")
	private Long statuteId;

	private BigDecimal active;

	private String name;

	@Column(name="STATUTE_ORDER")
	private BigDecimal statuteOrder;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATE_TIME")
	private Date updateTime;

	@Column(name="UPDATE_USER")
	private String updateUser;
	/*
	//bi-directional many-to-one association to MsMatrgroup
	@OneToMany(mappedBy="msStatute")
	private Set<MsMatrgroup> msMatrgroups;

	//bi-directional many-to-one association to StAttachpdf
	@OneToMany(mappedBy="msStatute")
	private Set<StAttachpdf> stAttachpdfs;

	//bi-directional many-to-one association to StAttachpic
	@OneToMany(mappedBy="msStatute")
	private Set<StAttachpic> stAttachpics;

	//bi-directional many-to-one association to StStatutesub
	@OneToMany(mappedBy="msStatute")
	private Set<StStatutesub> stStatutesubs;

	//bi-directional many-to-one association to TsStatuteLegalitem
	@OneToMany(mappedBy="msStatute")
	private Set<TsStatuteLegalitem> tsStatuteLegalitems;
	*/
    public MsStatute() {
    }

	public Long getStatuteId() {
		return this.statuteId;
	}

	public void setStatuteId(Long statuteId) {
		this.statuteId = statuteId;
	}

	public BigDecimal getActive() {
		return this.active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getStatuteOrder() {
		return this.statuteOrder;
	}

	public void setStatuteOrder(BigDecimal statuteOrder) {
		this.statuteOrder = statuteOrder;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/*
	public Set<MsMatrgroup> getMsMatrgroups() {
		return this.msMatrgroups;
	}

	public void setMsMatrgroups(Set<MsMatrgroup> msMatrgroups) {
		this.msMatrgroups = msMatrgroups;
	}
	
	public Set<StAttachpdf> getStAttachpdfs() {
		return this.stAttachpdfs;
	}

	public void setStAttachpdfs(Set<StAttachpdf> stAttachpdfs) {
		this.stAttachpdfs = stAttachpdfs;
	}
	
	public Set<StAttachpic> getStAttachpics() {
		return this.stAttachpics;
	}

	public void setStAttachpics(Set<StAttachpic> stAttachpics) {
		this.stAttachpics = stAttachpics;
	}
	
	public Set<StStatutesub> getStStatutesubs() {
		return this.stStatutesubs;
	}

	public void setStStatutesubs(Set<StStatutesub> stStatutesubs) {
		this.stStatutesubs = stStatutesubs;
	}
	
	public Set<TsStatuteLegalitem> getTsStatuteLegalitems() {
		return this.tsStatuteLegalitems;
	}

	public void setTsStatuteLegalitems(Set<TsStatuteLegalitem> tsStatuteLegalitems) {
		this.tsStatuteLegalitems = tsStatuteLegalitems;
	}
	*/
}