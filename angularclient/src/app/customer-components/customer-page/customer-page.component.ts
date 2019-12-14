import {Component, Input, OnInit} from '@angular/core';
import {Article} from "../../models/article.model";
import {CustomerService} from "../../services/customer.service";

@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.css']
})
export class CustomerPageComponent implements OnInit {

  @Input() customer;
  articleList: Article[] = [];
  articleListToDisplay: Article[] = [];
  basketPrice: number = 0;
  searchArticle: string;
  orderPlacedSuccessfully: boolean;

  constructor(private customerService:CustomerService) {

  }

  ngOnInit() {
    this.customer.basket = [];
    this.customerService.getAllArticles().subscribe(data => {
      if(data != null) {
        this.articleList = data;
        this.articleListToDisplay = data;
      }
    });
  }

  searchArticles() {
    this.articleListToDisplay = this.articleList.filter(article => article.name.toLowerCase().includes(this.searchArticle.toLocaleLowerCase()));
  }

  subQuantity(article: Article) {
    article.quantity--;
  }
  addQuantity(article: Article) {
    article.quantity++;
  }
  addToBasket(article: Article) {
    let basketArticle = new Article();
    basketArticle.id = article.id;
    basketArticle.name = article.name;
    basketArticle.quantity = article.quantity;
    basketArticle.price = article.price;
    this.customer.basket.push(basketArticle);
    for(let i = 0; i<basketArticle.quantity; i++) {
      this.basketPrice += basketArticle.price;
    }
    article.quantity=1;
  }

  removeFromBasket(article: Article) {
    for(let i = 0; i<article.quantity; i++) {
      this.basketPrice -= article.price;
    }
    let index = this.customer.basket.indexOf(article);
    this.customer.basket.splice(index, 1);
  }

  addBasketQuantity(article){
    article.quantity++;
    this.basketPrice += article.price;
  }

  subBasketQuantity(article) {
    article.quantity--;
    this.basketPrice -= article.price;
  }

  placeOrder() {
    this.customerService.checkout(this.customer).subscribe(data => {
      this.orderPlacedSuccessfully = data;
    });
  }

}
