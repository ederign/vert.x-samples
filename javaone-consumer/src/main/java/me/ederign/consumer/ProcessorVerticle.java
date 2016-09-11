package me.ederign.consumer;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessorVerticle {

    private static Logger logger = Logger.getAnonymousLogger();

    public static void main( String[] args ) {
        Vertx.clusteredVertx( new VertxOptions(), ar -> {
            Vertx vertx = ar.result();

            vertx.eventBus()
                    .consumer( "system.process.in",
                               m -> {
                                   JsonObject json = ( JsonObject ) m.body();
                                   String system = json.getString( "system" );

                                   if ( system != null ) {
                                       String s = "system.process.out." + system;
                                       logger.log( Level.INFO, s);
                                       vertx.eventBus()
                                               .publish( s,
                                                         json );

                                   } else {
                                       vertx.eventBus()
                                               .publish( "system.process.out.no_system",
                                                         json );

                                   }

                               } );
        } );
    }
}
