package com.hbd;

public class Clock implements Runnable {
    private int countdownSeconds;

    public Clock(int countdownSeconds) {
        this.countdownSeconds = countdownSeconds;
    }

    @Override
    public void run() {
        long endTime = System.currentTimeMillis() + countdownSeconds * 1000;

        while (true) {
            long remainingTime = endTime - System.currentTimeMillis();

            if (remainingTime > 0) {
                long seconds = (remainingTime / 1000) % 60;
                long millis = (remainingTime % 1000) / 100;
                String timeString = String.format("%02d.%01d", seconds, millis);
                TestMain.updateLabel(timeString);
            } else {
                TestMain.updateLabel("Waktu HABIS!!!");
                TestMain.stopTimer(); // Signal the main thread that the timer has finished
                break;
            }

            try {
                Thread.sleep(100); // Sleep for 100 milliseconds (0.1 second)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}