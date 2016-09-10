package me.ederign;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import me.ederign.util.Message;

import java.util.Date;
import java.util.logging.Logger;

public class HelloWorld extends AbstractVerticle {
    private static Logger logger = Logger.getAnonymousLogger();

    @Override
    public void start( Future<Void> fut ) {
        vertx
                .createHttpServer()
                .requestHandler( r -> {
                    r.response().end( "<h1>Hello from my first " +
                                              "Vert.x 3 application</h1>" );
                } )
                .listen( 8080, result -> {
                    if ( result.succeeded() ) {
                        fut.complete();
                    } else {
                        fut.fail( result.cause() );
                    }
                } );

        vertx.setPeriodic( 1000, l -> {
            vertx.eventBus()
                    .publish( "hello-bus",
                              new JsonObject(
                                      Json.encodePrettily( new Message( "HelloWorld Message: " + new Date() ) ) ) );
        } );

        vertx.eventBus()
                .consumer( "hello-bus",
                           m -> {
                               JsonObject json = ( JsonObject ) m.body();
                               logger.info( "Receiving "
                                                    + json.getString( "id" )
                                                    + " : "
                                                    + json.getString( "name" ) );
                           } );
    }
}