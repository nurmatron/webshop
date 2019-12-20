import {Component, Input, OnInit} from '@angular/core';
import {installPackage} from "@angular/cli/tasks/install-package";
import {Order} from "../../models/order.model";
import {CustomerService} from "../../services/customer.service";
import {OrderService} from "../../services/order.service";

@Component({
  selector: 'app-previous-orders',
  templateUrl: './previous-orders.component.html',
  styleUrls: ['./previous-orders.component.css']
})
export class PreviousOrdersComponent implements OnInit {
  @Input() customer;
  orderList : Order[];
  constructor(private orderService : OrderService) {

  }

  ngOnInit() {

    // populera orders
    //populera orderLines foreach order

  }

}
