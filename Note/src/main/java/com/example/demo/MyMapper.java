package com.example.demo;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.Repository.ColorRepository;
import com.example.demo.Repository.NoteBookRepository;
import com.example.demo.Repository.NoteRepository;
import com.example.demo.Repository.UserRepositry;
import com.example.demo.model.Colors;
import com.example.demo.model.Note;
import com.example.demo.model.Notebook;
import com.example.demo.model.User;
import com.example.demo.view.ColorViewModel;
import com.example.demo.view.NoteViewModle;
import com.example.demo.view.NotebookViewModel;
import com.example.demo.view.UserViewModel;
@Component
public class MyMapper {
	@Autowired
	NoteBookRepository bookRepository;
	private final String defultColor = "bg-warning";
	@Autowired
	ColorRepository colorRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepositry userRepositry;
	
	public NoteViewModle convertToNodeViewModel(Note entity) {
		NoteViewModle modle = new NoteViewModle();
		modle.setId(String.valueOf(entity.getId()));
		modle.setTitle(entity.getTitle());
		modle.setText(entity.getText());
		modle.setNoteBookId(String.valueOf(entity.getNotebook().getId()));
		modle.setData(entity.getLastModifiedOn().toString());
		modle.setNoteColor(entity.getNoteColor().getColor());
		return modle;
	}
	
	public Note convertToNodeEntity(NoteViewModle viewModle) throws ParseException {
		Note note = new Note();
		if(viewModle.getId() != null)
		note.setId(Long.parseLong(viewModle.getId()));
		note.setTitle(viewModle.getTitle());
		note.setText(viewModle.getText());
		if(viewModle.getNoteColor() != null)
		{
			note.setNoteColor(colorRepository.findByColor(viewModle.getNoteColor()));
		}else {
			note.setNoteColor(colorRepository.findByColor(defultColor));
		}
		 
		SimpleDateFormat format = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		String dateString = format.format(new Date());
		Date   date       = format.parse (dateString);
		note.setLastModifiedOn(date);
		//note.setLastModifiedOn(new SimpleDateFormat().parse();
		
		Notebook notebook = bookRepository.findById(Long.parseLong(viewModle.getNoteBookId())).orElse(new Notebook());
		note.setNotebook(notebook);
		return note;
	}
	
	
	public NotebookViewModel convertToNotebookViewModel(Notebook entity)
	{
		NotebookViewModel viewModel = new NotebookViewModel();
		viewModel.setId(String.valueOf((entity.getId())));
		viewModel.setName(entity.getName());
		if(entity.getNotes() != null)
		viewModel.setNumOfNote(entity.getNotes().size());
		viewModel.setColorNotebook(entity.getNotebookColor().getColor());
		return viewModel;
	}
	
	public Notebook convertToNotebookEntity(NotebookViewModel model) {
		Notebook notebook = new Notebook();
		if(model.getId() != null)
		notebook.setId(Long.parseLong(model.getId()));
		notebook.setName(model.getName());
		User user = userRepositry.findByEmail(model.getUserEmail());
		notebook.setUserNotebook(user);
		if(model.getColorNotebook() != null)
		{
			notebook.setNotebookColor(colorRepository.findByColor(model.getColorNotebook()));
		}else {
			notebook.setNotebookColor(colorRepository.findByColor(defultColor));
		}
		return notebook;
	}
	
	public Colors convertToColorsEntity (ColorViewModel colorViewModel){
		Colors colors = new Colors();
		if(colorViewModel.getId() != null)
		colors.setId(Long.parseLong(colorViewModel.getId()));
		colors.setColor(colorViewModel.getName());
		return colors;
	}
	public ColorViewModel convertToColorsViewModel(Colors entity) {
		ColorViewModel model = new ColorViewModel();
		model.setId(String.valueOf(entity.getId()));
		model.setName(entity.getColor());
		return model;
	}
	
	public User convertToUserEntity(UserViewModel model) {
		User user = new User();
		if(!model.getId().isEmpty() && model.getId() != null) {
			user.setId(Long.parseLong(model.getId()));
			user.setNotebooks(bookRepository.findByUserNotebook_Id(Long.parseLong(model.getId())));
			user.setNotes(noteRepository.findByUserNote_Id(Long.parseLong(model.getId())));
		}
		user.setUserName(model.getName());
		user.setUserPassword(encoder.encode(model.getPassword()));
		user.setEmail(model.getEmail());
		user.setType("User");
		
		return user;
	}
	
	public UserViewModel  convertToUserViewModel(User user) {
		UserViewModel model = new UserViewModel();
		model.setId(user.getId().toString());
		model.setName(user.getUserName());
		model.setPassword(encoder.encode(user.getUserPassword()));
		model.setEmail(user.getEmail());
		model.setType(user.getType());
		if(user.getNotebooks() != null)
		model.setNumOfNotebooks(user.getNotebooks().size());
		if(user.getNotes() != null)
		model.setNumOfNotes(user.getNotes().size());
		return model;
	}

}
