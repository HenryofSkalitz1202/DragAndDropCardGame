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

public class SaveStateControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hbd/GUI/SaveState.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    public void testFormatTextField() {
        // Lookup the format text field by its fx:id
        TextField formatTextField = lookup("#format").query();

        // Input some text
        clickOn(formatTextField).write("Test Format");

        // Verify that the text field has the correct text
        verifyThat(formatTextField, hasText("Test Format"));
    }

    @Test
    public void testFolderTextField() {
        // Lookup the folder text field by its fx:id
        TextField folderTextField = lookup("#folder").query();

        // Input some text
        clickOn(folderTextField).write("Test Folder");

        // Verify that the text field has the correct text
        verifyThat(folderTextField, hasText("Test Folder"));
    }

    // You can add more tests as needed for your controller's functionality
}
