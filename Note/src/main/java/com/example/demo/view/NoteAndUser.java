package com.example.demo.view;

public class NoteAndUser {
		private NoteViewModle noteViewModel;
		private UserViewModel userViewModel ;
		
		
		public NoteViewModle getNoteViewModel() {
			return noteViewModel;
		}
		public void setNoteViewModel(NoteViewModle notebookViewModel) {
			this.noteViewModel = notebookViewModel;
		}
		public UserViewModel getUserViewModel() {
			return userViewModel;
		}
		public void setUserViewModel(UserViewModel userViewModel) {
			this.userViewModel = userViewModel;
		}
		
}
