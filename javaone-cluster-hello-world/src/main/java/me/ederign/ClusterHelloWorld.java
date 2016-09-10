package me.ederign;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import me.ederign.util.Message;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.logging.Logger;

public class ClusterHelloWorld extends AbstractVerticle {
    private static Logger logger = Logger.getAnonymousLogger();

    @Override
    public void start( Future<Void> fut ) throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String me = ManagementFactory.getRuntimeMXBean().getName() + " : " + ip;

        Vertx.clusteredVertx( new VertxOptions(), ar -> {


            vertx.setPeriodic( 1000, l -> {
                vertx.eventBus()
                        .publish( "hello-bus",
                                  new JsonObject(
                                          Json.encodePrettily(
                                                  new Message( me
                                                  ) ) ) );
            } );

            vertx.eventBus()
                    .consumer( "hello-bus",
                               m -> {
                                   JsonObject json = ( JsonObject ) m.body();
                                   logger.info( ip + " receiving from "
                                                        + json.getString( "id" )
                                                        + " : "
                                                        + json.getString( "name" ) );
                               } );
        } );
    }
}