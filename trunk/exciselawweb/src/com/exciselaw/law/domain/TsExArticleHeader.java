package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TS_EX_ARTICLE_HEADER database table.
 * 
 */
@Entity
@Table(name="TS_EX_ARTICLE_HEADER")
public class TsExArticleHeader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ARTICLE_HEADER_SEQ", sequenceName="ARTICLE_HEADER_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTICLE_HEADER_SEQ")
	@Column(name="ARTICLE_HEADER_ID")
	private long articleHeaderId;

    @Lob()
	@Column(name="ARTICLE_HEADER_DETAIL")
	private String articleHeaderDetail;

	@Column(name="ARTICLE_HEADER_NAME")
	private String articleHeaderName;

	@Column(name="ARTICLE_HEADER_REMARK")
	private String articleHeaderRemark;

    @Lob()
	private String countersigned;
    
    @Lob()
	@Column(name="TABLE_ATTACH" )
	private String tableAttach;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="GAZETTE_DATED")
	private String gazetteDated;

	@Column(name="GAZETTE_EPISODE")
	private String gazetteEpisode;

	@Column(name="GAZETTE_NO")
	private String gazetteNo;

	@Column(name="GAZETTE_VOL")
	private String gazetteVol;

	@Column(name="LAW_TYPE_ID")
	private java.math.BigDecimal lawTypeId;

	@Column(name="STATUE_ID")
	private java.math.BigDecimal statueId;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsExArticleHeader() {
    }

	public long getArticleHeaderId() {
		return this.articleHeaderId;
	}

	public void setArticleHeaderId(long articleHeaderId) {
		this.articleHeaderId = articleHeaderId;
	}

	public String getArticleHeaderDetail() {
		return this.articleHeaderDetail;
	}

	public void setArticleHeaderDetail(String articleHeaderDetail) {
		this.articleHeaderDetail = articleHeaderDetail;
	}

	public String getArticleHeaderName() {
		return this.articleHeaderName;
	}

	public void setArticleHeaderName(String articleHeaderName) {
		this.articleHeaderName = articleHeaderName;
	}

	public String getArticleHeaderRemark() {
		return this.articleHeaderRemark;
	}

	public void setArticleHeaderRemark(String articleHeaderRemark) {
		this.articleHeaderRemark = articleHeaderRemark;
	}

	public String getCountersigned() {
		return this.countersigned;
	}

	public void setCountersigned(String countersigned) {
		this.countersigned = countersigned;
	}

	public String getTableAttach() {
		return tableAttach;
	}

	public void setTableAttach(String tableAttach) {
		this.tableAttach = tableAttach;
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

	public String getGazetteDated() {
		return this.gazetteDated;
	}

	public void setGazetteDated(String gazetteDated) {
		this.gazetteDated = gazetteDated;
	}

	public String getGazetteEpisode() {
		return this.gazetteEpisode;
	}

	public void setGazetteEpisode(String gazetteEpisode) {
		this.gazetteEpisode = gazetteEpisode;
	}

	public String getGazetteNo() {
		return this.gazetteNo;
	}

	public void setGazetteNo(String gazetteNo) {
		this.gazetteNo = gazetteNo;
	}

	public String getGazetteVol() {
		return this.gazetteVol;
	}

	public void setGazetteVol(String gazetteVol) {
		this.gazetteVol = gazetteVol;
	}

	public java.math.BigDecimal getLawTypeId() {
		return this.lawTypeId;
	}

	public void setLawTypeId(java.math.BigDecimal lawTypeId) {
		this.lawTypeId = lawTypeId;
	}

	public java.math.BigDecimal getStatueId() {
		return this.statueId;
	}

	public void setStatueId(java.math.BigDecimal statueId) {
		this.statueId = statueId;
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