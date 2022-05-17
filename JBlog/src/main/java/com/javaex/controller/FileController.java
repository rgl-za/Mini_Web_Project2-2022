package com.javaex.controller;


import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.javaex.service.BlogService;
import com.javaex.util.FileUtil;
import com.javaex.util.MediaUtil;
import com.javaex.vo.BlogVo;
import com.javaex.vo.FileVo;

@Controller
public class FileController {
	
	@Autowired
	private BlogService blogService;
	
	
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/basic", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public ModelAndView basic(MultipartFile file, ModelAndView mav, @PathVariable("id") String id) throws Exception {	
		mav.addObject("filePath", new ResponseEntity<String>(FileUtil.fileUpload(file).getFilePath(), HttpStatus.OK));
		mav.addObject("id", id);
		mav.addObject("postlist", blogService.contentList());
		mav.addObject("catelist", blogService.cateList(id));
		mav.setViewName("blog/blog-main");
		return mav;
	}
	
	// 이미지 업로드 처리
	@ResponseBody
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.POST, produces="text/plain; charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		return new ResponseEntity<String>(FileUtil.fileUpload(file).getSaveName(), HttpStatus.OK);
	}
	
	// 이미지 표시 처리
	@ResponseBody
	@RequestMapping(value = "/upload/displayFile", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			System.out.println(formatName);
			MediaType mType = MediaUtil.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream("/Users/USER/Documents/GitHub/Mini_Web_Project2-2022/JBlog/src/main/resources/upload/" + fileName);
			if(mType != null) {
				headers.setContentType(mType);
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		System.out.println(entity);
		return entity;
	}
	
}
