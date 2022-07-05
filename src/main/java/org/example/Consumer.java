package org.example;

import java.util.Optional;
import java.util.Random;
import java.util.List;

public class Consumer implements Runnable {

    private final ForgettingMap forgettingMap;

    Consumer(ForgettingMap forgettingMap, List<String> bagOfWords) {
        this.forgettingMap = forgettingMap;
    }

    @Override public void run()
    {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            retrieve();

            System.out.printf(Thread.currentThread().getName() + ": forgettingMap state = %s\n", forgettingMap);
        }
    }

    protected Optional<Object> retrieve() {
        Optional<String> _key = this.forgettingMap.keySet().parallelStream().findFirst();
        if (!_key.isPresent()) {
            System.out.printf(Thread.currentThread().getName() + ": key not found");
            return Optional.empty();
        }

        String key = _key.get();
        System.out.printf(Thread.currentThread().getName() + ": getting key value pair from ForgettingMap key %s\n", key);
        Optional<Object> associationValue = forgettingMap.Find(key);
        if (!associationValue.isPresent()) {
            System.out.printf(Thread.currentThread().getName() + ": value not found for key %s\n", key);
            return Optional.empty();
        }

        Object value = associationValue.get();
        System.out.printf(Thread.currentThread().getName() + ": value found for key %s \n", key);
        return Optional.ofNullable(value);
    }
}
