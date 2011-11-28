package com.exciselaw.law.form;

import java.io.Serializable;

import com.exciselaw.law.domain.StMatr;

public class StMatrForm extends CommonForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StMatr stMatr; 
	private String updateTime;
	
		public StMatrForm(){
			stMatr =new StMatr();
	}
		public StMatr getStMatr() {
			return stMatr;
		}
		public void setStMatr(StMatr stMatr) {
			this.stMatr = stMatr;
		}
		public String getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}
		 
	
}
