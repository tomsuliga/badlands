package org.suliga.badlands.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IMAGES")
public class Image {
	@Id
	@GeneratedValue
	private Long id;
	
	private String originalFilename;
	private String serverFilename;
	private Long size;
	
	public Image() {}
	
	public Image(String originalFilename, String serverFilename, Long size) {
		this.originalFilename = originalFilename;
		this.serverFilename = serverFilename;
		this.size = size;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getServerFilename() {
		return serverFilename;
	}
	public void setServerFilename(String serverFilename) {
		this.serverFilename = serverFilename;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
}

