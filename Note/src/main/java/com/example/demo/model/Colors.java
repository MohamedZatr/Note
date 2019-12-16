package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Colors {
		@Id
		@GeneratedValue
		long id;
		@Column(unique = true)
		String color;
		@JsonIgnore
		@OneToMany(mappedBy = "noteColor")
		List<Note> notes ;
		@JsonIgnore
		@OneToMany(mappedBy = "notebookColor")
		List<Notebook> notebooks ;
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public List<Note> getNotes() {
			return notes;
		}
		public void setNotes(List<Note> notes) {
			this.notes = notes;
		}
		public List<Notebook> getNotebooks() {
			return notebooks;
		}
		public void setNotebooks(List<Notebook> notebooks) {
			this.notebooks = notebooks;
		}
		
		
}
