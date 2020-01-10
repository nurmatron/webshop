// @ts-ignore
import {Component, Input, OnInit} from '@angular/core';
import {Article} from "../../models/article.model";
import {CustomerService} from "../../services/customer.service";
import {ArticleService} from "../../services/article.service";
import {Category} from "../../models/category.enum";

// @ts-ignore
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
  searchArticleByName: string;
  searchArticleByCategory: string = "ALL";
  orderPlacedSuccessfully: boolean;
  orderPlaced: boolean;
  categoryOptions = Object.values(Category);
  pageToShow: string = 'shopping-list';

  constructor(private customerService:CustomerService, private articleService : ArticleService) {
    this.categoryOptions.push("ALL");
  }

  ngOnInit() {
    this.customer.basket = [];
    this.orderPlaced = false;
    this.orderPlacedSuccessfully = false;
    this.articleService.getAllArticles().subscribe(data => {
      if(data != null) {
        this.articleList = data;
        this.articleListToDisplay = data;
      }
    });
  }

  searchArticles() {
    this.articleListToDisplay = this.articleList.filter(article => article.name.toLowerCase().includes(this.searchArticleByName.toLocaleLowerCase()));
}
  searchArticlesByCategory(){
    if(this.searchArticleByCategory.includes("ALL")) {
      this.articleListToDisplay = this.articleList;
    } else {
      this.articleListToDisplay = this.articleList.filter(article =>
        article.category.toLowerCase().includes(this.searchArticleByCategory.toLocaleLowerCase()));
    }
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
    if(this.customer.basket.length > 0) {
      this.customerService.checkout(this.customer).subscribe(data => {
        this.orderPlacedSuccessfully = data;
        this.orderPlaced = true;
        if (data) {
          window.alert("Your order has been placed successfully");
        }
      });
    } else {
      window.alert("Your basket is empty, nothing to checkout!")
    }
  }

}
