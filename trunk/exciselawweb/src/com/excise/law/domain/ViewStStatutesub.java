package com.excise.law.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the VIEW_ST_STATUTESUB database table.
 * 
 */
@Entity
@Table(name="VIEW_ST_STATUTESUB")
public class ViewStStatutesub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ST_STATUTESUB_ID")
	private Long stStatutesubId;

	@Column(name="ANNOUNCE_BOOK")
	private String announceBook;

    @Temporal( TemporalType.DATE)
	@Column(name="ANNOUNCE_DATE")
	private Date announceDate;

	@Column(name="ANNOUNCE_NO")
	private String announceNo;

	@Column(name="ANNOUNCE_PART")
	private String announcePart;

	private String article;

	@Column(name="ARTICLE_ENG")
	private String articleEng;

	@Column(name="BOOK_NO")
	private BigDecimal bookNo;

	@Column(name="BOOK_YEAR")
	private String bookYear;

    @Lob()
	private String detail;

	private String display;

	private String hightlight;

	private String intro;

	private String keyword;

//	@Column(name="LEGALITEM_ID")
//	private Long legalitemId;

	private String remark;

	@Column(name="ST_ORDER")
	private String stOrder;

	
	private String status;

	@Column(name="STATUTE_ID")
	private Long statuteId;

	@Column(name="STATUTESUB_ID")
	private Long statutesubId;

    @Lob()
	@Column(name="TABLE_ATTACH")
	private String tableAttach;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATE_TIME")
	private Date updateTime;

	@Column(name="UPDATE_USER")
	private String updateUser;
	 
	@Column(name="STATUTE_NAME")
	private String statuteName;
	
	@Column(name="STATUTESUB_NAME")
	private String statuteSubName;
	
    public ViewStStatutesub() {
    }

	public String getAnnounceBook() {
		return this.announceBook;
	}

	public void setAnnounceBook(String announceBook) {
		this.announceBook = announceBook;
	}

	public Date getAnnounceDate() {
		return this.announceDate;
	}

	public void setAnnounceDate(Date announceDate) {
		this.announceDate = announceDate;
	}

	public String getAnnounceNo() {
		return this.announceNo;
	}

	public void setAnnounceNo(String announceNo) {
		this.announceNo = announceNo;
	}

	public String getAnnouncePart() {
		return this.announcePart;
	}

	public void setAnnouncePart(String announcePart) {
		this.announcePart = announcePart;
	}

	public String getArticle() {
		return this.article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getArticleEng() {
		return this.articleEng;
	}

	public void setArticleEng(String articleEng) {
		this.articleEng = articleEng;
	}

	public BigDecimal getBookNo() {
		return this.bookNo;
	}

	public void setBookNo(BigDecimal bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookYear() {
		return this.bookYear;
	}

	public void setBookYear(String bookYear) {
		this.bookYear = bookYear;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getHightlight() {
		return this.hightlight;
	}

	public void setHightlight(String hightlight) {
		this.hightlight = hightlight;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
/*
	public Long getLegalitemId() {
		return this.legalitemId;
	}

	public void setLegalitemId(Long legalitemId) {
		this.legalitemId = legalitemId;
	}
*/
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStOrder() {
		return this.stOrder;
	}

	public void setStOrder(String stOrder) {
		this.stOrder = stOrder;
	}

	public Long getStStatutesubId() {
		return this.stStatutesubId;
	}

	public void setStStatutesubId(Long stStatutesubId) {
		this.stStatutesubId = stStatutesubId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getStatuteId() {
		return this.statuteId;
	}

	public void setStatuteId(Long statuteId) {
		this.statuteId = statuteId;
	}

	public Long getStatutesubId() {
		return this.statutesubId;
	}

	public void setStatutesubId(Long statutesubId) {
		this.statutesubId = statutesubId;
	}

	public String getTableAttach() {
		return this.tableAttach;
	}

	public void setTableAttach(String tableAttach) {
		this.tableAttach = tableAttach;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getStatuteName() {
		return statuteName;
	}

	public void setStatuteName(String statuteName) {
		this.statuteName = statuteName;
	}

	public String getStatuteSubName() {
		return statuteSubName;
	}

	public void setStatuteSubName(String statuteSubName) {
		this.statuteSubName = statuteSubName;
	}

}