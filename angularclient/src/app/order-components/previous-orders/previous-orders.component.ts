import {Component, Input, OnInit} from '@angular/core';
import {installPackage} from "@angular/cli/tasks/install-package";
import {Order} from "../../models/order.model";
import {CustomerService} from "../../services/customer.service";
import {OrderService} from "../../services/order.service";
import {observable, Observable} from "rxjs";
import {forEachComment} from "tslint";
import {OrderLine} from "../../models/orderLines.model";

@Component({
  selector: 'app-previous-orders',
  templateUrl: './previous-orders.component.html',
  styleUrls: ['./previous-orders.component.css']
})
export class PreviousOrdersComponent implements OnInit {
  @Input() customer;
  orderList: Order[] = [];

  constructor(private orderService: OrderService) {
  }

  ngOnInit() {
    this.orderService.getAllOrdersForCustomer(this.customer.id).subscribe(data => {
      data.forEach(order => { // för varje order id
        this.populateOrderAndPushToOrderList(order);
      });
    })
  }

  private populateOrderAndPushToOrderList(order: Order) {
    this.orderService.getOneOrder(order.id).subscribe(orderData => {
      let orderLines = [];
      this.orderService.getAllOrderLines(order.id).subscribe(data => {
        orderLines = data;
        for (let i = 0; i < orderLines.length; i++) {
          this.orderService.getArticleForOrderLine(orderLines[i].id).subscribe(data => {
            orderLines[i].article = data;
          })
        }
        order.orderLines = orderLines;
        this.orderList.push(order);

      });
    })
  }

  totalCostOfOrder(orderLines: OrderLine[]): number {
    let sum = 0;
    orderLines.forEach(orderLine => {
      for (let i = 0; i < orderLine.article.quantity; i++) {
        sum += orderLine.article.price;
      }
    });
    return sum;
  }


}
