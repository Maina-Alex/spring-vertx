package com.example.springvertxdemo.service;

import com.example.springvertxdemo.entity.Article;
import com.example.springvertxdemo.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getAllArticles(){
       return articleRepository.findAll();
    }
}
