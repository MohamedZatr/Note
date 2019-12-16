import { Injectable } from '@angular/core';
import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Notebook } from '../model/Notebook';
import { FeedBackView } from '../feedback/feedback.component';
import { Note } from '../model/Note';
import { Colors } from '../model/Colors';
import { User } from '../model/User';
import { UserAndNoteBook } from '../model/UserAndNoteBook';
import { NoteAndUser } from '../model/NoteAndUser';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
    private BASE_URL = "http://localhost:8080/api";
    private URL_ALL_NOTEBOOK = `${this.BASE_URL}/notebook/all`;
    private URL_SEND_FEED_BACK = `${this.BASE_URL}/feedback/send`;
    private URL_SAVE_UPDATE_NOOTBOOK = `${this.BASE_URL}/notebook/add`;
    private URL_DELETE_NOTEBOOK = `${this.BASE_URL}/notebook/delete/`;
    private URL_GET_ALL_NOTES = `${this.BASE_URL}/note/all`
    private URL_GET_NOTES_BY_NOTEBOOK = `${this.BASE_URL}/note/byNoteBook/`;
    private URL_DELETE_NOTE_BY_ID = `${this.BASE_URL}/note/deltebyId/`;
    private URL_ADD_NEW_NOTE = `${this.BASE_URL}/note/add`;
    private URL_GET_ALL_COLORS = `${this.BASE_URL}/color/all`
    private URL_LOGIN = `${this.BASE_URL}/user/login`;
    private URL_REGISTER = `${this.BASE_URL}/user/register`;
    private URL_LOGOUT = `${this.BASE_URL}/user/logout`;
  constructor(private http:HttpClient) {

   }

   getAllNotebookes(user:User):Observable<Notebook[]>
   {
    const httpheaders= this.getHttpHeaders(user); 
    return this.http.post<Notebook[]>(this.URL_ALL_NOTEBOOK,user,{headers:httpheaders});
   }

   postFeeback(user:User,feed :FeedBackView) :Observable<any>
   {
    const httpheaders= this.getHttpHeaders(user); 
    return this.http.post(this.URL_SEND_FEED_BACK,feed,{headers:httpheaders});
   }

   saveOrUpdateNotebook(notebook:Notebook,user:User):Observable<Notebook>
   {
  let obj:UserAndNoteBook={
    notebookViewModel:notebook,
    userViewModel:user
  }
      const httpheaders= this.getHttpHeaders(user); 
      return this.http.post<Notebook>(this.URL_SAVE_UPDATE_NOOTBOOK,obj,{headers:httpheaders}); 
   }

   deleteNotebook(id:string,user:User):Observable<any>
   {
    const httpheaders= this.getHttpHeaders(user); 
     return this.http.delete(this.URL_DELETE_NOTEBOOK+id,{headers:httpheaders});
   }

   getAllNote(user:User):Observable<Note[]>
   {
          const httpheaders= this.getHttpHeaders(user); 
          return this.http.post<Note[]>(this.URL_GET_ALL_NOTES,user,{headers:httpheaders});
   }
   getAllNoteByNotebook(notebook:string,user:User):Observable<Note[]>
   {
      const httpheaders= this.getHttpHeaders(user); 
     return this.http.get<Note[]>(this.URL_GET_NOTES_BY_NOTEBOOK + notebook,{headers:httpheaders});
   }

   deleteNoteById(id:string,user:User):Observable<any>
   {
      const httpheaders= this.getHttpHeaders(user); 
      return this.http.delete(this.URL_DELETE_NOTE_BY_ID + id,{headers:httpheaders});
   }

   addOrUpdateNote(note:Note,user:User):Observable<Note>
   {
        let noteAndUser:NoteAndUser ={
          noteViewModel:note,
          userViewModel:user
        }
        const httpheaders= this.getHttpHeaders(user); 
        return this.http.post<Note>(this.URL_ADD_NEW_NOTE,noteAndUser,{headers:httpheaders});
   }

   getAllColors(user:User):Observable<Colors[]>
   {
      const httpheaders= this.getHttpHeaders(user); 
      return this.http.get<Colors[]>(this.URL_GET_ALL_COLORS,{headers:httpheaders});
   }

   getHttpHeaders(user:User):HttpHeaders
   {
    let email = user.email;
    let password = user.password;

    return new HttpHeaders({
      'Content-Type': 'application/json',
      authorization : 'Basic ' + btoa(email + ':' + password)});
   }

   loginByUserAndPassword(user:User):Observable<User>
   {
      const httpheaders= this.getHttpHeaders(user); 
      return this.http.post<User>(this.URL_LOGIN,user,{headers:httpheaders});
   }

   registration(user:User):Observable<User>
   {
       return  this.http.post<User>(this.URL_REGISTER,user);
   }
  logout(user:User):Observable<any>
  {
    const httpheaders= this.getHttpHeaders(user); 
   return this.http.get(this.URL_LOGOUT,{headers:httpheaders});
  }

}
