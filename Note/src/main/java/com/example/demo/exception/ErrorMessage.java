package com.example.demo.exception;

public class ErrorMessage {
	
	private final String message;
	private final int httpStatus;

	public ErrorMessage(String message, int httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}
		
		public String getMessage() {
			return message;
		}
		
		public int getHttpStatus() {
			return httpStatus;
		}
		


		
		
}
