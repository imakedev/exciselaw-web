package com.exciselaw.law.form;

import java.io.Serializable;

public class ReportForm extends CommonForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	
	public ReportForm(){
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
