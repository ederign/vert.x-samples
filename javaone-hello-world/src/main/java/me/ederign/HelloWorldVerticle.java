package me.ederign;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import java.util.Random;
import java.util.logging.Logger;

public class HelloWorldVerticle extends AbstractVerticle {
    private static Logger logger = Logger.getAnonymousLogger();

    @Override
    public void start( Future<Void> fut ) {

        String random = String.valueOf( new Random().nextInt() )
                ;
        vertx.setPeriodic( 1000, l -> {
            vertx.eventBus()
                    .publish( "hello-bus", "HelloWorld from " + random );
        } );


        vertx.eventBus()
                .consumer( "hello-bus",
                           m -> {
                               String msg = ( String ) m.body();
                               logger.info( random + " receiving: "
                                                    + msg );
                           } );
    }
}