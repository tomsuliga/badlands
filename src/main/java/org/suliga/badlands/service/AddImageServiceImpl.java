package org.suliga.badlands.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.suliga.badlands.dao.ImageDao;
import org.suliga.badlands.model.Image;

@Service
@Transactional
public class AddImageServiceImpl implements AddImageService {
	private static final Logger logger = LoggerFactory.getLogger(AddImageServiceImpl.class);
	
	@Autowired
	private UniqueFilenameService uniqueFilenameService;
	
	@Autowired
	private ImageDao imageDao;
	
	@Override
	public void addImages(MultipartFile... imageFiles) {
		Arrays.stream(imageFiles).forEach( mf -> {
			if (mf != null &&  mf.getOriginalFilename() != null && mf.getOriginalFilename().length() > 0) {
				logger.info("mf.getOriginalFilename()=" + mf.getOriginalFilename());
				logger.info("mf.getSize()=" + mf.getSize());
				String serverFilename = uniqueFilenameService.generate();
				try {
					mf.transferTo(new File(serverFilename));
				} catch (IOException e) {
					e.printStackTrace();
				}
				imageDao.save(new Image(mf.getOriginalFilename(), serverFilename, mf.getSize()));
			}
		});
	}
}
