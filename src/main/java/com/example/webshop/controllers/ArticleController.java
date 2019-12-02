package com.example.webshop.controllers;

import com.example.webshop.models.Article;
import com.example.webshop.repositories.ArticleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;


    // create
    @PostMapping
    public Article createArticle(@RequestBody final Article article) {
        return articleRepository.saveAndFlush(article);
    }

    // read 1
    @GetMapping
    @RequestMapping(path = "{id}")
    public Article getOneArticle(@PathVariable Integer id) {
        return articleRepository.getOne(id);
    }

    // readAll
    @GetMapping
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    // update
    @PutMapping
    @RequestMapping(path = "{id}")
    public Article updateArticle(@PathVariable Integer id, @RequestBody Article article) {
        //TODO  add validation for each field.
        Article existingArticle = articleRepository.getOne(id);
        BeanUtils.copyProperties(article, existingArticle, "id");
        return articleRepository.saveAndFlush(existingArticle);
    }
    // delete
    @DeleteMapping
    @RequestMapping(path = "{id}")
    public void  deleteOneArticle(@PathVariable Integer id){
        articleRepository.deleteById(id);
    }
}
