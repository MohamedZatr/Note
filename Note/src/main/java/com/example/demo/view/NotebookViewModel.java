package com.example.demo.view;

public class NotebookViewModel {
	String id;
	String name;
	int numOfNote;
	String colorNotebook;
	String userEmail;
	
	
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public NotebookViewModel() {
		super();
	}
	public int getNumOfNote() {
		return numOfNote;
	}
	public void setNumOfNote(int numOfNote) {
		this.numOfNote = numOfNote;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColorNotebook() {
		return colorNotebook;
	}
	public void setColorNotebook(String colorNotebook) {
		this.colorNotebook = colorNotebook;
	}
	
}
