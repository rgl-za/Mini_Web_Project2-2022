package com.javaex.vo;

// import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BlogVo {
	
	private int userNo;
	private String id;
	private String blogTitle;
	private String logoFile;
	// private MultipartFile file;	
}
