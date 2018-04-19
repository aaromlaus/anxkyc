package com.anx.kyc.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	void uploadAndSaveImage(MultipartFile file);

}
