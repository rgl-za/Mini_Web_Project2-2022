package com.javaex.vo;

import lombok.Data;

@Data
public class FileVo {
	
	private String orgName; 
	private String exName; 
	private String saveName; 
	private String filePath; 
	private long fileSize;
	
	public FileVo() {}

	public FileVo(String orgName, String exName, String saveName, String filePath, long fileSize) {
		this.orgName = orgName;
		this.exName = exName;
		this.saveName = saveName; 
		this.filePath = filePath; 
		this.fileSize = fileSize;
	}
	

}
