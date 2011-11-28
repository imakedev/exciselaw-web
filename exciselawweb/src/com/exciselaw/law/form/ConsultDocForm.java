package com.exciselaw.law.form;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.exciselaw.law.domain.ConsultDocFilter;
import com.exciselaw.law.domain.TmConsultDoc;
import com.exciselaw.law.domain.TsDocResolution;
import com.exciselaw.law.domain.TsDocState;

public class ConsultDocForm extends CommonForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TmConsultDoc tmConsultDoc;
	private TsDocState tsDocState;
	private TsDocResolution tsDocResolution;
	private ConsultDocFilter consultDocFilter;
	private String internalOfficeId;
	private String externalOfficeId;
	private String docDate;
	private String docRefDate;
	private String bureauRecDate;
	private String officeRecDate;
	private String sectionRecDate;
	private String docSendDate;
	private String bureauSendDate;
	private String sectionSendDate;
	private String [] deleteFileBoxes;
	private String[] msStatutesBoxes;
	private String createdDate;
	
    private String filename;
    private CommonsMultipartFile[] fileData;

	
	public ConsultDocForm(){
	}


	public TmConsultDoc getTmConsultDoc() {
		return tmConsultDoc;
	}


	public void setTmConsultDoc(TmConsultDoc tmConsultDoc) {
		this.tmConsultDoc = tmConsultDoc;
	}


	public TsDocState getTsDocState() {
		return tsDocState;
	}


	public void setTsDocState(TsDocState tsDocState) {
		this.tsDocState = tsDocState;
	}


	public TsDocResolution getTsDocResolution() {
		return tsDocResolution;
	}


	public void setTsDocResolution(TsDocResolution tsDocResolution) {
		this.tsDocResolution = tsDocResolution;
	}


	public String getDocDate() {
		return docDate;
	}


	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}


	public String getBureauRecDate() {
		return bureauRecDate;
	}


	public void setBureauRecDate(String bureauRecDate) {
		this.bureauRecDate = bureauRecDate;
	}


	public String getOfficeRecDate() {
		return officeRecDate;
	}


	public void setOfficeRecDate(String officeRecDate) {
		this.officeRecDate = officeRecDate;
	}


	public String getSectionRecDate() {
		return sectionRecDate;
	}


	public void setSectionRecDate(String sectionRecDate) {
		this.sectionRecDate = sectionRecDate;
	}


	public String getDocSendDate() {
		return docSendDate;
	}


	public void setDocSendDate(String docSendDate) {
		this.docSendDate = docSendDate;
	}


	public String[] getDeleteFileBoxes() {
		return deleteFileBoxes;
	}


	public void setDeleteFileBoxes(String[] deleteFileBoxes) {
		this.deleteFileBoxes = deleteFileBoxes;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public CommonsMultipartFile[] getFileData() {
		return fileData;
	}


	public void setFileData(CommonsMultipartFile[] fileData) {
		this.fileData = fileData;
	}


	public String getInternalOfficeId() {
		return internalOfficeId;
	}


	public void setInternalOfficeId(String internalOfficeId) {
		this.internalOfficeId = internalOfficeId;
	}


	public String getExternalOfficeId() {
		return externalOfficeId;
	}


	public void setExternalOfficeId(String externalOfficeId) {
		this.externalOfficeId = externalOfficeId;
	}


	public ConsultDocFilter getConsultDocFilter() {
		return consultDocFilter;
	}


	public void setConsultDocFilter(ConsultDocFilter consultDocFilter) {
		this.consultDocFilter = consultDocFilter;
	}


	public String[] getMsStatutesBoxes() {
		return msStatutesBoxes;
	}


	public void setMsStatutesBoxes(String[] msStatutesBoxes) {
		this.msStatutesBoxes = msStatutesBoxes;
	}
	
	public String getDocRefDate() {
		return docRefDate;
	}


	public void setDocRefDate(String docRefDate) {
		this.docRefDate = docRefDate;
	}


	public String getBureauSendDate() {
		return bureauSendDate;
	}


	public void setBureauSendDate(String bureauSendDate) {
		this.bureauSendDate = bureauSendDate;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public String getSectionSendDate() {
		return sectionSendDate;
	}


	public void setSectionSendDate(String sectionSendDate) {
		this.sectionSendDate = sectionSendDate;
	}
}
