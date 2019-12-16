package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Response.MessageResponse;
import com.example.demo.services.NotebookService;
import com.example.demo.view.NotebookViewModel;
import com.example.demo.view.UserAndNoteBook;
import com.example.demo.view.UserViewModel;

import javassist.NotFoundException;
@RestController
@RequestMapping("/api/notebook")
@CrossOrigin
public class NoteBookController {

	@Autowired
	NotebookService notebookService;
	
	@PostMapping("/all")
	public List<NotebookViewModel> getAllNoteBook(@RequestBody UserViewModel model){
			
		return this.notebookService.getAllNoteBooks(model);
	}
	
	@PostMapping("/add")
	public NotebookViewModel save(@RequestBody UserAndNoteBook  userAndNotebook)
	{
		NotebookViewModel mViewModel = userAndNotebook.getNotebookViewModel();
		UserViewModel userModel = userAndNotebook.getUserViewModel();
		return  this.notebookService.SaveOrUpdateNotebook(mViewModel,userModel);
	}
	
	@DeleteMapping("/delete/{id}")
	public MessageResponse deleteNoteBook(@PathVariable("id") Long id) throws NotFoundException{
		return this.notebookService.deleteNotebooK(id);
	}
}
