package com.example.demo.services;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.MyMapper;
import com.example.demo.Repository.NoteBookRepository;
import com.example.demo.Repository.NoteRepository;
import com.example.demo.Repository.UserRepositry;
import com.example.demo.Response.MessageResponse;
import com.example.demo.exception.ExceptionFiledRequired;
import com.example.demo.exception.NotfoundResult;
import com.example.demo.model.Note;
import com.example.demo.model.Notebook;
import com.example.demo.model.User;
import com.example.demo.view.NoteViewModle;
import com.example.demo.view.UserViewModel;

@Service
public class NoteService {
	
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	NoteBookRepository notebookRepository;
	
	@Autowired
	UserRepositry userRepositry;
	
	@Autowired
	MyMapper maper;

	public List<NoteViewModle>  getAllNotes(UserViewModel userViewModel) {	
		User user = userRepositry.findByEmail(userViewModel.getEmail());
		List<Note> list = this.noteRepository.findByUserNote_Id(user.getId());
		List<NoteViewModle> 
		noteViewModles = (List<NoteViewModle>) list
											   .stream()
											   .map(note ->maper.convertToNodeViewModel(note))
											   .collect(Collectors.toList());
		return noteViewModles;
	}
	
	public NoteViewModle getNoteById(Long noteId)  {
		Note note = this.noteRepository.findById(noteId).
				orElseThrow(null);
		if(note == null)
		new NotfoundResult("Cannot find note with given id") ;
		NoteViewModle modle = maper.convertToNodeViewModel(note);
		return modle;
	}
	
	public List<NoteViewModle> getNotesByNotebook(Long notebookId) 
	{
		Notebook notebook  = this.notebookRepository.findById(notebookId).orElse(null);
		if(notebook == null)
			   throw new NotfoundResult("Cannot find notebook with given id");
		List<Note> notes = this.noteRepository.
				findAllByNotebook(notebook);
		
		
		return notes.stream().map(note->maper.convertToNodeViewModel(note)).collect(Collectors.toList());
	}
	
	public NoteViewModle saveOrUpdateNote(NoteViewModle noteModle,UserViewModel model) {
		if(noteModle.getNoteBookId() == null || noteModle.getNoteBookId().isEmpty())
		{
			throw new ExceptionFiledRequired("Notebook is required");
		}
		Note note=null;
		try {
			 note  = maper.convertToNodeEntity(noteModle);
			 User user = userRepositry.findByEmail(model.getEmail());
			 note.setUserNote(user);
			 this.noteRepository.save(note);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return this.maper.convertToNodeViewModel(note);
	}
	
	public MessageResponse deleteNoteById(Long nodeId) {
		
		if(noteRepository.existsById(nodeId)) {
			this.noteRepository.deleteById(nodeId);
			return new MessageResponse("Delete Success", true);
		}
		throw new NotfoundResult("Cannot Delete note with given id");
	}
	
	
	
	
	
	
}
