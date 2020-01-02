import {Component, OnInit} from '@angular/core';
import {Article} from "../../models/article.model";
import {Order} from "../../models/order.model";
import {ArticleService} from "../../services/article.service";

@Component({
  selector: 'app-employee-page',
  templateUrl: './employee-page.component.html',
  styleUrls: ['./employee-page.component.css']
})
export class EmployeePageComponent implements OnInit {
  searchArticle: string;
  articleList: Article[] = [];
  articleListToDisplay: Article[] = [];
  orderList: Order[];
  orderListToDisplay: Order[];
  showCreateArticle: boolean;
  newArticleName: string;
  newArticlePrice: number;

  constructor(private articleService: ArticleService) {
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

  searchArticles() {
    this.articleListToDisplay = this.articleList.filter(article => article.name.toLowerCase().includes(this.searchArticle.toLocaleLowerCase()));
  }

  createNewArticle() {
    this.articleService.createArticle(this.newArticleName, this.newArticlePrice).subscribe(data => {
      if (data != null) {
        this.populateArticleList();
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
