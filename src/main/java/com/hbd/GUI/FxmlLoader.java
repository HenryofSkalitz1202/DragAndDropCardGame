package com.hbd.GUI;

import javafx.scene.layout.Pane;
import java.net.URL;
import javafx.fxml.FXMLLoader;

public class FxmlLoader {
    private Pane view;

    public Pane getPage(String fileName) {

        try {
            // URL fileURL = App.class.getResource("/com/hbd/GUI/" + fileName + ".fxml");
            // if (fileURL == null) {
            // throw new java.io.FileNotFoundException("FXML file can't be found");
            // }

            view = new FXMLLoader(getClass().getResource("/com/hbd/GUI/" + fileName + ".fxml")).load();
        } catch (Exception e) {
            System.out.println("No page");
        }

        return view;
    }

}
