package com.hbd;

public class TestClock implements Runnable {
    private int countdownSeconds;
    private CountdownWindow window;

    public TestClock(int countdownSeconds, CountdownWindow window) {
        this.countdownSeconds = countdownSeconds;
        this.window = window;
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

                window.updateTime(timeString);
            } else {
                window.updateTime("00.0");
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