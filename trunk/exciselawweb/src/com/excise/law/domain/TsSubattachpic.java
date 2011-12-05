package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TS_SUBATTACHPIC database table.
 * 
 */
@Entity
@Table(name="TS_SUBATTACHPIC")
public class TsSubattachpic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TS_SUBATTACH_ID")
	private long tsSubattachId;

	@Column(name="ATTACH_FILE")
	private String attachFile;

	@Column(name="UPDATE_TIME")
	private String updateTime;

	@Column(name="UPDATE_USER")
	private String updateUser;

	//bi-directional many-to-one association to StStatutesub
    @ManyToOne
	@JoinColumn(name="ST_STATUTESUB_ID")
	private StStatutesub stStatutesub;

    public TsSubattachpic() {
    }

	public long getTsSubattachId() {
		return this.tsSubattachId;
	}

	public void setTsSubattachId(long tsSubattachId) {
		this.tsSubattachId = tsSubattachId;
	}

	public String getAttachFile() {
		return this.attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
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