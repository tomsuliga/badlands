package org.suliga.badlands.service;

import org.springframework.web.multipart.MultipartFile;

public interface AddImageService {
	void addImages(MultipartFile... imageFiles);
}
