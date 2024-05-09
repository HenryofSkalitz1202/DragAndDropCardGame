module com.hbd {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.hbd to javafx.fxml;
    exports com.hbd;
}
