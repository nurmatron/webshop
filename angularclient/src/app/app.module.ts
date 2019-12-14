import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerLoginComponent } from './customer-components/customer-login/customer-login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CustomerService} from "./services/customer.service";
import {HttpClientModule} from "@angular/common/http";
import { CustomerPageComponent } from './customer-components/customer-page/customer-page.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerLoginComponent,
    CustomerPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [CustomerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
