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
        if (order.expedited == true) {
          this.expitadedOrderList.push(order);
        } else {
          this.unExpitadedOrderList.push(order);
        }
      })
    })

  }

  expedite(id : number){
    this.orderService.expediteOrder(id).subscribe(data=>{
      if (data != null){
        this.populateOrderLists();
      }
    });
  }
}
