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
  orderList: Order[];

  constructor(private orderService: OrderService) {
  }

  ngOnInit() {
    this.orderList = [];
    this.orderService.getAllOrders(this.customer.id).subscribe(data => {
      data.forEach(order => { // för varje order id
        this.orderService.getOneOrder(order.id).subscribe(orderData => {
          let orderLines = [];
          this.orderService.getAllOrderLines(order.id).subscribe(data => {
            orderLines = data;
            console.log(orderLines, " i am orderlines");
            for (let i = 0; i < orderLines.length; i++) {
              this.orderService.getArticleForOrderLine(orderLines[i].id).subscribe(data => {
                orderLines[i].article = data;
                console.log("i am data after article", orderLines[i])
              })
            }
            order.orderLines = orderLines;
            console.log("i am order.orderlines", order)
            this.orderList.push(order);
            console.log(this.orderList, " after push ")

          });

          //  this.orderList.push(orderData);
          console.log(orderData, " i am order data")
        })
        //  this.orderList.push(order);
      });
      console.log(data, "")
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
