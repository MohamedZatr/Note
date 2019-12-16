import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { User } from '../model/User';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
  modelfeed:FeedBackView ={
    email:localStorage.getItem("email"),
    feedback:''
  };

  user:User ={
    id:'',
    name:'',
    password:localStorage.getItem("password"),
    email:localStorage.getItem("email"),
    type:'',
    numOfNotebooks:0,
    numOfNotes:0,
  }

  constructor(private apiSevices:ApiService, private router:Router) { }

  ngOnInit() {
    if(localStorage.getItem("email") == null)
    this.router.navigate(['']);
  }

  sendfeed(){
    this.apiSevices.postFeeback(this.user,this.modelfeed).subscribe(
        res =>{
          location.reload();
        },
        err=>{
          alert("An Error has occurred while sending Feedback pleas try in other time");
        }

    );
  }

}

export interface FeedBackView{
  email:string;
  feedback:string;
}
