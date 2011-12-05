package com.excise.law.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MS_STATUTESUB database table.
 * 
 */
@Entity
@Table(name="MS_STATUTESUB")
public class MsStatutesub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STATUTESUB_ID")
	private Long statutesubId;

	@Column(name="FOLDER_NAME")
	private String folderName;

	private String name;

	private BigDecimal status;

	@Column(name="STATUTESUB_GROUP")
	private BigDecimal statutesubGroup;

	@Column(name="STATUTESUB_ORDER")
	private BigDecimal statutesubOrder;

/*	//bi-directional many-to-one association to StStatutesub
	@OneToMany(mappedBy="msStatutesub")
	private Set<StStatutesub> stStatutesubs;*/

    public MsStatutesub() {
    }

	public Long getStatutesubId() {
		return this.statutesubId;
	}

	public void setStatutesubId(Long statutesubId) {
		this.statutesubId = statutesubId;
	}

	public String getFolderName() {
		return this.folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getStatutesubGroup() {
		return this.statutesubGroup;
	}

	public void setStatutesubGroup(BigDecimal statutesubGroup) {
		this.statutesubGroup = statutesubGroup;
	}

	public BigDecimal getStatutesubOrder() {
		return this.statutesubOrder;
	}

	public void setStatutesubOrder(BigDecimal statutesubOrder) {
		this.statutesubOrder = statutesubOrder;
	}

	
	
}