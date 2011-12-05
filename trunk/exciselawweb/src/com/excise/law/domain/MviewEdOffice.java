package com.excise.law.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the MVIEW_ED_OFFICE database table.
 * 
 */
@Entity
@Table(name="MVIEW_ED_OFFICE")
public class MviewEdOffice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OFFCODE")
	private String offcode;

    @Temporal( TemporalType.DATE)
	@Column(name="BEGIN_DATE")
	private Date beginDate;

    @Temporal( TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="INDC_OFF")
	private String indcOff;

	private String offname;

	@Column(name="SHORT_NAME")
	private String shortName;

	@Column(name="TAMBOL_CODE")
	private String tambolCode;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USERID")
	private String updUserid;

	@Column(name="SUPOFFCODE")
	private String supoffcode;

    public MviewEdOffice() {
    }

	public String getOffcode() {
		return this.offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIndcOff() {
		return this.indcOff;
	}

	public void setIndcOff(String indcOff) {
		this.indcOff = indcOff;
	}

	public String getOffname() {
		return this.offname;
	}

	public void setOffname(String offname) {
		this.offname = offname;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTambolCode() {
		return this.tambolCode;
	}

	public void setTambolCode(String tambolCode) {
		this.tambolCode = tambolCode;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdUserid() {
		return this.updUserid;
	}

	public void setUpdUserid(String updUserid) {
		this.updUserid = updUserid;
	}

	public String getSupoffcode() {
		return supoffcode;
	}

	public void setSupoffcode(String supoffcode) {
		this.supoffcode = supoffcode;
	}
	
}