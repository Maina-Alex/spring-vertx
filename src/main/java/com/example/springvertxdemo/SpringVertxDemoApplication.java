package com.example.springvertxdemo;

import com.example.springvertxdemo.verticles.RecipientVerticle;
import com.example.springvertxdemo.verticles.ServerVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Configuration
public class SpringVertxDemoApplication {
	@Autowired
	private ServerVerticle serverVerticle;
	@Autowired
	private RecipientVerticle recipientVerticle;

	public static void main(String[] args) {
		SpringApplication.run(SpringVertxDemoApplication.class, args);
	}

	@PostConstruct
	public void deployVerticle(){
		final Vertx vertx=Vertx.vertx();
		vertx.deployVerticle(serverVerticle);
		vertx.deployVerticle(recipientVerticle);
	}

}
