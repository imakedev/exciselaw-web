package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TS_JUDGEMENT database table.
 * 
 */
@Entity
@Table(name="TS_JUDGEMENT")
public class TsJudgement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="JUDGEMENT_SEQ", sequenceName="JUDGEMENT_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JUDGEMENT_SEQ")
	@Column(name="JUDGEMENT_ID")
	private long judgementId;

	private String accused;

	private String accuser;

	@Column(name="AGENCY_CONSULTANT")
	private String agencyConsultant;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="FINISHED_PLACE")
	private String finishedPlace;

    @Temporal( TemporalType.DATE)
	@Column(name="JUDGEMENT_DATE")
	private Date judgementDate;

    @Lob()
	@Column(name="JUDGEMENT_DETAIL")
	private String judgementDetail;

	@Column(name="JUDGEMENT_DISPLAY")
	private BigDecimal judgementDisplay;

	@Column(name="JUDGEMENT_KEYWORD")
	private String judgementKeyword;

	@Column(name="JUDGEMENT_NUMBER1")
	private String judgementNumber1;

	@Column(name="JUDGEMENT_NUMBER2")
	private String judgementNumber2;

	@Column(name="JUDGEMENT_STATUS")
	private String judgementStatus = "A";

	@Column(name="JUDGEMENT_TOPIC")
	private String judgementTopic;

	@Column(name="JUDGEMENT_TYPE")
	private BigDecimal judgementType;

    @Lob()
	@Column(name="LONG_SUMMARY")
	private String longSummary;

    @Temporal( TemporalType.DATE)
	@Column(name="PRONOUNCEMENT_DATE")
	private Date pronouncementDate;

    @Lob()
	@Column(name="SHORT_SUMMARY")
	private String shortSummary;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsJudgement() {
    }

	public long getJudgementId() {
		return this.judgementId;
	}

	public void setJudgementId(long judgementId) {
		this.judgementId = judgementId;
	}

	public String getAccused() {
		return this.accused;
	}

	public void setAccused(String accused) {
		this.accused = accused;
	}

	public String getAccuser() {
		return this.accuser;
	}

	public void setAccuser(String accuser) {
		this.accuser = accuser;
	}

	public String getAgencyConsultant() {
		return this.agencyConsultant;
	}

	public void setAgencyConsultant(String agencyConsultant) {
		this.agencyConsultant = agencyConsultant;
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

	public String getFinishedPlace() {
		return this.finishedPlace;
	}

	public void setFinishedPlace(String finishedPlace) {
		this.finishedPlace = finishedPlace;
	}

	public Date getJudgementDate() {
		return this.judgementDate;
	}

	public void setJudgementDate(Date judgementDate) {
		this.judgementDate = judgementDate;
	}

	public String getJudgementDetail() {
		return this.judgementDetail;
	}

	public void setJudgementDetail(String judgementDetail) {
		this.judgementDetail = judgementDetail;
	}

	public BigDecimal getJudgementDisplay() {
		return this.judgementDisplay;
	}

	public void setJudgementDisplay(BigDecimal judgementDisplay) {
		this.judgementDisplay = judgementDisplay;
	}

	public String getJudgementKeyword() {
		return this.judgementKeyword;
	}

	public void setJudgementKeyword(String judgementKeyword) {
		this.judgementKeyword = judgementKeyword;
	}

	public String getJudgementNumber1() {
		return this.judgementNumber1;
	}

	public void setJudgementNumber1(String judgementNumber1) {
		this.judgementNumber1 = judgementNumber1;
	}

	public String getJudgementNumber2() {
		return this.judgementNumber2;
	}

	public void setJudgementNumber2(String judgementNumber2) {
		this.judgementNumber2 = judgementNumber2;
	}

	public String getJudgementStatus() {
		return this.judgementStatus;
	}

	public void setJudgementStatus(String judgementStatus) {
		this.judgementStatus = judgementStatus;
	}

	public String getJudgementTopic() {
		return this.judgementTopic;
	}

	public void setJudgementTopic(String judgementTopic) {
		this.judgementTopic = judgementTopic;
	}

	public BigDecimal getJudgementType() {
		return this.judgementType;
	}

	public void setJudgementType(BigDecimal judgementType) {
		this.judgementType = judgementType;
	}

	public String getLongSummary() {
		return this.longSummary;
	}

	public void setLongSummary(String longSummary) {
		this.longSummary = longSummary;
	}

	public Date getPronouncementDate() {
		return this.pronouncementDate;
	}

	public void setPronouncementDate(Date pronouncementDate) {
		this.pronouncementDate = pronouncementDate;
	}

	public String getShortSummary() {
		return this.shortSummary;
	}

	public void setShortSummary(String shortSummary) {
		this.shortSummary = shortSummary;
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