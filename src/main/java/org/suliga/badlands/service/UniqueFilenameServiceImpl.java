package org.suliga.badlands.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UniqueFilenameServiceImpl implements UniqueFilenameService {
	
	@Value("${file.upload.store.dir}")
    private String fileUploadStoreDir;

	private static final String[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".split("");
	
	@Override
	public String generate() {
		String dir = fileUploadStoreDir;
		
		if (!dir.endsWith(File.separator)) {
			dir += File.separator;
		}
		
		StringBuilder sb = null;
		final ThreadLocalRandom random = ThreadLocalRandom.current();

		do {
			sb = new StringBuilder(dir);
			for (int i=0;i<8;i++) {
				sb.append(chars[random.nextInt(0, chars.length)]);
			}
			sb.append(".dat");
		} while (Files.exists(Paths.get(sb.toString())));
		
		return sb.toString();
	}
}

