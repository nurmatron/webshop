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

  /*
public getAllArticles(): Observable<Article[]> {
  return this.http.get<Article[]>(this.articleUrl);
}
*/
  public getAllOrders(id: number): Observable<Order[]> {
    return this.http.get<Order[]>(this.orderUrl + "/getall/" + id)
  }

  public getAllOrderLines(id: number): Observable<OrderLine[]> {
    return this.http.get<OrderLine[]>(this.orderLineUrl + "/getall/" + id)
  }

}
