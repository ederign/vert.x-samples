package me.ederign.util;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer {

    private static final AtomicInteger COUNTER = new AtomicInteger();

    private Integer id;

    private String name;

    private String system;

    public Customer( String name, String system ) {
        this.system = system;
        this.id = COUNTER.getAndIncrement();
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSystem() {
        return system;
    }
}
