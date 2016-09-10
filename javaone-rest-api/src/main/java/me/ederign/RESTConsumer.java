package me.ederign;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.json.JsonObject;

import java.util.Map;
import java.util.logging.Logger;

public class RESTConsumer {


    private static Logger logger = Logger.getAnonymousLogger();

    public static void main( String[] args ) {
        Vertx.clusteredVertx( new VertxOptions(), ar -> {
            Vertx vertx = ar.result();

//            vertx.setPeriodic( 30000, new Handler<Long>() {
//
//                @Override
//                public void handle( Long aLong ) {
//                    HttpClient httpClient = vertx.createHttpClient();
//
//                    httpClient.getNow( 8080, "system.com", "/api/customers.json", new Handler<HttpClientResponse>() {
//
//                        @Override
//                        public void handle( HttpClientResponse httpClientResponse ) {
//
//                            httpClientResponse.bodyHandler( new Handler<JsonObject>() {
//                                @Override
//                                public void handle( JsonObject jsonObject ) {
//                                    jsonObject.getMap().keySet().size();
//                                }
//                            } );
//                        }
//                    }
//                }
//
//                );
//
//
//            } );


//            vertx.eventBus()
//                    .consumer( "events",
//                               m -> {
//                                   JsonObject json = ( JsonObject ) m.body();
//                                   logger.info( "Receiving "
//                                                        + json.getString( "id" )
//                                                        + " : "
//                                                        + json.getString( "name" ) );
//                               } );
        } );
    }

}
