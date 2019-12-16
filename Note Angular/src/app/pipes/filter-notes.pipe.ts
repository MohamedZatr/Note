import { Pipe, PipeTransform } from '@angular/core';
import { Note } from '../model/Note';

@Pipe({
  name: 'filterNotes'
})
export class FilterNotesPipe implements PipeTransform {

  transform(Notes: Note[], searchText:string): Note[] {
      if(searchText == null || searchText == "")
        return Notes;
    
        return Notes.filter(note => note.title.includes(searchText) || note.text.includes(searchText))
  }

}
