package me.ederign;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import me.ederign.util.Message;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainVerticle extends AbstractVerticle {

    private Map<Integer, Message> products = new LinkedHashMap<>();

    private void createSomeData() {
        Message msg1 = new Message( "Message1" );
        products.put( msg1.getId(), msg1 );
        Message msg2 = new Message( "Message2" );
        products.put( msg2.getId(), msg2 );
    }

    private void getAll(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end( Json.encodePrettily( products.values()));
    }

    @Override
    public void start() throws Exception {

        createSomeData();

        Router router = Router.router( vertx );

        router.route("/").handler( StaticHandler.create() );

        router.get("/api/messages").handler(this::getAll);

        vertx.createHttpServer().requestHandler( router::accept ).listen( 8080 );

    }

}