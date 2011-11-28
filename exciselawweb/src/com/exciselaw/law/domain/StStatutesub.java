package com.exciselaw.law.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the ST_STATUTESUB database table.
 * 
 */
@Entity
@Table(name="ST_STATUTESUB")
public class StStatutesub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ST_STATUTESUB_SEQ" ,sequenceName="ST_STATUTESUB_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ST_STATUTESUB_SEQ")
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

	private String remark;

	@Column(name="ST_ORDER")
	private String stOrder;

	private String status;

    @Lob()
	@Column(name="TABLE_ATTACH")
	private String tableAttach;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATE_TIME")
	private Date updateTime;

	@Column(name="UPDATE_USER")
	private String updateUser;

	//bi-directional many-to-one association to MsStatute
    @ManyToOne
	@JoinColumn(name="STATUTE_ID")
	private MsStatute msStatute;

	//bi-directional many-to-one association to MsStatutesub
    @ManyToOne
	@JoinColumn(name="STATUTESUB_ID")
	private MsStatutesub msStatutesub;

/*	//bi-directional many-to-one association to TsStatutesubLegalitem
	@OneToMany(mappedBy="stStatutesub")
	private Set<TsStatutesubLegalitem> tsStatutesubLegalitems;

	//bi-directional many-to-one association to TsSubattachpdf
	@OneToMany(mappedBy="stStatutesub")
	private Set<TsSubattachpdf> tsSubattachpdfs;

	//bi-directional many-to-one association to TsSubattachpic
	@OneToMany(mappedBy="stStatutesub")
	private Set<TsSubattachpic> tsSubattachpics;
*/
    public StStatutesub() {
    }

	public Long getStStatutesubId() {
		return this.stStatutesubId;
	}

	public void setStStatutesubId(Long stStatutesubId) {
		this.stStatutesubId = stStatutesubId;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public MsStatute getMsStatute() {
		return this.msStatute;
	}

	public void setMsStatute(MsStatute msStatute) {
		this.msStatute = msStatute;
	}
	
	public MsStatutesub getMsStatutesub() {
		return this.msStatutesub;
	}

	public void setMsStatutesub(MsStatutesub msStatutesub) {
		this.msStatutesub = msStatutesub;
	}
	
	/*public Set<TsStatutesubLegalitem> getTsStatutesubLegalitems() {
		return this.tsStatutesubLegalitems;
	}

	public void setTsStatutesubLegalitems(Set<TsStatutesubLegalitem> tsStatutesubLegalitems) {
		this.tsStatutesubLegalitems = tsStatutesubLegalitems;
	}
	
	public Set<TsSubattachpdf> getTsSubattachpdfs() {
		return this.tsSubattachpdfs;
	}

	public void setTsSubattachpdfs(Set<TsSubattachpdf> tsSubattachpdfs) {
		this.tsSubattachpdfs = tsSubattachpdfs;
	}
	
	public Set<TsSubattachpic> getTsSubattachpics() {
		return this.tsSubattachpics;
	}

	public void setTsSubattachpics(Set<TsSubattachpic> tsSubattachpics) {
		this.tsSubattachpics = tsSubattachpics;
	}*/
	
}