package org.example;

import java.util.List;
import java.util.Random;

class Producer implements Runnable {

    private final ForgettingMap forgettingMap;
    private final Random random;
    private final List<String> bagOfWords;

    Producer(ForgettingMap forgettingMap, List<String> bagOfWords) {
        this.forgettingMap = forgettingMap;
        this.random = new Random();
        this.bagOfWords = bagOfWords;
    }

    @Override public void run()
    {
        while (true) {
            send();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void send() {
        String key = this.bagOfWords.get(this.random.nextInt(this.bagOfWords.size()));
        Integer value = this.random.nextInt();

        this.forgettingMap.Add(key, value);
    }
}
