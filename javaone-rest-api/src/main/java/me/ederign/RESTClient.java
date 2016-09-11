package me.ederign;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;

import java.util.Map;
import java.util.logging.Logger;

public class RESTClient {


    private static Logger logger = Logger.getAnonymousLogger();

    public static void main( String[] args ) {
        Vertx.clusteredVertx( new VertxOptions(), ar -> {
            Vertx vertx = ar.result();

            HttpClient httpClient = vertx.createHttpClient();

            vertx.setPeriodic( 10000, event -> getDataFromRESTAPIs( httpClient, vertx, 8080 ) );

            vertx.setPeriodic( 13000, event -> getDataFromRESTAPIs( httpClient, vertx, 8081 ) );

        } );
    }

    private static void getDataFromRESTAPIs( HttpClient httpClient, Vertx vertx , int port) {
        httpClient.getNow( port, "system.com", "/api/customers",
                           httpClientResponse -> httpClientResponse.bodyHandler( data -> {
                               processData( vertx, data );
                           } ) );
    }

    private static void processData( Vertx vertx, Buffer data ) {
        JsonObject entries = data.toJsonObject();
        for ( Map.Entry<String, Object> entry : entries ) {
            vertx.eventBus()
                    .publish( "system.process.in",
                              entry.getValue() );
        }
    }

}
