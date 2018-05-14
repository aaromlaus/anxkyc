package com.anx.kyc.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="identification_card")
public class IdentificationCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "id_type")
	private String idType;
	
	@Column(name = "id_number")
	private String idNumber;
	
	@Column(name = "expiration_date")
	private Date expirationDate;

	@Lob
	@Column(name = "front_img")
	private byte[] frontImg;

	@Lob
	@Column(name = "back_img")
	private byte[] backImg;

	private String expDateStr;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public byte[] getFrontImg() {
		return frontImg;
	}

	public void setFrontImg(byte[] frontImg) {
		this.frontImg = frontImg;
	}

	public byte[] getBackImg() {
		return backImg;
	}

	public void setBackImg(byte[] backImg) {
		this.backImg = backImg;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getExpDateStr() {
		return expDateStr;
	}

	public void setExpDateStr(String expDateStr) {
		this.expDateStr = expDateStr;
	}
	
	public String getFormattedExpDateStr() {
		if(expirationDate == null) return "";
		return new SimpleDateFormat("yyyy-MM-dd").format(expirationDate);
	}

	public String getBackImgStr() {
		return new String(this.backImg);
	}

	public String getFrontImgStr() {
		return new String(this.frontImg);
	}

}
