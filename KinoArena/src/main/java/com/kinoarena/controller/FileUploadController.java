//package com.kinoarena.controller;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//
//import javax.servlet.ServletContext;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//@Controller
//public class FileUploadController {
//
//	@Autowired
//	ServletContext context;
//	private static String UPLOAD_FOLDER = "C:\\kinoarena\\movies\\";
//
//	@RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
//	public String fileUploadPageTest(Model model) {
//
//		return "fileUpload";
//	}
//
//	@RequestMapping(value = "/fileUploadPage", method = RequestMethod.POST)
//	public @ResponseBody String handleFileUploadTest(@RequestParam("file") MultipartFile file) {
//		if (!file.isEmpty()) {
//			try {
//				System.out.println(file.getOriginalFilename());
//				System.out.println(file.getSize());
//				byte[] bytes = file.getBytes();
//				BufferedOutputStream stream = new BufferedOutputStream(
//						new FileOutputStream(new File(UPLOAD_FOLDER + File.pathSeparator + file.getOriginalFilename())));
//				stream.write(bytes);
//				stream.close();
//				return "success";
//			} catch (Exception e) {
//				return "error";
//			}
//		} else {
//			return "error";
//		}
//	}
//	
//	@RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
//	public String fileUploadPage(Model model) {
//
//		return "fileUpload";
//	}
//
//	@RequestMapping(value = "/fileUploadPage", method = RequestMethod.POST)
//	public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) {
//		String name = "image_name";
//		if (!file.isEmpty()) {
//			try {
//				System.out.println(file.getOriginalFilename());
//				System.out.println(file.getSize());
//				byte[] bytes = file.getBytes();
//				BufferedOutputStream stream = new BufferedOutputStream(
//						new FileOutputStream(new File(UPLOAD_FOLDER + File.pathSeparator + file.getOriginalFilename())));
//				stream.write(bytes);
//				stream.close();
//				return "success";
//			} catch (Exception e) {
//				return "error";
//			}
//		} else {
//			return "error";
//		}
//	}
//
//
//}
