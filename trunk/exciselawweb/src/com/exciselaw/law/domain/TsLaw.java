package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TS_LAW database table.
 * 
 */
@Entity
@Table(name="TS_LAW")
public class TsLaw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LAW_SEQ", sequenceName="LAW_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LAW_SEQ")
	@Column(name="LAW_ID")
	private long lawId;

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

    @Lob()
	@Column(name="LAW_ATTACTH_TABLE")
	private String lawAttacthTable;

	@Column(name="LAW_DISPLAY_STATUS")
	private BigDecimal lawDisplayStatus;

	@Column(name="LAW_ENFORCE_STATUS")
	private BigDecimal lawEnforceStatus;

	@Column(name="LAW_EXCISE_STATUS")
	private BigDecimal lawExciseStatus;

	@Column(name="LAW_KEYWORD")
	private String lawKeyword;

	@Column(name="LAW_NO")
	private String lawNo;
	
    @Lob()
	@Column(name="LAW_DETAIL")
	private String lawDetail;

    @Lob()
	@Column(name="LAW_REMARK")
	private String lawRemark;

    @Lob()
	@Column(name="LAW_TITLE_ENG")
	private String lawTitleEng;

    @Lob()
	@Column(name="LAW_TITLE_THAI")
	private String lawTitleThai;

	@Column(name="LAW_TYPE_ID")
	private BigDecimal lawTypeId;

	@Column(name="LAW_YEAR")
	private String lawYear;

	@Column(name="STATUE_ID")
	private BigDecimal statueId;

	@Column(name="GROUP_NUMBER")
	private BigDecimal groupName;

	@Column(name="LAW_ORDER")
	private BigDecimal lawOrder;
	

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsLaw() {
    }

	public long getLawId() {
		return this.lawId;
	}

	public void setLawId(long lawId) {
		this.lawId = lawId;
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

	public String getLawAttacthTable() {
		return this.lawAttacthTable;
	}

	public void setLawAttacthTable(String lawAttacthTable) {
		this.lawAttacthTable = lawAttacthTable;
	}

	public BigDecimal getLawDisplayStatus() {
		return this.lawDisplayStatus;
	}

	public void setLawDisplayStatus(BigDecimal lawDisplayStatus) {
		this.lawDisplayStatus = lawDisplayStatus;
	}

	public BigDecimal getLawEnforceStatus() {
		return this.lawEnforceStatus;
	}

	public void setLawEnforceStatus(BigDecimal lawEnforceStatus) {
		this.lawEnforceStatus = lawEnforceStatus;
	}

	public BigDecimal getLawExciseStatus() {
		return this.lawExciseStatus;
	}

	public void setLawExciseStatus(BigDecimal lawExciseStatus) {
		this.lawExciseStatus = lawExciseStatus;
	}

	public String getLawKeyword() {
		return this.lawKeyword;
	}

	public void setLawKeyword(String lawKeyword) {
		this.lawKeyword = lawKeyword;
	}

	public String getLawNo() {
		return this.lawNo;
	}

	public void setLawNo(String lawNo) {
		this.lawNo = lawNo;
	}

	public String getLawDetail() {
		return lawDetail;
	}

	public void setLawDetail(String lawDetail) {
		this.lawDetail = lawDetail;
	}

	public String getLawRemark() {
		return this.lawRemark;
	}

	public void setLawRemark(String lawRemark) {
		this.lawRemark = lawRemark;
	}

	public String getLawTitleEng() {
		return this.lawTitleEng;
	}

	public void setLawTitleEng(String lawTitleEng) {
		this.lawTitleEng = lawTitleEng;
	}

	public String getLawTitleThai() {
		return this.lawTitleThai;
	}

	public void setLawTitleThai(String lawTitleThai) {
		this.lawTitleThai = lawTitleThai;
	}

	public BigDecimal getLawTypeId() {
		return this.lawTypeId;
	}

	public void setLawTypeId(BigDecimal lawTypeId) {
		this.lawTypeId = lawTypeId;
	}

	public String getLawYear() {
		return this.lawYear;
	}

	public void setLawYear(String lawYear) {
		this.lawYear = lawYear;
	}

	public BigDecimal getStatueId() {
		return this.statueId;
	}

	public void setStatueId(BigDecimal statueId) {
		this.statueId = statueId;
	}

	public BigDecimal getGroupName() {
		return groupName;
	}

	public void setGroupName(BigDecimal groupName) {
		this.groupName = groupName;
	}

	public BigDecimal getLawOrder() {
		return lawOrder;
	}

	public void setLawOrder(BigDecimal lawOrder) {
		this.lawOrder = lawOrder;
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