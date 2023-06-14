package com.epam.kabaldin.chatgpt.developer.frameworkandconf;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class MainVerticle extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new MainVerticle());
    }

    @Override
    public void start() {
        Router router = Router.router(vertx);

        router.get("/hello").handler(this::handleHello);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);
    }

    private void handleHello(RoutingContext routingContext) {
        routingContext.response().end("Hello, world!");
    }
}
