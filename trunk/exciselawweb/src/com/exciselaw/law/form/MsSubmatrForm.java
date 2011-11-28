package com.exciselaw.law.form;

import java.io.Serializable;

import com.exciselaw.law.domain.MsSubmatr;

public class MsSubmatrForm extends CommonForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MsSubmatr msSubmatr; 
	public MsSubmatrForm(){
		msSubmatr =new MsSubmatr();
	}
	public MsSubmatr getMsSubmatr() {
		return msSubmatr;
	}
	public void setMsSubmatr(MsSubmatr msSubmatr) {
		this.msSubmatr = msSubmatr;
	}
	 
}
