package com.account.statement.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.account.statement.dtos.ResponseDto;

public interface AccountStatementPDFService {

	ResponseEntity<ResponseDto> generatePDf(String jsonValue, HttpServletRequest request);

}
