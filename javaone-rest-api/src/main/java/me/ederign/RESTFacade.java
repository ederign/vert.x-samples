package me.ederign;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import static me.ederign.util.RandomData.generateRandomData;

public class RESTFacade extends AbstractVerticle {

    @Override
    public void start( Future<Void> fut ) {
        String httpPort = System.getProperty( "http.port" );
        if ( httpPort == null || httpPort.isEmpty() ) {
            httpPort = "8080";
        }
        Router router = Router.router( vertx );

        router.get( "/api/customers" ).handler( this::getAll );

        vertx
                .createHttpServer()
                .requestHandler( router::accept )
                .listen(
                        Integer.valueOf( httpPort ),
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
                .end( generateRandomData( 100, System.getProperty( "server.name" ) ) );
    }
}
