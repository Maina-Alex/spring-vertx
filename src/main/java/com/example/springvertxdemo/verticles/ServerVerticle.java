package com.example.springvertxdemo.verticles;

import com.example.springvertxdemo.verticles.RecipientVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Component;

@Component
public class ServerVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        super.start();
        Router router= Router.router(vertx);

        router.get("/api/articles")
                .handler(this::getAllArticles);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config().getInteger("http.port",8080));


    }
    private void getAllArticles(RoutingContext routingContext){
        vertx.eventBus()
                .<String>request(RecipientVerticle.GET_ALL_ARTICLES, "", result -> {
                    if (result.succeeded()) {
                        routingContext.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(result.result()
                                        .body());
                    } else {
                        routingContext.response()
                                .setStatusCode(500)
                                .end();
                    }
                });
    }

}
