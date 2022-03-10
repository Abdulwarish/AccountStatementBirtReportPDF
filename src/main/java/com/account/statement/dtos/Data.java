package com.account.statement.dtos;

import java.time.LocalDateTime;

public class Data {
	private String fileUrl;
	private String ttl;
	private LocalDateTime createdDateTime;
	public String getFileUrl() {
		return fileUrl;
	}
	public String getTtl() {
		return ttl;
	}
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	

}
