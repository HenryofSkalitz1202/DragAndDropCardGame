package com.hbd;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

import java.util.Scanner;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.hbd.PetakLadang.PetakLadang;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.Kartu.Item.Item;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.KartuGUI;

public class TestMain extends Application {
    private static Label timeLabel;
    private static int countdownSeconds;
    private static volatile boolean timerRunning = true;
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static Future<?> inputTask;

    public static void main(String[] args) throws Exception {
        KartuGUI.initializePaths();
        Scanner inputObj = new Scanner(System.in);
        System.out.print("Countdown seconds: ");
        String input = inputObj.nextLine();
        countdownSeconds = Integer.parseInt(input);

        PetakLadang ladang = new PetakLadang();
        PetakLadang inv = new PetakLadang();
        Item protectItem = (Item)FactoryKartu.getKartu("Protect");
        Item trapItem = (Item)FactoryKartu.getKartu("Trap");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Makhluk hewanLadang;
                if (i <= 2 && j <= 3) {
                    hewanLadang = (Makhluk)FactoryKartu.getKartu("Sapi");
                    ladang.setMakhluk(j, i, hewanLadang);
                } else if (i == 3) {
                    hewanLadang = (Makhluk)FactoryKartu.getKartu("Ayam");
                    hewanLadang.hisabItem(protectItem);
                    ladang.setMakhluk(j, i, hewanLadang);
                } else {
                    hewanLadang = (Makhluk)FactoryKartu.getKartu("Ayam");
                    hewanLadang.hisabItem(trapItem);
                    ladang.setMakhluk(j, i, hewanLadang);
                }
            }
        }

        new Thread(() -> Application.launch(TestMain.class)).start();

        List<int[]> attackedCells = BearAttack.generateAttackedCells();
        System.out.println("Quick, you have to do something!");
        ladang.printLadang();

        inputTask = executorService.submit(() -> {
            while (timerRunning) {
                System.out.printf("\nColumn: ");
                int column = Integer.parseInt(inputObj.nextLine());

                System.out.printf("Row: ");
                int row = Integer.parseInt(inputObj.nextLine());

                try {
                    Makhluk temp = ladang.getMakhluk(column, row);
                    inv.setMakhluk(column, row, temp);
                    ladang.setNull(column, row);
                } catch (DiluarPetakException e) {
                    System.out.println("Error: " + e.getMessage());
                }

                ladang.printLadang();
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
            BearAttack.attack(ladang, attackedCells);
            ladang.printLadang();

            System.out.println("=========INV=========");
            inv.printLadang();
        }

        inputObj.close();
        executorService.shutdown();
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
        inputTask.cancel(true); // Interrupt the input thread
    }
}




