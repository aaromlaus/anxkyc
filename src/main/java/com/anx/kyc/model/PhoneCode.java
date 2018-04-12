package com.anx.kyc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "phone_code")
public class PhoneCode {
	@Id
	@Column(name = "phone_code_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long phoneCodeId;
	@Column(name = "phone_code_name")
	private String phoneCodeName;
	@Column(name = "country")
	private String country;
	@Transient
	private String phoneCodeCountry;
	public Long getPhoneCodeId() {
		return phoneCodeId;
	}
	public void setPhoneCodeId(Long phoneCodeId) {
		this.phoneCodeId = phoneCodeId;
	}
	public String getPhoneCodeName() {
		return phoneCodeName;
	}
	public void setPhoneCodeName(String phoneCodeName) {
		this.phoneCodeName = phoneCodeName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneCodeCountry() {
		return phoneCodeName +" - " +country;
	}
	public void setPhoneCodeCountry(String phoneCodeCountry) {
		this.phoneCodeCountry = phoneCodeCountry;
	}

}
