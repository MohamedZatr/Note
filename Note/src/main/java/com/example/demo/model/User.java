package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String userName;
	@Column(length = 60)
	private String userPassword;
	@Column(unique = true)
	private String email;
	private String type;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userNotebook",fetch = FetchType.LAZY)
	private List<Notebook> notebooks;
	@JsonIgnore
	@OneToMany(mappedBy = "userNote", fetch =  FetchType.LAZY)
	private List<Note> notes;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Notebook> getNotebooks() {
		return notebooks;
	}
	public void setNotebooks(List<Notebook> notebooks) {
		this.notebooks = notebooks;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	
}
