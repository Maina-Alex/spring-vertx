package com.example.springvertxdemo.verticles;

import com.example.springvertxdemo.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipientVerticle extends AbstractVerticle {

    public static final String GET_ALL_ARTICLES="get.articles.all";

    private final ObjectMapper mapper= new ObjectMapper();

    @Autowired
    private ArticleService articleService;

    @Override
    public void start() throws Exception {
        super.start();
        vertx.eventBus().<String>consumer(GET_ALL_ARTICLES).handler(getAllArticles(articleService));

    }

    private Handler<Message<String>> getAllArticles(ArticleService articleService) {
        return  msg-> vertx.<String>executeBlocking(future->{
            try{
                future.complete(mapper.writeValueAsString(articleService.getAllArticles()));
            }catch(JsonProcessingException e){
                future.fail(e);
            }
            }, res->{
            if(res.succeeded()){
                msg.reply(res.result());
            }else{
                msg.reply(res.cause().toString());
            }
        });
    }
}
