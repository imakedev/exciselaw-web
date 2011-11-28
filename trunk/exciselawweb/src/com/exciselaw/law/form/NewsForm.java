package com.exciselaw.law.form;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.exciselaw.law.domain.NewsFilter;
import com.exciselaw.law.domain.TsNews;

public class NewsForm extends CommonForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private TsNews tsNews;
	private NewsFilter newsFilter;
	private String createdDate;
	private String newsStartDate;
	private String newsEndDate;
	private String [] deleteFileBoxes;
	

    private String filePicname;
    private CommonsMultipartFile filePic;
	
    private String filename;
    private CommonsMultipartFile[] fileData;

	
	public NewsForm(){
	}

	public TsNews getTsNews() {
		return tsNews;
	}

	public void setTsNews(TsNews tsNews) {
		this.tsNews = tsNews;
	}

	public NewsFilter getNewsFilter() {
		return newsFilter;
	}

	public void setNewsFilter(NewsFilter newsFilter) {
		this.newsFilter = newsFilter;
	}

	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public String getNewsStartDate() {
		return newsStartDate;
	}


	public void setNewsStartDate(String newsStartDate) {
		this.newsStartDate = newsStartDate;
	}


	public String getNewsEndDate() {
		return newsEndDate;
	}


	public void setNewsEndDate(String newsEndDate) {
		this.newsEndDate = newsEndDate;
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


	public String getFilePicname() {
		return filePicname;
	}

	public void setFilePicname(String filePicname) {
		this.filePicname = filePicname;
	}

	public CommonsMultipartFile getFilePic() {
		return filePic;
	}

	public void setFilePic(CommonsMultipartFile filePic) {
		this.filePic = filePic;
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

}
