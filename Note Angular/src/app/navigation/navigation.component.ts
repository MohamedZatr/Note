import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { User } from '../model/User';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  user:User ={
    id:'',
    name:'',
    password:'',
    email:'',
    type:'',
    numOfNotebooks:0,
    numOfNotes:0,
  }
  constructor(private apiServices:ApiService,private router:Router) {}

  ngOnInit() {
  }
  logout(){
    this.user.email = localStorage.getItem("email");
    this.user.password = localStorage.getItem("password");
    
    this.apiServices.logout(this.user).subscribe(
      res=>{
          localStorage.clear();
          this.router.navigate(['']);
      },
      err=>{
        alert("There are erro")
      }
  
    );
  }
}
