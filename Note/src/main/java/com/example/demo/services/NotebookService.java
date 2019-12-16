package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.MyMapper;
import com.example.demo.Repository.NoteBookRepository;
import com.example.demo.Repository.UserRepositry;
import com.example.demo.Response.MessageResponse;
import com.example.demo.exception.ExceptionFiledRequired;
import com.example.demo.exception.NotfoundResult;
import com.example.demo.model.Notebook;
import com.example.demo.model.User;
import com.example.demo.view.NotebookViewModel;
import com.example.demo.view.UserViewModel;

@Service
public class NotebookService {


	@Autowired
	NoteBookRepository bookRepository;
	
	
	@Autowired
	MyMapper mapper;
	
	@Autowired
	UserRepositry userRepositry;
	/*
	 *  this Function get All notebook From data base;
	 *  return List Of NotebookViewModle
	*/
	public List<NotebookViewModel> getAllNoteBooks(UserViewModel model){
		User user = userRepositry.findByEmail(model.getEmail());
		List<Notebook> notebooks = this.bookRepository.findByUserNotebook_Id(user.getId());
		return notebooks.stream().
			   map(notebook ->mapper.convertToNotebookViewModel(notebook)).
			   collect(Collectors.toList());
	}
	
	/* 
	 * this function Save or update data in database
	 * parameter (object of NotebookViewModel) 
	 * 
	 * return object of NotebookViewModel After  Saved
	 */
	
	public NotebookViewModel SaveOrUpdateNotebook(NotebookViewModel model,UserViewModel userViewModel) {
		User user  =  userRepositry.findByEmail(userViewModel.getEmail()); 
		if(model.getName() == null)
		{
			throw new ExceptionFiledRequired("Notebook name is required");
		}
		Notebook notebook = mapper.convertToNotebookEntity(model);
		notebook.setUserNotebook(user);
		bookRepository.save(notebook);
		System.out.println(notebook.getId()+" "+notebook.getName());
		return mapper.convertToNotebookViewModel(notebook);	
	}
	
	/*
	 * this function delete Notebook from data base
	 * parameter take the id of notebook 
	 * return message if success 
	 * throw exception if there are error
	 */
	
	public MessageResponse deleteNotebooK(Long notebookId) {
		if(this.bookRepository.existsById(notebookId))
		{
			this.bookRepository.deleteById(notebookId);
			return new MessageResponse("Delete Success", true);
		}
		throw new NotfoundResult("Cannot Delete note with given id");
 
	}
	
	
	
	
	
	
}
