package com.example.webshop.controllers;

import com.example.webshop.models.Article;
import com.example.webshop.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Article createArticle(@RequestBody final Article article) {
        return articleService.create(article);
    }

    // read 1
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public Article getOneArticle(@PathVariable Integer id) {
        return articleService.getOne(id);
    }

    // readAll
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAll();
    }

    // update
    @PutMapping
    @RequestMapping(path = "update/{id}")
    public Article updateArticle(@PathVariable Integer id, @RequestBody Article article) {
        return articleService.update(id, article);
    }

    // delete
    @DeleteMapping
    @RequestMapping(path = "delete/{id}")
    public void deleteOneArticle(@PathVariable Integer id) {
        articleService.delete(id);
    }
}
