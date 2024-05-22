module com.hbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.smartcardio;

    opens com.hbd to javafx.fxml;
    exports com.hbd;
    exports com.hbd.PetakLadang;
    opens com.hbd.PetakLadang to javafx.fxml;
    exports com.hbd.Pemain;
    opens com.hbd.Pemain to javafx.fxml;
    exports com.hbd.Deck;
    opens com.hbd.Deck to javafx.fxml;
}
