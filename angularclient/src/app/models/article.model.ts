import {EnumValue} from "@angular/compiler-cli/src/ngtsc/partial_evaluator";
import {Category} from "./category.enum";

export class Article {
  id: number;
  name: string;
  price: number;
  quantity: number;
  category : Category;
  constructor() {
  }

}
