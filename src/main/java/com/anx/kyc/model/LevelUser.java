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
@Table(name="level_user")
public class LevelUser {
	
	@Id
	@Column(name="level_user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long levelUserId;
	@ManyToOne(optional = false)
	@JoinColumn(name = "level_id")
	private Level level;
	@ManyToOne(optional = false)
	@JoinColumn(name = "anx_user_id")
	private AnxUser anxUser;
	@Column(name="level_limit")
	private boolean levelLimit;
	public LevelUser(){
		super();
	}
	public LevelUser(AnxUser anxUser,Level level,boolean levelLimit){
		this.level = level;
		this.anxUser = anxUser;
		this.levelLimit = levelLimit;
	}
	public Long getLevelUserId() {
		return levelUserId;
	}
	public void setLevelUserId(Long levelUserId) {
		this.levelUserId = levelUserId;
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

}
