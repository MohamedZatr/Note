package com.example.demo.exception;

public class NotfoundResult extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotfoundResult (String message) {
		super(message);
	}
	
}
