package com.hbd;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.hbd.PetakLadang.PetakLadang;
import com.hbd.Deck.Deck;
import com.hbd.PetakLadang.Exception.DiluarPetakException;

import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Makhluk.Makhluk;

public class BearAttack extends Application {
    private static Label timeLabel;
    private static int countdownSeconds;
    private static volatile boolean timerRunning = true;
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static Future<?> inputTask;

    public static void startBearAttack(PetakLadang ladang, Deck deckAktif) throws Exception {
        Scanner inputObj = new Scanner(System.in);
        Random rand = new Random();
        countdownSeconds = rand.nextInt(60 - 30 + 1) + 30;

        // Launch JavaFX application in a separate thread
        new Thread(() -> Application.launch(BearAttack.class)).start();

        List<int[]> attackedCells = BearAttack.generateAttackedCells();
        System.out.println("Quick, you have to do something!");
        ladang.printLadang();

        inputTask = executorService.submit(() -> {
            while (timerRunning) {
                try {
                    System.out.printf("\nColumn Ambil: ");
                    int columnAmbil = Integer.parseInt(inputObj.nextLine());

                    System.out.printf("Row Ambil: ");
                    int rowAmbil = Integer.parseInt(inputObj.nextLine());

                    System.out.printf("\nColumn Taro: ");
                    int columnTaro = Integer.parseInt(inputObj.nextLine());

                    System.out.printf("Row Taro: ");
                    int rowTaro = Integer.parseInt(inputObj.nextLine());

                    try {
                        Makhluk temp = ladang.getMakhluk(columnAmbil, rowAmbil);
                        if (ladang.getMakhluk(columnTaro, rowTaro) != null) {
                            ladang.setNull(columnTaro, rowTaro);
                        }
                        ladang.setMakhluk(columnTaro, rowTaro, temp);
                        ladang.setNull(columnAmbil, rowAmbil);
                    } catch (DiluarPetakException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    ladang.printLadang();
                } catch (Exception e) {
                    System.out.println("Input error: " + e.getMessage());
                }
            }
        });

        try {
            inputTask.get(); // Wait for the task to complete
        } catch (Exception e) {
            // Handle any exceptions
        }

        if (!timerRunning) {
            System.out.println("Timer finished, program ending.");
            System.out.println("BEAR ATTACK!!");
            BearAttack.attack(ladang, deckAktif, attackedCells);
            ladang.printLadang();

            System.out.println("=========INV=========");
            deckAktif.print();

        }

        inputObj.close();
    }

    public static List<int[]> generateAttackedCells() {
        List<int[]> attackedCells = new ArrayList<>();

        int maxNumRow = 3;
        int minNumRow = 1;
        int maxNumCol = 3;
        int minNumCol = 1;
        Random rand = new Random();
        int numRow = rand.nextInt(maxNumRow - minNumRow + 1) + minNumRow;
        if (numRow == 3) {
            maxNumCol = 2;
        }
        int numCol = rand.nextInt(maxNumCol - minNumCol + 1) + minNumCol;

        int startRow = rand.nextInt(3 - numRow + 1) + numRow;
        int startCol = rand.nextInt(4 - numCol + 1) + numCol;

        System.out.println("Cells attacked:");

        for (int i = startRow; i > startRow - numRow; i--) {
            for (int j = startCol; j > startCol - numCol; j--) {
                System.out.printf("(%d, %d)\n", j, i);
                attackedCells.add(new int[]{i, j});
            }
        }

        return attackedCells;
    }

    public static void attack(PetakLadang ladang, Deck deckAktif, List<int[]> attackedCells) throws Exception {
        boolean trapped = false;

        for (int[] cell : attackedCells) {
            Makhluk makhlukCell = ladang.getMakhluk(cell[1], cell[0]);
            if (makhlukCell == null) {
                System.out.printf("(%d, %d) is empty\n", cell[1], cell[0]);
            } else {
                boolean isProtected = false;
                for (String effect : makhlukCell.getEffect()) {
                    if (effect.equals("Protect")) {
                        System.out.printf("(%d, %d) is protected\n", cell[1], cell[0]);
                        isProtected = true;
                        break;
                    } else if (effect.equals("Trap")) {
                        System.out.printf("(%d, %d) traps beruang!!\n", cell[1], cell[0]);
                        if (!deckAktif.isFull()) {
                            deckAktif.insertKartu(FactoryKartu.getKartu("Beruang"));
                        }
                        trapped = true;
                        break;
                    }
                }

                if (trapped) {
                    break;
                }

                if (!isProtected) {
                    System.out.printf("(%d, %d) is attacked\n", cell[1], cell[0]);
                    ladang.setNull(cell[1], cell[0]);
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Countdown Clock");

        timeLabel = new Label("00.0");
        timeLabel.setStyle("-fx-font-size: 24px;");

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(timeLabel);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        Thread runner = new Thread(new Clock(countdownSeconds));
        runner.start();
    }

    public static void updateLabel(String text) {
        Platform.runLater(() -> timeLabel.setText(text));
    }

    public static void stopTimer() {
        timerRunning = false;
        if (inputTask != null && !inputTask.isDone()) {
            inputTask.cancel(true); // Interrupt the input thread
        }
    }
}