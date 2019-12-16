 package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.demo.model.Colors;

@Entity
public class Notebook {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "notebook",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Note> notes;
	@ManyToOne
	private Colors notebookColor;
	@ManyToOne(fetch = FetchType.LAZY)
	private User userNotebook ;
	
	
	public User getUserNotebook() {
		return userNotebook;
	}
	public void setUserNotebook(User userNotebook) {
		this.userNotebook = userNotebook;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public Colors getNotebookColor() {
		return notebookColor;
	}
	public void setNotebookColor(Colors notebookColor) {
		this.notebookColor = notebookColor;
	}
	
	
	
}
