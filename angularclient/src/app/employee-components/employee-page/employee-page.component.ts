import {Component, OnInit} from '@angular/core';
import {Article} from "../../models/article.model";
import {Order} from "../../models/order.model";
import {ArticleService} from "../../services/article.service";
import {Category} from "../../models/category.enum";

@Component({
  selector: 'app-employee-page',
  templateUrl: './employee-page.component.html',
  styleUrls: ['./employee-page.component.css']
})
export class EmployeePageComponent implements OnInit {
  searchArticleByName: string;
  searchArticleByCategory: string = "ALL";
  articleList: Article[] = [];
  articleListToDisplay: Article[] = [];
  orderList: Order[];
  orderListToDisplay: Order[];
  showCreateArticle: boolean;
  newArticleName: string ="";
  newArticlePrice: number;
  newArticleCategory : Category = Category.Fruit;
  categoryOptions = Object.values(Category);

  constructor(private articleService: ArticleService) {
    this.categoryOptions.push("ALL");
  }

  ngOnInit() {
    this.populateArticleList();
  }

  private populateArticleList() {
    this.articleService.getAllArticles().subscribe(data => {
      if (data != null) {
        this.articleList = data;
        this.articleListToDisplay = data;
      }
    });
  }

  searchArticlesByName() {
    this.articleListToDisplay = this.articleList.filter(article =>
      article.name.toLowerCase().includes(this.searchArticleByName.toLocaleLowerCase()));
  }
  searchArticlesByCategory(){
    if(this.searchArticleByCategory.includes("ALL")) {
      this.articleListToDisplay = this.articleList;
    } else {
      this.articleListToDisplay = this.articleList.filter(article =>
        article.category.toLowerCase().includes(this.searchArticleByCategory.toLocaleLowerCase()));
    }
  }

  createNewArticle() {
    this.articleService.createArticle(this.newArticleCategory,this.newArticleName, this.newArticlePrice).subscribe(data => {
      if (data != null) {
        console.log(this.newArticleCategory)
        this.populateArticleList();
        this.newArticleName = "";
        this.newArticlePrice = null;
      }
    });
    this.showCreateArticle = false;
  }

  deleteArticle(article: Article) {
    let confirmRemove = confirm("Are you sure you want to delete " + article.name + "from database?");
    if (confirmRemove) {
      this.articleService.deleteArticle(article.id).subscribe(data => {
        console.log(data , " i am data from delete")

        if (data.status == 200) {
          window.alert("Article has been removed.");
          this.populateArticleList();
        }
      })
    }
  }

}
