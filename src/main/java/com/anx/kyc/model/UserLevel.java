package com.anx.kyc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_level")
public class UserLevel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_level_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userLevelId;
	
	@Column(name = "user_level_group")
	private int userLevelGroup;

	@Column(name = "user_level_name")
	private String userLevelName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="requirement")
	private String requirement;
	
	@Column(name="cash_in")
	private String cashIn;

	@Column(name="cash_out")
	private String cashOut;	

	
	public int getUserLevelId() {
		return userLevelId;
	}

	public void setUserLevelId(int userLevelId) {
		this.userLevelId = userLevelId;
	}

	public int getUserLevelGroup() {
		return userLevelGroup;
	}

	public void setUserLevelGroup(int userLevelGroup) {
		this.userLevelGroup = userLevelGroup;
	}

	public String getUserLevelName() {
		return userLevelName;
	}

	public void setUserLevelName(String userLevelName) {
		this.userLevelName = userLevelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getCashIn() {
		return cashIn;
	}

	public void setCashIn(String cashIn) {
		this.cashIn = cashIn;
	}

	public String getCashOut() {
		return cashOut;
	}

	public void setCashOut(String cashOut) {
		this.cashOut = cashOut;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		return "UserLevel [userLevelId=" + userLevelId + ", userLevelGroup=" + userLevelGroup + ", userLevelName="
				+ userLevelName + ", description=" + description + ", requirement=" + requirement + ", cashIn=" + cashIn
				+ ", cashOut=" + cashOut + "]";
	}
	
	

}
