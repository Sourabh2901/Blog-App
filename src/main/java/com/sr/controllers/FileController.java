package com.sr.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sr.payloads.FileResponse;
import com.sr.services.FileService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile image){
		
		String fileName = null;
		try {
			fileName = fileService.uploadImage(path, image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(new FileResponse(fileName ,"Image  Not Uploaded") ,HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>(new FileResponse(fileName ,"Image Uploaded Succesfully") ,HttpStatus.OK);
	}
	
	
//	Method to Serve File
	@GetMapping(value = "/images/{imageName}" ,produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName ,HttpServletResponse response) throws IOException {
		
		InputStream resource = null;
		try {
			resource = fileService.getResource(path, imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(resource, response.getOutputStream());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
