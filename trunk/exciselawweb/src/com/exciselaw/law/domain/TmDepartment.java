package com.exciselaw.law.domain;

import java.io.Serializable;

public class TmDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String deptId;
	private String deptName;

    public TmDepartment() {
    }

	public TmDepartment(String deptId, String deptName) {
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}