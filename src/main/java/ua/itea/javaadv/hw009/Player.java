package ua.itea.javaadv.hw009;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Player implements Callable<String> {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String call() throws Exception {
        long sleep = (long) (Math.random() * 20);
        if (sleep < 5) {
            sleep = 5;
        }
        System.out.println(name + " is sleeping for " + sleep);
        TimeUnit.SECONDS.sleep(sleep);
        return name;
    }
}
