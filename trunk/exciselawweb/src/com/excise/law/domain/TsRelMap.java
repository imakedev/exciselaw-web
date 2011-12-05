package com.excise.law.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TS_REL_MAP")
public class TsRelMap implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@SequenceGenerator(name="REL_MAP_SEQ" ,sequenceName="REL_MAP_SEQ" ,allocationSize=1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REL_MAP_SEQ")
		@Column(name="RM_ID")
		private Long rmId;

		@Column(name="RM_KEY")
		private String rmKey;

		@Column(name="RM_NAME")
		private String rmName;
		
		@Column(name="RM_TABLE")
		private String rmTable;
		
		@Column(name="CREATED_BY")
		private String createdBy;

	    @Temporal( TemporalType.DATE)
		@Column(name="CREATED_DATE")
		private Date createdDate;
		
	    @Column(name="UPDATED_BY")
		private String updatedBy;

	    @Temporal( TemporalType.DATE)
		@Column(name="UPDATED_DATE")
		private Date updatedDate;
		public Long getRmId() {
			return rmId;
		}

		public void setRmId(Long rmId) {
			this.rmId = rmId;
		}

		public String getRmKey() {
			return rmKey;
		}

		public void setRmKey(String rmKey) {
			this.rmKey = rmKey;
		}

		public String getRmTable() {
			return rmTable;
		}

		public void setRmTable(String rmTable) {
			this.rmTable = rmTable;
		}

		public String getRmName() {
			return rmName;
		}

		public void setRmName(String rmName) {
			this.rmName = rmName;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public String getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}
		
}
