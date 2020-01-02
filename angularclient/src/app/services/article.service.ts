import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Article} from "../models/article.model";
import {HttpClient, HttpResponse, HttpResponseBase} from "@angular/common/http";
import {error} from "util";
import {Category} from "../models/category.enum";

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

  public createArticle(category : Category,name: string, price: number): Observable<Article> {
    let article = {
      name: name,
      price: price,
      category : category,
    };
    return this.http.post<Article>(this.articleUrl + "/save", article);
  }

  public deleteArticle(id: number): Observable<HttpResponse<boolean>> {
    return this.http.delete<boolean>(this.articleUrl + "/delete/" + id, {observe : "response"});
  }
}

