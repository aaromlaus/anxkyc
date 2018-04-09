package com.anx.kyc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="level")
public class Level {
	@Id
	@Column(name="level_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long levelId;

	@Column(name="description")
	private String description;
	@Column(name="requirement")
	private String requirement;
	@Column(name="cash_in")
	private String cashIn;
	@Column(name="cash_out")
	private String cashOut;	
	
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
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	
	
}
