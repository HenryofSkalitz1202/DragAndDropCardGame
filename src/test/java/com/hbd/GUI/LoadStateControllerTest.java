package com.hbd.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

public class LoadStateControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hbd/GUI/loadstate.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    public void testFormatTextField() {

        TextField formatTextField = lookup("#format").query();

        clickOn(formatTextField).write("Test Format");

        verifyThat(formatTextField, hasText("Test Format"));
    }

    @Test
    public void testFolderTextField() {

        TextField folderTextField = lookup("#folder").query();

        clickOn(folderTextField).write("Test Folder");

        verifyThat(folderTextField, hasText("Test Folder"));
    }

}
