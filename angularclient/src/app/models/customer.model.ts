import {Article} from "./article.model";

export class Customer {
  id: number;
  name: string;
  password: string;
  mail: string;
  basket: Article[];

  constructor() {
    this.basket = [];
  }
}
