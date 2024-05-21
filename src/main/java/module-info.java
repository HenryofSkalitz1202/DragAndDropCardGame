module com.hbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.hbd to javafx.fxml;

    exports com.hbd;
    exports com.hbd.Kartu;
    exports com.hbd.Kartu.Makhluk;
    exports com.hbd.Kartu.Produk;

}
