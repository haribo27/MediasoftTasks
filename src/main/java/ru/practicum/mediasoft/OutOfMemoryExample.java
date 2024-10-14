package ru.practicum.mediasoft;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OutOfMemoryExample {

    public static void main(String[] args) {
        exampleOne();
        exampleTwo();
        exampleThree();
    }

    public static void exampleOne() {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte[1024 * 1024]);
        }
    }

    public static void exampleTwo() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            while (true) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void exampleThree() {
        try {
            int[] largeArray = new int[Integer.MAX_VALUE];
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }
}
