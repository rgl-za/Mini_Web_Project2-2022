package com.javaex.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.FileVo;

public class FileUtil {

	public static FileVo fileUpload(MultipartFile file) {

		// String saveDir = "D:\\javaStudy\\upload";
//		String saveDir = "/Users/jeongjonguk/eclipse-workspace/jblog/src/main/resources/upload";

		String saveDir = "/Users/jihyeonjeong/Mini_Web_Project2-2022/JBlog/src/main/resources/upload/";
		// 원파일이름
		String orgName = file.getOriginalFilename();

		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));

		// 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

		// 파일패스 생성
		String filePath = saveDir + "/" + saveName;

		// 파일 사이즈
		long fileSize = file.getSize();

		// 파일업로드
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir + "/" + saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);

			if (bout != null) {
				bout.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		FileVo fileVo = new FileVo(orgName, exName, saveName, filePath, fileSize);

		return fileVo;

	}
}
