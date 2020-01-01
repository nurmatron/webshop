import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Order} from "../models/order.model";
import {Customer} from "../models/customer.model";

@Injectable()
export class EmployeeService {
  private orderLineUrl: string;
  private orderUrl: string;
  private customerUrl : string;
  private articleUrl : string;
  private employeeUrl : string;

  constructor(private http: HttpClient) {
    this.orderLineUrl = "http://localhost:8080/api/orderline";
    this.orderUrl = "http://localhost:8080/api/order";
    this.customerUrl = "http://localhost:8080/api/customer";
    this.articleUrl = "http://localhost:8080/api/article";
    this.employeeUrl = "http://localhost:8080/api/employee";
  }
  public getAllOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.orderUrl);
  }
  public login(id : number, password: string): Observable<boolean> {
    let url = this.employeeUrl + '/login/' + id + '/' + password;
     return this.http.get<boolean>(url);
  }
}
