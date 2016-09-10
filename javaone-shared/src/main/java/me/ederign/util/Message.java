package me.ederign.util;

import java.util.concurrent.atomic.AtomicInteger;

public class Message {

    private static final AtomicInteger COUNTER = new AtomicInteger();

    private String id;

    private String name;


    public Message( String name ) {
        this.id = String.valueOf( COUNTER.getAndIncrement() );
        this.name = name;
    }

    public Message() {
        this.id = String.valueOf( COUNTER.getAndIncrement() );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
