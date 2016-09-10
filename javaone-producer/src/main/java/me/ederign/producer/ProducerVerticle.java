package me.ederign.producer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import me.ederign.util.Message;

import java.util.Date;

public class ProducerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Vertx.clusteredVertx( new VertxOptions(), ar -> {
            Vertx vertx = ar.result();
            vertx.setPeriodic( 1000, l -> {
                vertx.eventBus()
                        .publish( "events",
                                  new JsonObject(
                                          Json.encodePrettily( new Message( "Java Producer Message: " + new Date() ) ) ) );
            } );

        } );
    }
}
