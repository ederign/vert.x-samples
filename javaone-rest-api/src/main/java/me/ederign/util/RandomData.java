package me.ederign.util;

import io.vertx.core.json.Json;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RandomData {

    private final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    private final static java.util.Random rand = new java.util.Random();

    private final static Set<String> identifiers = new HashSet<String>();

    public static String generateRandomData( int numberOfSamples, String system ) {

        Map<Integer, Customer> c = new HashMap<>();
        for ( int i = 0; i < numberOfSamples; i++ ) {
            Customer c1 = new Customer( randomIdentifier(), system );
            c.put( c1.getId(), c1 );
        }

        return
                Json.encodePrettily( c );
    }


    private static String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while ( builder.toString().length() == 0 ) {
            int length = rand.nextInt( 5 ) + 5;
            for ( int i = 0; i < length; i++ )
                builder.append( lexicon.charAt( rand.nextInt( lexicon.length() ) ) );
            if ( identifiers.contains( builder.toString() ) ) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

}
