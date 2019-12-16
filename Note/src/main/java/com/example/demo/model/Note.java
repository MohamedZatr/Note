package com.example.demo.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;


@AutoConfigurationPackage
@Entity
public class Note {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String text;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	private Notebook notebook;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedOn;
	@ManyToOne
	private Colors noteColor;
	@ManyToOne(fetch = FetchType.LAZY)
	private User userNote ;
	

	
	public User getUserNote() {
		return userNote;
	}
	public void setUserNote(User userNote) {
		this.userNote = userNote;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Notebook getNotebook() {
		return notebook;
	}
	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}
	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
	public Colors getNoteColor() {
		return noteColor;
	}
	public void setNoteColor(Colors noteColor) {
		this.noteColor = noteColor;
	}
	
	
	
	
}
