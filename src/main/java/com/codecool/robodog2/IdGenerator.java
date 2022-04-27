package com.codecool.robodog2;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private final static AtomicInteger counter = new AtomicInteger();

    public static int getId() {
        return counter.incrementAndGet();
    }
}
