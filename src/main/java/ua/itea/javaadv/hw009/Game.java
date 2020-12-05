package ua.itea.javaadv.hw009;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Game {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);

        List<Future<String>> listOfFutures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            listOfFutures.add(es.submit(new Player("Player_" + i)));
        }

        List<Future<String>> stillRunningFutures = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
            stillRunningFutures =
                    listOfFutures.stream().filter(f -> !f.isDone()).collect(Collectors.toList());
            if (stillRunningFutures.isEmpty()) {
                System.out.println();
                System.out.println("All players done connecting. Game started!");
                break;
            }
        }
        if (!stillRunningFutures.isEmpty()) {
            System.out.println();
            System.out.println("Connection lost. This game is safe to leave.");
        }
        es.shutdown();
    }
}
