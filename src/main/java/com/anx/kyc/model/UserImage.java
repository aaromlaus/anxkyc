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
@Table(name="user_image")
public class UserImage {
	
	@Id
	@Column(name="user_image_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userImageId;
	@ManyToOne(optional = false)
	@JoinColumn(name = "anx_user_id")
	private AnxUser anxUser;
	@Column(name = "link")
	private String link;
	@Column(name ="level")
	private String level;


	public UserImage(AnxUser anxUser,String link) {
		this.anxUser = anxUser;
		this.link = link;
	}
	public UserImage(){
		super();
	}
	
	public Long getUserImageId() {
		return userImageId;
	}

	public void setUserImageId(Long userImageId) {
		this.userImageId = userImageId;
	}

	public AnxUser getAnxUser() {
		return anxUser;
	}
	public void setAnxUser(AnxUser anxUser) {
		this.anxUser = anxUser;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
