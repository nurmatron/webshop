import {Article} from "./article.model";
import {Order} from "./order.model";

export class OrderLine {
  id: number;
  order: Order;
  article : Article;

  constructor() {

  }
}
