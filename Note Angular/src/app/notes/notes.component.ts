import { Component, OnInit, HostListener } from '@angular/core';
import { Notebook } from '../model/Notebook';
import { ApiService } from '../services/api.service';
import { Note } from '../model/Note';
import { Colors } from '../model/Colors';
import { Router } from '@angular/router';
import { User } from '../model/User';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {
Notebooks:Notebook[] =[];
Notes:Note[] =[];
lastNotebookClicked:Notebook;
defultecolor:string ="bg-warning"
colors:Colors[] =[];
searchWord:string;
private user:User ={
  id:'',
  name:'',
  password:'',
  email:'',
  type:'',
  numOfNotebooks:0,
  numOfNotes:0,
}
constructor(private apiServices:ApiService,private router:Router) { }
  edit:boolean = false;
  ngOnInit() {
    if(localStorage.getItem("email") === null || localStorage.getItem("email") === "" ||localStorage.getItem("email").length <= 0 ){
        this.router.navigate(['']);        
      }else{
    this.user.email = localStorage.getItem("email");
    this.user.password = localStorage.getItem("password");
    this.getAllNotebook()
    this.getAllNotes();
    this.getAllColors();
    
  }
  }
  ////************** Get All Notebook *****************/ 
  public getAllNotebook(){
    console.log(this.user.email)
    this.apiServices.getAllNotebookes(this.user).subscribe(
      res => {
        this.Notebooks = res;
      },
      err => {
        alert("An error is occurred");
      }
    );
  }
    ////************** Create New Notebook *****************/ 
  createNewNotebook(){
    let newNotebook:Notebook={
      name:'New NoteBook',
      id:null,
      numOfNote:null,
      colorNotebook:this.defultecolor,
      userEmail:this.user.email

    }
    this.apiServices.saveOrUpdateNotebook(newNotebook,this.user).subscribe(
        res =>{
            newNotebook.id = res.id;
            newNotebook.numOfNote = res.numOfNote;
            this.Notebooks.push(newNotebook); 
        },
        err =>{
            alert("Ther is an error Occuer Happen");
        }
    );
  }
    ////************** Upate Notebook *****************/ 
  updateNotebook(updateNotebook){
    this.apiServices.saveOrUpdateNotebook(updateNotebook,this.user).subscribe(
      res =>{
      },
      err =>{
          alert("Ther is an error Occuer Happen");
      }
  );
  }
    ////************** Delete  Notebook *****************/ 
  deleteNotebook(notebook){
    if(confirm("Are you want to delete the note book called "+notebook.name)){    
    this.apiServices.deleteNotebook(notebook.id,this.user).subscribe(
      res=>{
        let index = this.Notebooks.indexOf(notebook);
        this.Notebooks.splice(index,1);
        location.reload();
      },
      err =>{
        alert("there are errors");
      }
    )
  }
  }

getNoteByNotebook(notebook:Notebook){
  this.lastNotebookClicked = notebook;
  this.apiServices.getAllNoteByNotebook(notebook.id,this.user).subscribe(
    res=>{
      this.Notes = res;
    },
    err=>{
      alert("There Are Erro Happen");
    }
  )
}


////************** Get All Notes *****************/ 
getAllNotes(){
  this.apiServices.getAllNote(this.user).subscribe(
    res =>{
      this.Notes = res;
    },
    err => {
      alert("there are errors");
    }
  )
  this.lastNotebookClicked = null;
}
getNotesByNotebook(notebook:string){
  this.apiServices.getAllNoteByNotebook(notebook,this.user).subscribe(
    res=>{
        this.Notes = res;
    },
    err=>{
      alert("there are errors");
    }
  )
}
deleteNote(note:Note){
  this.apiServices.deleteNoteById(note.id,this.user).subscribe(
    res=>{
      let index:number = this.Notes.indexOf(note); 
      this.Notes.splice(index,1);
      if(this.searchWord.length > 0)
      {
        location.reload();
      }
    },
    err=>{
      alert("there are errors");
    }
  )
}
addNewNote( ){
  if(this.lastNotebookClicked == null){
      alert("Plese Click on Notebook to Create Note")
  }else{
        let Newnote:Note={
            id:null,
            title:"New Note",
            text:"New text Note",
            noteBookId:this.lastNotebookClicked.id,
            data: new Date().toString(),
            noteColor:this.defultecolor
        }
      this.apiServices.addOrUpdateNote(Newnote,this.user).subscribe(
        res=>{
           Newnote.id = res.id;
           this.Notes.push(Newnote);
        },
        err=>{
            alert("there are errors");
        }
      )

  } 
}

updateNote(note:Note){
  if(note.text !== "" && note.title!==""){
  this.apiServices.addOrUpdateNote(note,this.user).subscribe(
    res=>{

    },
    err=>{
      alert("There are Error occured");
    }
  )
}
}
getAllColors(){
  this.apiServices.getAllColors(this.user).subscribe(
    res=>{
        this.colors = res;
    },
    err=>{
      alert(err);
    }
  )
}
colorOfNoteBook(value){
console.log(value);
if(this.lastNotebookClicked.colorNotebook !== value ){
      this.lastNotebookClicked.colorNotebook = value;
      this.updateNotebook(this.lastNotebookClicked);
}
}
colorOfNote(note:Note){
  this.updateNote(note);
}

}
