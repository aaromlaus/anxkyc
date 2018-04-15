package com.anx.kyc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_level_details")
public class UserLevelDetails {
	
	@Id
	@Column(name="user_level_details_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userLevelDetailsId;
	@ManyToOne(optional = false)
	@JoinColumn(name = "level_id")
	private Level level;
	@ManyToOne(optional = false)
	@JoinColumn(name = "anx_user_id")
	private AnxUser anxUser;
	@Column(name="level_limit")
	private boolean levelLimit;
	public UserLevelDetails(){
		super();
	}
	public UserLevelDetails(AnxUser anxUser,Level level,boolean levelLimit){
		this.level = level;
		this.anxUser = anxUser;
		this.levelLimit = levelLimit;
	}
	
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public AnxUser getAnxUser() {
		return anxUser;
	}
	public void setAnxUser(AnxUser anxUser) {
		this.anxUser = anxUser;
	}
	public boolean isLevelLimit() {
		return levelLimit;
	}
	public void setLevelLimit(boolean levelLimit) {
		this.levelLimit = levelLimit;
	}
	public Long getUserLevelDetailsId() {
		return userLevelDetailsId;
	}
	public void setUserLevelDetailsId(Long userLevelDetailsId) {
		this.userLevelDetailsId = userLevelDetailsId;
	}

}
