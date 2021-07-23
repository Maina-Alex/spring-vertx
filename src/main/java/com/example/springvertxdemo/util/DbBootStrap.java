package com.example.springvertxdemo.util;

import com.example.springvertxdemo.entity.Article;
import com.example.springvertxdemo.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class DbBootStrap implements CommandLineRunner {
    private final ArticleRepository articleRepository;
    @Override
    public void run(String... args) throws Exception {
        IntStream.range(0,10)
                .forEach(count-> this.articleRepository.save(new Article(new Random().nextLong(), UUID.randomUUID().toString())));
    }
}
