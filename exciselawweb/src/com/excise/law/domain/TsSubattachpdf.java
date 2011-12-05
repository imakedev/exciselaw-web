package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TS_SUBATTACHPDF database table.
 * 
 */
@Entity
@Table(name="TS_SUBATTACHPDF")
public class TsSubattachpdf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TS_SUBATTACHPDF_ID")
	private long tsSubattachpdfId;

	@Column(name="ATTACH_FILE")
	private String attachFile;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	@Column(name="UPDATE_USER")
	private String updateUser;

	//bi-directional many-to-one association to StStatutesub
    @ManyToOne
	@JoinColumn(name="ST_STATUTESUB_ID")
	private StStatutesub stStatutesub;

    public TsSubattachpdf() {
    }

	public long getTsSubattachpdfId() {
		return this.tsSubattachpdfId;
	}

	public void setTsSubattachpdfId(long tsSubattachpdfId) {
		this.tsSubattachpdfId = tsSubattachpdfId;
	}

	public String getAttachFile() {
		return this.attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public StStatutesub getStStatutesub() {
		return this.stStatutesub;
	}

	public void setStStatutesub(StStatutesub stStatutesub) {
		this.stStatutesub = stStatutesub;
	}
	
}