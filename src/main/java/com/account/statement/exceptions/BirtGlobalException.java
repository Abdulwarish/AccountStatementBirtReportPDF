package com.account.statement.exceptions;

public class BirtGlobalException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8404558988759267375L;

	private String message;
	private Boolean status;
	
	public BirtGlobalException(String message) {
        super(message);
    }
	

    public BirtGlobalException(Boolean status, String message ) {
		super();
		this.message = message;
		this.status = status;
	}


	public BirtGlobalException(String message, Throwable cause) {
        super(message, cause);
    }


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
