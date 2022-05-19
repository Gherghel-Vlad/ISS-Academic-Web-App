import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder} from '@angular/forms'
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  public loginForm !: FormGroup;
  constructor(private formBuilder : FormBuilder, private http : HttpClient, private router : Router) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group ({
      username: [''],
      password: ['']
    })
  }

  async login() { 
    const typeUser = ["student", "teacher", "admin"];
    var found = false;
    let newUser;

    this.http.get<IUser>(`http://localhost:8080/user/${this.loginForm.value.username}`)
          .subscribe((v:IUser) => {
            if (v.role == "null") {
              alert("Login failed");
            }
            else {
              alert(`login as ${v.role} successful`);
              this.loginForm.reset();
              this.router.navigate([`${v.role.toLocaleLowerCase()}-dashboard`]);
            }
          })
  }

}

// TO DO: make sure it's the whole user
export default interface IUser {
  role: string
}

export async function http(request: RequestInfo): Promise<any> {
  // returns the respone to the http request
  const response = await fetch(request);
  const body = await response.json();
  return body;
}

