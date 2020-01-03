import { Component, OnInit } from '@angular/core';
import {Customer} from "../../models/customer.model";
import {CustomerService} from "../../services/customer.service";

@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css']
})
export class CustomerLoginComponent implements OnInit {

  customer: Customer;
  loggedIn: boolean;
  failedLoginMessage: string;
  newName: string ="";
  newPassword: string ="";
  newEmail: string ="";
  showCreateCustomer: boolean = false;

  constructor(private customerService: CustomerService) { this.customer = new Customer(); }

  ngOnInit() {
  }

  onSubmit() {
    this.customerService.login(this.customer.name, this.customer.password).subscribe(data => {
      if(data != null) {
        this.customer = data;
        this.loggedIn = true;
        this.failedLoginMessage = "";
      } else {
        this.loggedIn = false;
        this.customer.name = "";
        this.customer.password = "";
        this.failedLoginMessage = "Login failed, name and password didn't match any existing customer";
      }
    });
  }

  createCustomer() {
    let newCustomer: Customer = new Customer();
    newCustomer.name = this.newPassword;
    newCustomer.password = this.newPassword;
    newCustomer.mail = this.newEmail;
    this.customerService.createCustomer(newCustomer).subscribe(data => {
      if(data != null) {
        if(confirm("Sign up was successful! Do you want to login now?")) {
          this.customer.name = this.newName;
          this.customer.password = this.newPassword;
          this.onSubmit();
        }
        this.newName = "";
        this.newPassword = "";
        this.newEmail = "";
        this.showCreateCustomer = false;
      }
    });
  }


}
