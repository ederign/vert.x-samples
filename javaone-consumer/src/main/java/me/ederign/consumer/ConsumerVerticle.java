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
                    .consumer( "system.process.in",
                               m -> {
                                   JsonObject json = ( JsonObject ) m.body();
                                   logger.info( "Receiving "
                                                        + json.getInteger( "id" )
                                                        + " : "
                                                        + json.getString( "name" )
                                                        + " : "
                                                        + json.getString( "system" ) );

                               } );
        } );
    }
}
