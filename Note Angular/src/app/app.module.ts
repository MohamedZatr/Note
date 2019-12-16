import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { NotesComponent } from './notes/notes.component';
import { NotfoundComponent } from './notfound/notfound.component';
import {Routes, RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NoteComponent } from './notes/note/note.component';
import { FilterNotesPipe } from './pipes/filter-notes.pipe';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
const appRoutes: Routes=[
  {
    path:"notes",
    component:NotesComponent
  },
  {
    path:'registration',
    component:RegisterComponent,
  },
  {
    path:"feedback",
    component:FeedbackComponent
  },
  {
    path:'',
    component:LoginComponent,
    pathMatch:"full"
  },
  {
    path:'login',
    component:LoginComponent
  },
  {
    path:"**",
    component:NotfoundComponent
  }
];


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    FeedbackComponent,
    NotesComponent,
    NotfoundComponent,
    NoteComponent,
    FilterNotesPipe,
    LoginComponent,
    RegisterComponent,
    
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
   
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
