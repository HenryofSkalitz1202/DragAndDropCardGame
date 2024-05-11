package com.hbd;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.fxml.Initializable;

public class DraggableController implements Initializable {

    @FXML
    private Rectangle rect1;

    DraggableMaker dm = new DraggableMaker();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dm.makeDraggable(rect1);
    }
}
