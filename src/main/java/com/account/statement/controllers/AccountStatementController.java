package com.account.statement.controllers;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.birt.report.engine.api.EngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.statement.dtos.ResponseDto;
import com.account.statement.services.AccountStatementPDFService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/api/v1/account")
public class AccountStatementController {
	
	/*
	 * This API is called from BIRT report engine with JSON request
	 * Here this method is receiving request
	 */
	@Autowired
	private AccountStatementPDFService service;
	
	
	@ApiOperation(value = "Generate Account Statement Report")
	@ApiResponses(value = { @ApiResponse(code = 200, message = ""), @ApiResponse(code = 400, message = "Bad Request"),
	@ApiResponse(code = 404, message = "Not Found"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/statement")
		public ResponseEntity<ResponseDto> pdfGenerationService(@RequestBody String jsonValue, HttpServletRequest request)throws JSONException, EngineException {
		System.out.println("account statement report generating");	
		ResponseEntity<ResponseDto> response = null;
		//Here service is calling to execute the Business Logic and return response to the birt engine
		response = service.generatePDf(jsonValue, request); 
		return response;
	}
	
	@GetMapping("/init")
	public String home() {
		return "Account Statemetn running.............";
	}
}

