package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Response.MessageResponse;
import com.example.demo.services.NoteService;
import com.example.demo.view.NoteAndUser;
import com.example.demo.view.NoteViewModle;
import com.example.demo.view.UserViewModel;

@RestController
@RequestMapping("/api/note")
@CrossOrigin
public class NoteController {

	@Autowired
	NoteService noteService;
	
	@PostMapping("/all")
	public List<NoteViewModle> getAllNote(@RequestBody UserViewModel model){
		
		return this.noteService.getAllNotes(model);
	}
	
	@GetMapping("/byId/{id}")
	public NoteViewModle getNotreById(@PathVariable("id") Long id){
			return this.noteService.getNoteById(id);
	}
	
	@GetMapping("/byNoteBook/{notebookId}")
	public List<NoteViewModle> getNotesByNoteBook(@PathVariable("notebookId") Long id) 
	{	
		return this.noteService.getNotesByNotebook(id);
	}
	
	@PostMapping("/add")
	public NoteViewModle save(@RequestBody NoteAndUser modle)
	{	
		NoteViewModle noteViewModle = modle.getNoteViewModel();
		UserViewModel userViewModel = modle.getUserViewModel();
		
		return this.noteService.saveOrUpdateNote(noteViewModle,userViewModel);
	}
	
	@DeleteMapping("/deltebyId/{id}")
	public MessageResponse deleteNoteById(@PathVariable("id") Long id){
		return this.noteService.deleteNoteById(id);
	}
	
}
