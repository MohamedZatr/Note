import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';
import { ApiService } from '../services/api.service';
import { LoginComponent } from '../login/login.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
user:User={
  id:'',
  name:'',
  password:'',
  email:'',
  type:'',
  numOfNotebooks:0,
  numOfNotes:0
}

  constructor(private apiservice:ApiService,private router:Router) { }

  ngOnInit() {
  }
  register(){
    this.apiservice.registration(this.user).subscribe(
      res=>{
        this.router.navigate(['']);
      },
      err=>{
        alert("there are Erorrs happen")
      }
    );
  }
}
