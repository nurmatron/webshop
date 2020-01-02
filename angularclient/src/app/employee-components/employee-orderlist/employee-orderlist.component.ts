import {Component, OnInit} from '@angular/core';
import {Order} from "../../models/order.model";
import {OrderService} from "../../services/order.service";

@Component({
  selector: 'app-employee-orderlist',
  templateUrl: './employee-orderlist.component.html',
  styleUrls: ['./employee-orderlist.component.css']
})
export class EmployeeOrderlistComponent implements OnInit {
  expitadedOrderList: Order[] = [];
  unExpitadedOrderList: Order[] = [];

  constructor(private orderService: OrderService) {
  }

  ngOnInit() {
    this.populateOrderLists();
  }

  populateOrderLists() {
    this.expitadedOrderList  = [];
    this.unExpitadedOrderList  = [];
    this.orderService.getAllOrders().subscribe(data => {
      data.forEach(order => {
        console.log("ortder : ", order)
        if (order.expedited == true) {
          console.log(order.expedited, "i am expidated status")
          this.expitadedOrderList.push(order);
        } else {
          this.unExpitadedOrderList.push(order);
        }
      })
    })

  }

  expedite(id : number){
    console.log("Iam happening from expedite");
    this.orderService.expediteOrder(id).subscribe(data=>{
      if (data != null){
        console.log("Iam inside expidite if not null")
        this.populateOrderLists();
      }
    });
  }
}
