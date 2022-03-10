package com.account.statement.utils;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.account.statement.dtos.Data;
import com.account.statement.dtos.ResponseDto;
import com.account.statement.exceptions.BirtGlobalException;

@Service
public class CommonUtil {
	private static final String DEFAULT_ALGORITHM = "SHA1PRNG";
	public String getFileNameWithTimeStamp(String fileName) {
		String name = null;
		String extension = null;
		try {
			name = fileName.substring(0, fileName.lastIndexOf('.'));
			extension = fileName.substring(fileName.lastIndexOf('.'));
			fileName = name + "_" + System.currentTimeMillis() + extension;
		} catch (Exception e) {
			fileName = System.currentTimeMillis() + "_" + fileName;
		}
		return fileName;

	}
	
	public Integer generateRandomReferenceNumber() {
		int rand = 0;
		try {
			Random random = SecureRandom.getInstance(DEFAULT_ALGORITHM);
			rand = 100000 + random.nextInt(900000);
		} catch (NoSuchAlgorithmException e) {
			throw new BirtGlobalException("Failed to generate Otp ", e);
		}
		return rand;
	}
	
	public ResponseEntity<ResponseDto> getResponse(ResponseDto resDto,String fileUrlPath) {
		if (resDto.getIsError() == 0) {
			resDto = jsonResponse(fileUrlPath, HttpStatus.OK,null);
			return ResponseEntity.ok(resDto);
		} else {
			resDto = jsonResponse(null, HttpStatus.INTERNAL_SERVER_ERROR,resDto.getErrorMessage());
			return ResponseEntity.ok(resDto);
		}
	}
	
	public static ResponseDto jsonResponse(String fileUrlPath, HttpStatus statusCode,String errorMessage) {
		ResponseDto resDTO = new ResponseDto();
		Data dataDto = new Data();
		dataDto.setFileUrl(fileUrlPath);
		dataDto.setCreatedDateTime(LocalDateTime.now());
		dataDto.setTtl("180");
		resDTO.setStatus(statusCode);
		resDTO.setData(dataDto);
		resDTO.setErrorMessage(errorMessage);
		return resDTO;
	}

	public String getPath(String fileNameWithTimeStamp, String extension) throws UnknownHostException {
		return fileNameWithTimeStamp + "."+extension;
	}
}
