package com.exciselaw.law.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the ST_MATR database table.
 * 
 */
@Entity
@Table(name="ST_MATR")
public class StMatr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ST_MATR_SEQ" ,sequenceName="ST_MATR_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ST_MATR_SEQ")
	@Column(name="ST_MATR_ID")
	private Long stMatrId;

	private String activate;

    @Lob()
	private String detail;

	private String matr;

	private String part;

	private String remark;

	/*@Column(name="STATUTE_ID")
	private BigDecimal statuteId;*/
	@ManyToOne
	@JoinColumn(name="STATUTE_ID")
	private MsStatute msStatute;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATE_TIME")
	private Date updateTime;

	@Column(name="UPDATE_USER")
	private String updateUser;

	//bi-directional many-to-one association to MsMatrgroup
    @ManyToOne
	@JoinColumn(name="MATRGROUP_ID")
	private MsMatrgroup msMatrgroup;

	//bi-directional many-to-one association to MsSubmatr
    @ManyToOne
	@JoinColumn(name="SUBMATR_ID")
	private MsSubmatr msSubmatr;

    public StMatr() {
    }

	public Long getStMatrId() {
		return this.stMatrId;
	}

	public void setStMatrId(Long stMatrId) {
		this.stMatrId = stMatrId;
	}

	public String getActivate() {
		return this.activate;
	}

	public void setActivate(String activate) {
		this.activate = activate;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getMatr() {
		return this.matr;
	}

	public void setMatr(String matr) {
		this.matr = matr;
	}

	public String getPart() {
		return this.part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*public BigDecimal getStatuteId() {
		return this.statuteId;
	}

	public void setStatuteId(BigDecimal statuteId) {
		this.statuteId = statuteId;
	}*/

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

	public MsMatrgroup getMsMatrgroup() {
		return this.msMatrgroup;
	}

	public void setMsMatrgroup(MsMatrgroup msMatrgroup) {
		this.msMatrgroup = msMatrgroup;
	}
	
	public MsSubmatr getMsSubmatr() {
		return this.msSubmatr;
	}

	public void setMsSubmatr(MsSubmatr msSubmatr) {
		this.msSubmatr = msSubmatr;
	}

	public MsStatute getMsStatute() {
		return msStatute;
	}

	public void setMsStatute(MsStatute msStatute) {
		this.msStatute = msStatute;
	}
	
}