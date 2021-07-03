package com.config;

import java.util.Date;

import javax.servlet.http.Part;

public class ImageUploader {

	public static String upload(String uploadPath, Part part) {
		String fileName = "";
		try {
			// Handle uploaded file
			fileName = part.getSubmittedFileName();
			String[] arr = fileName.split("\\.");

			// Get extension
			fileName = arr[arr.length - 1];

			// Change filename to unix timestamp
			fileName = String.valueOf((new Date()).getTime()) + "." + fileName;
			part.write(uploadPath + fileName);

		} catch (Throwable e) {
			System.out.println(e);
		}
		return fileName;
	}
}