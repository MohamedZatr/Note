package com.example.demo.Response;

public class MessageResponse {
	String message;
	boolean state;
	public MessageResponse(String message, boolean state) {
		super();
		this.message = message;
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	
}
