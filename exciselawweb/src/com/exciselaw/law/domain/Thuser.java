package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the THUSER database table.
 * 
 */
@Entity
public class Thuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private String userId;

	@Column(name="CUST_DEPT_NO")
	private BigDecimal custDeptNo;

	@Column(name="PRENAME_CODE")
	private BigDecimal prenameCode;

	@Column(name="USR_FNAME")
	private String usrFname;

	@Column(name="USR_GRP_ID")
	private BigDecimal usrGrpId;

	@Column(name="USR_LNAME")
	private String usrLname;

	@Column(name="USR_PWD")
	private String usrPwd;

	@Column(name="USR_UID")
	private String usrUid;

    public Thuser() {
    }

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getCustDeptNo() {
		return this.custDeptNo;
	}

	public void setCustDeptNo(BigDecimal custDeptNo) {
		this.custDeptNo = custDeptNo;
	}

	public BigDecimal getPrenameCode() {
		return this.prenameCode;
	}

	public void setPrenameCode(BigDecimal prenameCode) {
		this.prenameCode = prenameCode;
	}

	public String getUsrFname() {
		return this.usrFname;
	}

	public void setUsrFname(String usrFname) {
		this.usrFname = usrFname;
	}

	public BigDecimal getUsrGrpId() {
		return this.usrGrpId;
	}

	public void setUsrGrpId(BigDecimal usrGrpId) {
		this.usrGrpId = usrGrpId;
	}

	public String getUsrLname() {
		return this.usrLname;
	}

	public void setUsrLname(String usrLname) {
		this.usrLname = usrLname;
	}

	public String getUsrPwd() {
		return this.usrPwd;
	}

	public void setUsrPwd(String usrPwd) {
		this.usrPwd = usrPwd;
	}

	public String getUsrUid() {
		return this.usrUid;
	}

	public void setUsrUid(String usrUid) {
		this.usrUid = usrUid;
	}

}