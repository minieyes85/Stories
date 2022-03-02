package com.minieyes.stories.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.minieyes.stories.common.FileManagerService;

public class FileManagerService {
	
	public final static String FILE_UPLOAD_PATH = "D:\\JSChoi_Web_development\\upload\\images/";
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	
	//파일 저장
	public static String saveFile(int userId, MultipartFile file) {		
		
		// 첨부파일 없을 경우 예외처리
		if(file == null) {
			logger.error("FileManagerService::saveFile - There is no uploaded file.");
			return null;
		}
		
		//파일 저장 경로
		//사용자 별로 구분
		//사용자가 파일을 올린 시간으로 구분
		// /12_12355574553/test.jpg
		// 1970.1.1 기준 현재 밀리세컨.
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		//디렉토리 생성
		File directory = new File(filePath);
		if(directory.mkdirs() == false) {
			//디렉토리 생성 에러
			logger.error("FileManagerService::saveFile - Fail to make target directory");
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			//파일 저장 경로 + 파일 이름 경로 객체
			Path path = Paths.get(filePath + file.getOriginalFilename());
			
			//파일 저장
			Files.write(path, bytes);
			
		} catch (IOException e) {
			//파일 저장 실패
			logger.error("FileManagerService::saveFile - Fail to write target file");
			e.printStackTrace();
			return null;
		}
		
		// 파일 접근 가능 주소 리턴
		// ex <img src="/images/12_12349865665/test.png">
		
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	
	// 파일 삭제
	public static void removeFile(String filePath) {
		
		if(filePath == null) {
			logger.error("FileManagerService::removeFile - There is no target file.");
			return;
		}
		
		// 삭제할 파일 경로
		// filePath : /images/2_2465787665/test.png
		// 실제 파일 경로 : D:\\JSChoi_Web_development\\upload\\images\\2_2465787665/test.png
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/", "");
		
		// 파일 지우기
		Path path = Paths.get(realFilePath);
		
		// 파일이 있는지 확인
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileManagerService::removeFile - Fail to delete target file");
				e.printStackTrace();
			}
		}
		
		// 디렉토리 삭제 (폴더)
		// 실제 디렉토리 경로 : D:\\JSChoi_Web_development\\upload\\images\\2_2465787665
		path = path.getParent();
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileManagerService::removeFile - Fail to delete target directory");
				e.printStackTrace();
			}
		}
		
		
	}
}
