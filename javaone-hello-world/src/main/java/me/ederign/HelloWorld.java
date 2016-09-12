package me.ederign;

import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import me.ederign.util.Message;

import java.util.Date;
import java.util.logging.Logger;

public class HelloWorld {
    private static Logger logger = Logger.getAnonymousLogger();

    public static void main( String[] args ) {
        Vertx vertx = Vertx.vertx();
        vertx
                .createHttpServer()
                .requestHandler( r -> {
                    r.response().end( "<h1>Hello from my first " +
                                              "Vert.x 3 application</h1>" );
                } )
                .listen( 8080 );

    }
}