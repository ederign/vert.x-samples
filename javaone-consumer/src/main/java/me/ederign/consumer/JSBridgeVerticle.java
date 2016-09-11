package me.ederign.consumer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

public class JSBridgeVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Vertx.clusteredVertx( new VertxOptions(), ar -> {
            Vertx vertx = ar.result();
            vertx.setPeriodic( 1000, l -> {
//                vertx.eventBus()
//                        .publish( "events",
//                                  new JsonObject(
//                                          Json.encodePrettily( new Message( "JSBridge Message: " + new Date() ) ) ) );

                Router router = Router.router( vertx );

                SockJSHandler sockJSHandler = SockJSHandler.create( vertx );
                BridgeOptions options = new BridgeOptions()
                        .addInboundPermitted(
                                new PermittedOptions().setAddress( "system.process.out.S8080" ) )
                        .addInboundPermitted(
                                new PermittedOptions().setAddress( "system.process.out.S8081" ) )
                        .addInboundPermitted(
                                new PermittedOptions().setAddress( "system.process.in" ) )
                        .addOutboundPermitted(
                                new PermittedOptions().setAddress( "system.process.out.S8080" ) )
                        .addOutboundPermitted(
                                new PermittedOptions().setAddress( "system.process.out.S8081" ) )
                        .addOutboundPermitted(
                                new PermittedOptions().setAddress( "system.process.in" ) );
                sockJSHandler.bridge( options );

                router.route( "/eventbus/*" ).handler( sockJSHandler );
                router.route( "/assets/*" )
                        .handler( StaticHandler.create( "assets" ) );

                vertx.createHttpServer()
                        .requestHandler( router::accept ).listen( 9001 );
            } );

        } );
    }
}