package com.anx.kyc.service;

import org.springframework.web.multipart.MultipartFile;

import com.anx.kyc.model.AnxUser;

public interface FileUploadService {

	public void uploadAndSaveImage(MultipartFile file,AnxUser anxUser);

}
