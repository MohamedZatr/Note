import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 user:User ={
  id:'',
  name:'',
  password:'',
  email:'',
  type:'',
  numOfNotebooks:0,
  numOfNotes:0,
}
error:boolean = false;

  constructor(private apiServices:ApiService,private router:Router) {}

  ngOnInit(){
  }

login(){
  this.apiServices.loginByUserAndPassword(this.user).subscribe(
    res=>{
      
      localStorage.setItem("email",this.user.email);
      localStorage.setItem("password",this.user.password);
      this.router.navigate(['/notes']);
    },
    err=>{
      this.error = true;
        this.router.navigate(['login']);
    }
  )
}

}
