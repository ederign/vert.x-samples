package me.ederign.consumer;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;

import java.util.logging.Logger;

public class ConsumerVerticle {

    private static Logger logger = Logger.getAnonymousLogger();

    public static void main( String[] args ) {
        Vertx.clusteredVertx( new VertxOptions(), ar -> {
            Vertx vertx = ar.result();

            vertx.eventBus()
                    .consumer( "events",
                               m -> {
                                   JsonObject json = ( JsonObject ) m.body();
                                   logger.info( "Receiving "
                                                        + json.getString( "id" )
                                                        + " : "
                                                        + json.getString( "name" ) );
                               } );
        } );
    }
}
