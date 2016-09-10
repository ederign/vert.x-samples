package me.ederign;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import static me.ederign.util.RandomData.generateRandomData;

public class RESTFacade extends AbstractVerticle {

    @Override
    public void start( Future<Void> fut ) {
        Router router = Router.router( vertx );

        router.get( "/api/customers.json" ).handler( this::getAll );

        vertx
                .createHttpServer()
                .requestHandler( router::accept )
                .listen(
                        config().getInteger( "http.port", 8080 ),
                        result -> {
                            if ( result.succeeded() ) {
                                fut.complete();
                            } else {
                                fut.fail( result.cause() );
                            }
                        }
                );
    }

    private void getAll( RoutingContext routingContext ) {
        routingContext.response()
                .putHeader( "content-type", "application/json; charset=utf-8" )
                .end(   generateRandomData( 100, "Legacy System A" ) );
    }
}
