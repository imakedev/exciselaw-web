package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the THUSERGRP database table.
 * 
 */
@Entity
public class Thusergrp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USR_GRP_ID")
	private long usrGrpId;

	@Column(name="USR_GRP_NAME")
	private String usrGrpName;

    public Thusergrp() {
    }

	public long getUsrGrpId() {
		return this.usrGrpId;
	}

	public void setUsrGrpId(long usrGrpId) {
		this.usrGrpId = usrGrpId;
	}

	public String getUsrGrpName() {
		return this.usrGrpName;
	}

	public void setUsrGrpName(String usrGrpName) {
		this.usrGrpName = usrGrpName;
	}

}