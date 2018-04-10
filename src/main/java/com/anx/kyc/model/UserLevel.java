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
	
	@Column(name = "user_level_name")
	private String userLevelName;
	
	@Column(name = "description")
	private String description;


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

	public int getUserLevelId() {
		return userLevelId;
	}

	public void setUserLevelId(int userLevelId) {
		this.userLevelId = userLevelId;
	}

}
