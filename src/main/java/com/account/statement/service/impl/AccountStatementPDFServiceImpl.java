package com.account.statement.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.account.statement.constants.KeyConstants;
import com.account.statement.dtos.CheckPaidsDto;
import com.account.statement.dtos.ResponseDto;
import com.account.statement.engine.BirtEngine;
import com.account.statement.entities.CheckPaidsEntity;
import com.account.statement.repositories.CheckPaidsRepository;
import com.account.statement.services.AccountStatementPDFService;
import com.account.statement.utils.CommonUtil;

@Service
public class AccountStatementPDFServiceImpl implements AccountStatementPDFService {
	private static final Logger logger = LoggerFactory.getLogger(AccountStatementPDFServiceImpl.class);

	@Autowired
	private CommonUtil commonUtil;
	@Autowired
	private BirtEngine birtEngine;
	@Autowired
	private CheckPaidsRepository checkPaidsRepository;

	@SuppressWarnings("finally")
	@Override
	public ResponseEntity<ResponseDto> generatePDf(String jsonValue, HttpServletRequest request) {
		List<CheckPaidsDto> checkPaidsDtoList = null;
		List<CheckPaidsEntity> customerEntity = null;
		ResponseDto resDto = null;
		String fileUrlPath = null;
		int randomReferenceNum = 0;
		try {
			JSONObject jsonObject = new JSONObject(jsonValue);
			//appending time stamp
			String fileNameWithTimeStamp = commonUtil.getFileNameWithTimeStamp(jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.TEMPLATE_ID));
			//generating random reference number to make uniqueness data in DB
			randomReferenceNum = commonUtil.generateRandomReferenceNumber();
			//parsing JSon data to list of DTO
			checkPaidsDtoList = parseAccountStatementTemplate(jsonValue);
			//converting list DTO to Entity
			customerEntity = convertCustomerDtoToEntity(checkPaidsDtoList, randomReferenceNum);
			//saving list of data in DB
			checkPaidsRepository.saveAll(customerEntity);
			//calling BirtEngine To Generate Report as Expected and return 1 or 0 status
			resDto = birtEngine.birtReportGenerate(randomReferenceNum, fileNameWithTimeStamp, jsonValue, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.TEMPLATE_ID),jsonObject.getString(KeyConstants.DOCUMENT_TYPE));
			//storing path
			fileUrlPath = commonUtil.getPath(fileNameWithTimeStamp,jsonObject.getString(KeyConstants.DOCUMENT_TYPE));
			logger.info(fileUrlPath);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(KeyConstants.FILE_NOT_FOUND_EXCEPTION + e.getMessage());
		} finally {
			return commonUtil.getResponse(resDto, fileUrlPath);
		}
	}
	
	public ArrayList<CheckPaidsDto> parseAccountStatementTemplate(String result) throws JSONException {
		ArrayList<CheckPaidsDto> userList = new ArrayList<>();
		JSONObject jsonObject = new JSONObject(result);
		JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("checks_paid");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonData = jsonArray.getJSONObject(i);
			CheckPaidsDto customerDTO = new CheckPaidsDto();
			customerDTO.setAmount(jsonData.getString("amount"));
			customerDTO.setReferenceNumber(jsonData.getString("referenceNumber"));
			customerDTO.setDataPaid(jsonData.getString("datePaid"));
			customerDTO.setCheckNumber(jsonData.getString("checkNumber"));
			userList.add(customerDTO);
		}
		return userList;
	}
	
	public List<CheckPaidsEntity> convertCustomerDtoToEntity(List<CheckPaidsDto> dataObjList, int randomReferenceNum) {
		List<CheckPaidsEntity> entityList = new ArrayList<CheckPaidsEntity>();
		dataObjList.stream().forEach(a -> {
			CheckPaidsEntity entity = new CheckPaidsEntity();
			entity.setReferenceNumber(a.getReferenceNumber());
			entity.setDataPaid(a.getDataPaid());
			entity.setCheckNumber(a.getCheckNumber());
			entity.setAmount(a.getAmount());
			entity.setUniqueReferenceNumber(randomReferenceNum);
			entity.setCreatedAt(LocalDateTime.now());
			entityList.add(entity);

		});
		return entityList;
	}
}
