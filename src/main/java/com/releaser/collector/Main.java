package com.releaser.collector;

import com.releaser.collector.exception.CollectorException;

public class Main {

    public static void main(String[] args)
    {
        Collector collector = new Collector();

        try {
            collector.collect("data/test/");
        } catch (CollectorException e) {
            e.printStackTrace();
        }
    }
}
