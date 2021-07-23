package com.example.springvertxdemo.repository;

import com.example.springvertxdemo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
