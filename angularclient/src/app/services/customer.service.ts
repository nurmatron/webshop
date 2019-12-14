import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../models/customer.model";
import {Article} from "../models/article.model";

@Injectable()
export class CustomerService {

  private customerUrl: string;
  private articleUrl: string;

  constructor(private http: HttpClient) {
    this.customerUrl = "http://localhost:8080/api/customer";
    this.articleUrl = "http://localhost:8080/api/article";
  }

    public login(name: string, password: string): Observable<Customer> {
    let url = this.customerUrl + '/login/' + name + '/' + password;
      return this.http.get<Customer>(url);
    }

    public getAllArticles(): Observable<Article[]> {
      return this.http.get<Article[]>(this.articleUrl);
    }

    public checkout(customer: Customer): Observable<boolean> {
    let url = this.customerUrl + '/checkout/' + customer.id;
    return this.http.post<boolean>(url, customer.basket);
    }
}
