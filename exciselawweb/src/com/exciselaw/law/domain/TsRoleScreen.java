package com.exciselaw.law.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TS_ROLE_SCREEN database table.
 * 
 */
@Entity
@Table(name="TS_ROLE_SCREEN")
public class TsRoleScreen implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="ROLE_SCREEN_SEQ" ,sequenceName="ROLE_SCREEN_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_SCREEN_SEQ")
	@Column(name="ROLE_SCREEN_ID")
	private long roleScreenId;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    @Column(name="ACTION_ID")
	private BigDecimal actionId;

    @Column(name="ROLE_ID")
	private String roleId;

    @Column(name="SCREEN_ID")
	private BigDecimal screenId;

    public TsRoleScreen() {
    }

	public long getRoleScreenId() {
		return this.roleScreenId;
	}

	public void setRoleScreenId(long roleScreenId) {
		this.roleScreenId = roleScreenId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BigDecimal getActionId() {
		return actionId;
	}

	public void setActionId(BigDecimal actionId) {
		this.actionId = actionId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public BigDecimal getScreenId() {
		return screenId;
	}

	public void setScreenId(BigDecimal screenId) {
		this.screenId = screenId;
	}
}