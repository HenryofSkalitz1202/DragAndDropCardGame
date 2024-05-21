package com.hbd;

import java.time.Duration;
import java.time.LocalTime;

public class CountdownClock {
    public static void main(String[] args) {
        int countdownSeconds = 60; // Set the countdown duration in seconds
        LocalTime endTime = LocalTime.now().plusSeconds(countdownSeconds);

        Thread countdownThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    LocalTime currentTime = LocalTime.now();
                    Duration remainingTime = Duration.between(currentTime, endTime);

                    if (!remainingTime.isNegative()) {
                        // long hours = remainingTime.toHours();
                        // long minutes = remainingTime.toMinutes() % 60;
                        long seconds = remainingTime.getSeconds() % 60;
                        long millis = remainingTime.toMillis() % 1000;
                        
                        System.out.printf("%02d.%01d%n", seconds, millis / 100);
                    } else {
                        System.out.println("00.0");
                        break;
                    }

                    try {
                        Thread.sleep(100); // Sleep for 100 milliseconds (0.1 second)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        countdownThread.start();
    }
}