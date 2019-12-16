import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Note } from 'src/app/model/Note';
import { Colors } from 'src/app/model/Colors';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {
@Input("myNote") note:Note;
@Input("myColors") colors:Colors[];

@Output("myUpdate") update:EventEmitter<Note> = new EventEmitter();
@Output("myDelete") delete:EventEmitter<Note> = new EventEmitter();
@Output("myChangeColor") changeColor:EventEmitter<Note> = new EventEmitter();
  constructor() { 
  }

  ngOnInit() {
  }
updateNote(){
    this.update.emit(this.note);
  }
deleteNote(){
  this.delete.emit(this.note);
}
colorOfNote(val:string,){
  this.note.noteColor = val;
  this.changeColor.emit(this.note);
}


}
