import {Article} from "./article.model";
import {Customer} from "./customer.model";
import {OrderLine} from "./orderLines.model";

export class Order {
  id: number;
  customer : Customer;
  orderLines : OrderLine[];
  expedited : boolean;
}



