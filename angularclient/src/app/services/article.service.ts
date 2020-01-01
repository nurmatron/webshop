import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Article} from "../models/article.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private articleUrl: string;

  constructor(private http: HttpClient) {
    this.articleUrl = "http://localhost:8080/api/article";
  }

  public getAllArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(this.articleUrl);
  }

  public createArticle(name: string, price: number): Observable<Article> {
    let article = {
      name: name,
      price: price,
    };
    return this.http.post<Article>(this.articleUrl + "/save", article);
  }
}

