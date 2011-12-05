package com.excise.law.form;

import java.io.Serializable;

import com.excise.law.domain.MsStatute;
import com.excise.law.domain.MsStatutesub;

public class LawForm extends CommonForm implements Serializable{

	private static final long serialVersionUID = 1L;
	private MsStatute msStatute = new MsStatute();
	private MsStatutesub msStatutesub = new MsStatutesub();
	
	
	public MsStatute getMsStatute() {
		return msStatute;
	}
	public void setMsStatute(MsStatute msStatute) {
		this.msStatute = msStatute;
	}
	public MsStatutesub getMsStatutesub() {
		return msStatutesub;
	}
	public void setMsStatutesub(MsStatutesub msStatutesub) {
		this.msStatutesub = msStatutesub;
	}

}
