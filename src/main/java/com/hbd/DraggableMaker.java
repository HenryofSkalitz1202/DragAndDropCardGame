package com.hbd;

import javafx.scene.Node;

public class DraggableMaker {

    private double mouseAnchorX;
    private double mouseAnchorY;

    public void makeDraggable ( Node n) {
        n.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
        });

        n.setOnMouseDragged(mouseEvent -> {
            n.setLayoutX(mouseEvent.getSceneX()-mouseAnchorX);
            n.setLayoutY(mouseEvent.getSceneY()-mouseAnchorY);
        });
    }
}
