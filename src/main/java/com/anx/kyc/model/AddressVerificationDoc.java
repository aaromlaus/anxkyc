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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "address_document")
@Entity
public class AddressVerificationDoc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "type")
	private String type;

	@Column(name = "file_name")
	private String fileName;

	@Lob
	@Column(name = "file")
	private byte[] file;

	@Column(name = "exp_date")
	private Date expirationDate;

	@Column(name = "doc_shows_name")
	private boolean documentShowsName;
	
	private String expirationDateStr;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isDocumentShowsName() {
		return documentShowsName;
	}

	public void setDocumentShowsName(boolean documentShowsName) {
		this.documentShowsName = documentShowsName;
	}

	public String getExpirationDateStr() {
		return expirationDateStr;
	}

	public void setExpirationDateStr(String expirationDateStr) {
		this.expirationDateStr = expirationDateStr;
	}
	
	public String getFormattedExpirationDate() {
		if(expirationDate == null) return "";
		return new SimpleDateFormat("yyyy-MM-dd").format(expirationDate);
	}
	
	public String getFileStr() {
		return new String(this.file);
	}

}
