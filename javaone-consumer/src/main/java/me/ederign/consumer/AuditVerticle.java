package me.ederign.consumer;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AuditVerticle {

    private static Logger logger = Logger.getAnonymousLogger();

    public static void main( String[] args ) {
        Vertx.clusteredVertx( new VertxOptions(), ar -> {
            Vertx vertx = ar.result();

            vertx.eventBus()
                    .consumer( "system.process.out.server1",
                               m -> {
                                   JsonObject json = ( JsonObject ) m.body();
                                   Integer id = json.getInteger(  "id" );
                                   String name = json.getString( "name" );
                                   //add some logic here
                                   logger.log( Level.INFO, "Audit server1 " + id + ": " + name );
                               } );
            vertx.eventBus()
                    .consumer( "system.process.out.server2",
                               m -> {
                                   JsonObject json = ( JsonObject ) m.body();
                                   Integer id = json.getInteger( "id" );
                                   String name = json.getString( "name" );
                                   //add some logic here
                                   logger.log( Level.INFO, "Audit server2 " + id + ": " + name );
                               } );
        } );
    }
}
