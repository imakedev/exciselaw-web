package com.exciselaw.law.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.exciselaw.law.domain.MsLegalitem;
import com.exciselaw.law.domain.MsStatute;

public class MsStatuteForm extends CommonForm  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MsStatute msStatute; 	
	private TsStatuteLegalitemCheckBox[] tsStatuteLegalitemCheckBoxes;
	private List<MsLegalitem> msLegalitemProducts;
	private List<MsLegalitem> msLegalitemServices;
	private String[] boxeValues;
	private String[] productBoxes;
	private String[] serviceBoxes;
	public String[] getBoxeValues() {
		return boxeValues;
	}
	public void setBoxeValues(String[] boxeValues) {
		this.boxeValues = boxeValues;
	}

	public List<MsLegalitem> getMsLegalitemProducts() {
		return msLegalitemProducts;
	}
	public void setMsLegalitemProducts(List<MsLegalitem> msLegalitemProducts) {
		this.msLegalitemProducts = msLegalitemProducts;
	}
	public List<MsLegalitem> getMsLegalitemServices() {
		return msLegalitemServices;
	}
	public void setMsLegalitemServices(List<MsLegalitem> msLegalitemServices) {
		this.msLegalitemServices = msLegalitemServices;
	}
	public String[] getProductBoxes() {
		return productBoxes;
	}
	public void setProductBoxes(String[] productBoxes) {
		this.productBoxes = productBoxes;
	}
	public String[] getServiceBoxes() {
		return serviceBoxes;
	}
	public void setServiceBoxes(String[] serviceBoxes) {
		this.serviceBoxes = serviceBoxes;
	}
	public MsStatuteForm(){
		msStatute =new MsStatute();
		msLegalitemProducts = new ArrayList<MsLegalitem>();
		msLegalitemServices = new ArrayList<MsLegalitem>();
	}
	public MsStatute getMsStatute() {
		return msStatute;
	}
	public void setMsStatute(MsStatute msStatute) {
		this.msStatute = msStatute;
	}
	 
	public TsStatuteLegalitemCheckBox[] getTsStatuteLegalitemCheckBoxes() {
		return tsStatuteLegalitemCheckBoxes;
	}
	public void setTsStatuteLegalitemCheckBoxes(
			TsStatuteLegalitemCheckBox[] tsStatuteLegalitemCheckBoxes) {
		this.tsStatuteLegalitemCheckBoxes = tsStatuteLegalitemCheckBoxes;
	}

}
