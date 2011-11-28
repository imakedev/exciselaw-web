package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MS_PATH database table.
 * 
 */
@Entity
@Table(name="MS_PATH")
public class MsPath implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PATH_NO")
	private long pathNo;

	@Column(name="PATH_DESC")
	private String pathDesc;

	@Column(name="PATH_KEY")
	private String pathKey;

	@Column(name="PATH_NAME")
	private String pathName;

    public MsPath() {
    }

	public long getPathNo() {
		return this.pathNo;
	}

	public void setPathNo(long pathNo) {
		this.pathNo = pathNo;
	}

	public String getPathDesc() {
		return this.pathDesc;
	}

	public void setPathDesc(String pathDesc) {
		this.pathDesc = pathDesc;
	}

	public String getPathKey() {
		return this.pathKey;
	}

	public void setPathKey(String pathKey) {
		this.pathKey = pathKey;
	}

	public String getPathName() {
		return this.pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

}