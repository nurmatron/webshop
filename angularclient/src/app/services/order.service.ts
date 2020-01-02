import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Article} from "../models/article.model";
import {HttpClient} from "@angular/common/http";
import {Order} from "../models/order.model";
import {OrderLine} from "../models/orderLines.model";

@Injectable()
export class OrderService {
  private orderLineUrl: string;
  private orderUrl: string;

  constructor(private http: HttpClient) {
    this.orderLineUrl = "http://localhost:8080/api/orderline";
    this.orderUrl = "http://localhost:8080/api/order";
  }

  public getAllOrdersForCustomer(id: number): Observable<Order[]> {
    return this.http.get<Order[]>(this.orderUrl + "/getall/" + id)
  }
  public getAllOrders(){
    return this.http.get<Order[]>(this.orderUrl);
  }

  public getAllOrderLines(id: number): Observable<OrderLine[]> {
    return this.http.get<OrderLine[]>(this.orderLineUrl + "/getall/" + id)
  }
  public getOneOrder(id : number) : Observable<Order>{
    return this.http.get<Order>(this.orderUrl +"/get/" +id)
  }
    public getArticleForOrderLine(id: number) : Observable<Article>{
    return  this.http.get<Article>(this.orderLineUrl + "/getarticle/" + id);
    }
    public expediteOrder(id : number) : Observable<Order>{
    // @ts-ignore
      return this.http.put<Order>(this.orderUrl + "/expedite/" + id);
    }

}
