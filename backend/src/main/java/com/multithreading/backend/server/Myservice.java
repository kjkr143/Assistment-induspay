package com.multithreading.backend.server;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class Myservice {

    public CompletableFuture<String> performTaskAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task completed asynchronously";
        });
    }
}
