package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ST_ATTACHPIC database table.
 * 
 */
@Entity
@Table(name="ST_ATTACHPIC")
public class StAttachpic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STATUTE_ATTACH_ID")
	private long statuteAttachId;

	@Column(name="ATTACH_FILE")
	private String attachFile;

	@Column(name="UPDATE_TIME")
	private String updateTime;

	@Column(name="UPDATE_USER")
	private String updateUser;

	//bi-directional many-to-one association to MsStatute
    @ManyToOne
	@JoinColumn(name="STATUTE_ID")
	private MsStatute msStatute;

    public StAttachpic() {
    }

	public long getStatuteAttachId() {
		return this.statuteAttachId;
	}

	public void setStatuteAttachId(long statuteAttachId) {
		this.statuteAttachId = statuteAttachId;
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

	public MsStatute getMsStatute() {
		return this.msStatute;
	}

	public void setMsStatute(MsStatute msStatute) {
		this.msStatute = msStatute;
	}
	
}