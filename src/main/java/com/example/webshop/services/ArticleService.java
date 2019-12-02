package com.example.webshop.services;

import com.example.webshop.models.Article;
import com.example.webshop.repositories.ArticleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticleService implements CrudService<Article> {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Article create(Article article) {
       return articleRepository.saveAndFlush(article);
    }

    @Override
    public Article getOne(Integer id) {
        return articleRepository.getOne(id);
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article update(Integer id, Article article) {
        //TODO  add validation for each field.
        Article existingArticle = articleRepository.getOne(id);
        BeanUtils.copyProperties(article, existingArticle, "id");
        return articleRepository.saveAndFlush(existingArticle);
    }

    @Override
    public void delete(Integer id) {
        articleRepository.deleteById(id);
    }
}
