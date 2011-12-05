package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the V_EX_ARTICLE_HEADER database table.
 * 
 */
@Entity
@Table(name="V_EX_ARTICLE_HEADER")
public class VExArticleHeader implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ARTICLE_HEADER_ID")
	private BigDecimal articleHeaderId;

	@Column(name="ARTICLE_HEADER_NAME")
	private String articleHeaderName;

	@Column(name="GAZETTE_DATED")
	private String gazetteDated;

	@Column(name="GAZETTE_EPISODE")
	private String gazetteEpisode;

	@Column(name="GAZETTE_NO")
	private String gazetteNo;

	@Column(name="GAZETTE_VOL")
	private String gazetteVol;

	@Column(name="LAW_TYPE_ID")
	private BigDecimal lawTypeId;

	@Column(name="LAW_TYPE_NAME")
	private String lawTypeName;

	@Column(name="STATUE_ID")
	private BigDecimal statueId;

	@Column(name="STATUE_NAME")
	private String statueName;

    public VExArticleHeader() {
    }

	public BigDecimal getArticleHeaderId() {
		return this.articleHeaderId;
	}

	public void setArticleHeaderId(BigDecimal articleHeaderId) {
		this.articleHeaderId = articleHeaderId;
	}

	public String getArticleHeaderName() {
		return this.articleHeaderName;
	}

	public void setArticleHeaderName(String articleHeaderName) {
		this.articleHeaderName = articleHeaderName;
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

	public BigDecimal getLawTypeId() {
		return this.lawTypeId;
	}

	public void setLawTypeId(BigDecimal lawTypeId) {
		this.lawTypeId = lawTypeId;
	}

	public String getLawTypeName() {
		return this.lawTypeName;
	}

	public void setLawTypeName(String lawTypeName) {
		this.lawTypeName = lawTypeName;
	}

	public BigDecimal getStatueId() {
		return this.statueId;
	}

	public void setStatueId(BigDecimal statueId) {
		this.statueId = statueId;
	}

	public String getStatueName() {
		return this.statueName;
	}

	public void setStatueName(String statueName) {
		this.statueName = statueName;
	}

}