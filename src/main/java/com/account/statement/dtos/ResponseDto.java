package com.account.statement.dtos;

import org.springframework.http.HttpStatus;

public class ResponseDto {
	private HttpStatus status;
	private Data data;
	private String errorMessage;
	private int isError;
	
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public int getIsError() {
		return isError;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setIsError(int isError) {
		this.isError = isError;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	
}
