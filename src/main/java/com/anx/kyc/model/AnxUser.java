package com.anx.kyc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="anx_user")
public class AnxUser {
		@Id
		@Column(name="anx_user_id")
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long anxUserId;
		@Column(name="last_name")
		private String lastName;
		@Column(name="first_name")
		private String firstName;
		@Column(name="middle_name")
		private String middleName;
		
		
		public Long getAnxUserId() {
			return anxUserId;
		}
		public void setAnxUserId(Long anxUserId) {
			this.anxUserId = anxUserId;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

}
