import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerLoginComponent } from './customer-components/customer-login/customer-login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CustomerService} from "./services/customer.service";
import {HttpClientModule} from "@angular/common/http";
import { CustomerPageComponent } from './customer-components/customer-page/customer-page.component';
import { OrderSummaryComponent } from './order-components/order-summary/order-summary.component';
import { PreviousOrdersComponent } from './order-components/previous-orders/previous-orders.component';
import {OrderService} from "./services/order.service";
import { EmployeeLoginComponent } from './employee-components/employee-login/employee-login.component';
import { EmployeePageComponent } from './employee-components/employee-page/employee-page.component';
import {EmployeeService} from "./services/employee.service";
import {ArticleService} from "./services/article.service";
import {DialogModule} from "primeng";
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { EmployeeOrderlistComponent } from './employee-components/employee-orderlist/employee-orderlist.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerLoginComponent,
    CustomerPageComponent,
    OrderSummaryComponent,
    PreviousOrdersComponent,
    EmployeeLoginComponent,
    EmployeePageComponent,
    EmployeeOrderlistComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    DialogModule,
    NoopAnimationsModule,
  ],
  providers: [CustomerService, OrderService , EmployeeService, ArticleService],
  bootstrap: [AppComponent],
})
export class AppModule { }
