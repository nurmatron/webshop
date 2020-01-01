import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../../services/employee.service";

@Component({
  selector: 'app-employee-login',
  templateUrl: './employee-login.component.html',
  styleUrls: ['./employee-login.component.css']
})
export class EmployeeLoginComponent implements OnInit {
  loggedIn: boolean;
  failedLoginMessage: string;
  id : number;
  password : string;
  constructor(private employeeService : EmployeeService) {
    this.loggedIn = false;
  }

  ngOnInit() {
  }

  onSubmit(){
    console.log(this.id, this.password, this.loggedIn, this.failedLoginMessage);
  this.employeeService.login(this.id, this.password).subscribe(data=>{
    this.loggedIn = data;
    console.log(data)
    if(this.loggedIn){
      this.failedLoginMessage ="";
    }
  },error => {
    this.failedLoginMessage = "Login failed, id and password didn't match any existing employee";

  })
  }
}
