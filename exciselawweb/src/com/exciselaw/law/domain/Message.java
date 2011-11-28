package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MESSAGE database table.
 * 
 */
@Entity
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private String descriptoin;

    public Message() {
    }

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescriptoin() {
		return this.descriptoin;
	}

	public void setDescriptoin(String descriptoin) {
		this.descriptoin = descriptoin;
	}

}