package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TS_NEWS database table.
 * 
 */
@Entity
@Table(name="TS_NEWS")
public class TsNews implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NEWS_SEQ", sequenceName="NEWS_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NEWS_SEQ")
	@Column(name="NEWS_ID")
	private long newsId;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

    @Lob()
	@Column(name="NEWS_DETAIL")
	private String newsDetail;

	@Column(name="NEWS_DISPLAY_STATUS")
	private BigDecimal newsDisplayStatus;

    @Temporal( TemporalType.DATE)
	@Column(name="NEWS_END_DATE")
	private Date newsEndDate;

    @Lob()
	@Column(name="NEWS_IMAGE")
	private byte[] newsImage;

	@Column(name="NEWS_IMG_NAME")
	private String newsImgName;

    @Temporal( TemporalType.DATE)
	@Column(name="NEWS_START_DATE")
	private Date newsStartDate;

	@Column(name="NEWS_STATUS")
	private String newsStatus = "A";

	@Column(name="NEWS_TOPIC")
	private String newsTopic;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsNews() {
    }

	public long getNewsId() {
		return this.newsId;
	}

	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getNewsDetail() {
		return this.newsDetail;
	}

	public void setNewsDetail(String newsDetail) {
		this.newsDetail = newsDetail;
	}

	public BigDecimal getNewsDisplayStatus() {
		return this.newsDisplayStatus;
	}

	public void setNewsDisplayStatus(BigDecimal newsDisplayStatus) {
		this.newsDisplayStatus = newsDisplayStatus;
	}

	public Date getNewsEndDate() {
		return this.newsEndDate;
	}

	public void setNewsEndDate(Date newsEndDate) {
		this.newsEndDate = newsEndDate;
	}

	public byte[] getNewsImage() {
		return this.newsImage;
	}

	public void setNewsImage(byte[] newsImage) {
		this.newsImage = newsImage;
	}

	public String getNewsImgName() {
		return this.newsImgName;
	}

	public void setNewsImgName(String newsImgName) {
		this.newsImgName = newsImgName;
	}

	public Date getNewsStartDate() {
		return this.newsStartDate;
	}

	public void setNewsStartDate(Date newsStartDate) {
		this.newsStartDate = newsStartDate;
	}

	public String getNewsStatus() {
		return this.newsStatus;
	}

	public void setNewsStatus(String newsStatus) {
		this.newsStatus = newsStatus;
	}

	public String getNewsTopic() {
		return this.newsTopic;
	}

	public void setNewsTopic(String newsTopic) {
		this.newsTopic = newsTopic;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}