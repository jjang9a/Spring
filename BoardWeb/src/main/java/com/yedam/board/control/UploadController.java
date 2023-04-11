package com.yedam.board.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.board.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("uploadForm....");
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax...");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile) {
		String uploadFolder = "c:\\upload";
		for(MultipartFile multipartFile : uploadFile) {
			log.info("-------------------");
			log.info("Upload FileName : "+ multipartFile.getOriginalFilename());
			log.info("file Size : " + multipartFile.getSize());

			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
					
		}
	}
	
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody // 매소드 앞에다 붙여도 되고 변수쪽에다 써도 됨
	public List<AttachFileDTO> uploadAjaxPost(MultipartFile[] uploadFile) {
		
		List<AttachFileDTO> list = new ArrayList<>();
				
		String uploadFolder = "c:\\upload";
		String uploadFolderPath = getFolder();
		// folder setting
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info(uploadPath);
		
		if(uploadPath.exists() == false) {
			// 경로의 폴더가 존재하는지 체크 후 없으면 생성
			uploadPath.mkdirs();
		}
	
		for(MultipartFile multipartFile : uploadFile) {
			AttachFileDTO attachDTO = new AttachFileDTO();
			// 가지고 온 업로드 된 파일들의 정보. 밑의 포구문을 돌면서 정보를 담아 for문이 끝나기전에 list에 add해서 생성한 정보를 담아줌
			
			String uploadFileName = multipartFile.getOriginalFilename();
			attachDTO.setFileName(uploadFileName); // attachDTO setting....
			
			// 중복된 파일이름을 방지. UUID 이용 ->
			UUID uuid = UUID.randomUUID(); // 문자열이 중복되지않도록 랜덤생성
			// 주의점 : 나중에 읽어올때 이 UUID값을 계속 기억하고 있어야 함
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info(uploadFileName);
			
			// File saveFile = new File(uploadFolder, uploadFileName);
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString()); // attachDTO setting.... 
				attachDTO.setUploadPath(uploadFolderPath); // attachDTO setting....
				
				// 이미지 파일이면 썸네일 이미지 생성
				if(checkImageType(saveFile)) {
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 200, 200);
					thumbnail.close();
				}
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			list.add(attachDTO);
		}	// end of for
		
		return list;
	} // end of uploadAjaxPost
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		File file = new File("c:\\upload\\" + fileName);
		ResponseEntity<byte[]> result = null;
		
		byte[] files;
		try {
			files = FileCopyUtils.copyToByteArray(file);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath())); // image/png, image/jpg... content-type지정을 위함
			
			result = new ResponseEntity<byte[]>(files, headers, HttpStatus.OK);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 업로드 파일을 저장할 폴더를 날짜별로 생성해서 저장하기 위한 폴더네이밍
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator); // 운영체제가 지원하는 형태로 파일을 구분하기 위함 \, /
	}
	
	// 이미지 파일 여부 체크
	private boolean checkImageType(File file) {
		String contentType;
		try {
			contentType = Files.probeContentType(file.toPath());
			log.info(contentType);
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
