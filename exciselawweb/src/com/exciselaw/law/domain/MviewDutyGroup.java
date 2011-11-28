package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the MVIEW_DUTY_GROUP database table.
 * 
 */
@Entity
@Table(name="MVIEW_DUTY_GROUP")
public class MviewDutyGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="GROUP_ID")
	private String groupId;
	
    @Temporal( TemporalType.DATE)
	@Column(name="BEGIN_DATE")
	private Date beginDate;

    @Temporal( TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;    

	@Column(name="GROUP_NAME")
	private String groupName;

	@Column(name="GROUP_STATUS")
	private String groupStatus;

	@Column(name="SUP_GROUP_ID")
	private String supGroupId;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USERID")
	private String updUserid;

    public MviewDutyGroup() {
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

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupStatus() {
		return this.groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}

	public String getSupGroupId() {
		return this.supGroupId;
	}

	public void setSupGroupId(String supGroupId) {
		this.supGroupId = supGroupId;
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

}