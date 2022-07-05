package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numberOfAssociations = Integer.parseInt(args[0]);
        String _bagOfWords = args[1];
        System.out.printf("Number of associations: %s\n", numberOfAssociations);
        System.out.printf("Bag of words: %s\n", _bagOfWords);
        List<String> bagOfWords = Arrays.asList(_bagOfWords.split(","));

        ForgettingMap forgettingMap = new ForgettingMap(numberOfAssociations);

        Thread producer = new Thread(new Producer(forgettingMap, bagOfWords));
        Thread producer2 = new Thread(new Producer(forgettingMap, bagOfWords));

        Thread consumer = new Thread(new Consumer(forgettingMap, bagOfWords));
        Thread consumer2 = new Thread(new Consumer(forgettingMap, bagOfWords));

        producer.start();
        producer2.start();

        consumer.start();
        consumer2.start();
    }
}