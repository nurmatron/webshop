package com.example.webshop.services;

import com.example.webshop.models.Article;
import com.example.webshop.repositories.ArticleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements CrudService<Article> {

    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Optional<Article> create(Article article) {
       return Optional.of(articleRepository.saveAndFlush(article));
    }

    @Override
    public Optional<Article> getOne(Integer id) {
        return Optional.of(articleRepository.getOne(id));
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> update(Integer id, Article article) {
        //TODO  add validation for each field.
        Article existingArticle = articleRepository.getOne(id);
        BeanUtils.copyProperties(article, existingArticle, "id");
        return Optional.of(articleRepository.saveAndFlush(existingArticle));
    }

    @Override
    public boolean delete(Integer id) {
        articleRepository.deleteById(id);
            Optional<Article> article = getOne(id);
            if(article.isPresent()) {
                return false;
            }
            return true;
    }
}
