package com.example.webshop.controllers;

import com.example.webshop.models.Article;
import com.example.webshop.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/article")
public class ArticleController extends SuperController<Article> {

    @Autowired
    private ArticleService articleService;

    @PostMapping(path = "save")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        return super.createUnit(article, articleService);
    }

    // read 1
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public ResponseEntity<Article> getOneArticle(@PathVariable Integer id) {
        return super.getOneUnit(id, articleService);
    }

    // readAll
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        return super.getAllUnits(articleService);
    }

    // update
    @PutMapping
    @RequestMapping(path = "update/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Integer id, @RequestBody Article article) {
        return super.updateUnit(id, article, articleService);
    }

    // delete
    @DeleteMapping
    @RequestMapping(path = "delete/{id}")
    public void deleteOneArticle(@PathVariable Integer id) {
        super.deleteUnit(id, articleService);
    }
}
