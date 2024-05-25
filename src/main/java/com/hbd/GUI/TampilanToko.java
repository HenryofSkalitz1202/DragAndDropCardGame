package com.hbd.GUI;

import com.hbd.GameEngine;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Makhluk.Hewan;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.Kartu.Makhluk.Tanaman;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Pemain.Exception.TidakMampuBeliException;
import com.hbd.Toko.Toko;
import com.hbd.Toko.Exception.ProdukTidakDijualException;

import javafx.event.ActionEvent;
import javafx.scene.layout.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
// imagePaths.put("Daging Beruang", "img\\productanimal\\bear_meat.jpg");
// imagePaths.put("Telur", "img\\productanimal\\egg.jpg");
// imagePaths.put("Daging Kuda", "img\\productanimal\\horse_meat.png");
// imagePaths.put("Susu", "img\\productanimal\\milk.png");
// imagePaths.put("Daging Domba", "img\\productanimal\\mutton.jpg");
// imagePaths.put("Sirip Hiu", "img\\productanimal\\shark_fin.jpg");
// imagePaths.put("Jagung", "img\\productplant\\corn.jpg");
// imagePaths.put("Labu", "img\\productplant\\pumpkin.jpg");
// imagePaths.put("Stroberi", "img\\productplant\\strawberry.png");


public class TampilanToko extends AnchorPane {
    //private PopupInfoController controller;
    private static Toko toko = Toko.getInstance();
    private static GameEngine enjin = GameEngine.getInstance();
    //Constructor
    public TampilanToko(){
            VBox main = new VBox();
            HBox h1 = new HBox();
            HBox h2 = new HBox();
            HBox h3 = new HBox();
            //Membuat template kotak
            
            Pane bear_meat = this.TemplateProdukGenerator("Daging Beruang");
            Pane telur = this.TemplateProdukGenerator("Telur");
            Pane kuda_meat = this.TemplateProdukGenerator("Daging Kuda");
            Pane susu = this.TemplateProdukGenerator("Susu");
            Pane domba_meat = this.TemplateProdukGenerator("Daging Domba");
            Pane sirip = this.TemplateProdukGenerator("Sirip Hiu");
            Pane corn = this.TemplateProdukGenerator("Jagung");
            Pane labu = this.TemplateProdukGenerator("Labu");
            Pane stroberi = this.TemplateProdukGenerator("Stroberi");

            h1.setSpacing(20); // Mengatur jarak antar pane menjadi 17 piksel
            h2.setSpacing(20);
            h3.setSpacing(20);
            // Menambahkan pane produk ke dalam HBox
            h1.getChildren().addAll(bear_meat, telur, kuda_meat);
            h2.getChildren().addAll(susu, domba_meat, sirip);
            h3.getChildren().addAll(corn, labu, stroberi);

            main.setSpacing(8);
            main.getChildren().addAll(h1, h2, h3);
            place(main, 0.0, 0.0);

            AnchorPane.setLeftAnchor(this, 230.0);
            AnchorPane.setTopAnchor(this, 120.0);
            this.show();
    }
   

    public Pane TemplateProdukGenerator(String namaProduk){
        Pane main = new Pane();
        // VBox v = new VBox();
        // HBox h = new HBox();
        // Define a method to handle click events

        
        Image bgimg = new Image(getClass().getResource("img/templateToko.png").toExternalForm());
        ImageView bg = new ImageView(bgimg);
        bg.setFitWidth(120);
        bg.setFitHeight(130);
        main.getChildren().add(bg);
        
        //Mencari foto produk
        Image produkimg = new Image(getClass().getResource(KartuGUI.getImagePathMap().get(namaProduk)).toExternalForm());
        ImageView fotoproduk = new ImageView(produkimg);
        fotoproduk.setFitWidth(120);
        fotoproduk.setFitHeight(68);
        main.getChildren().add(fotoproduk);
        fotoproduk.setLayoutY(9);
        
        //Cari tau harga
        Produk useless = (Produk) FactoryKartu.getKartu(namaProduk);
        int harga = useless.getHarga();
        System.out.println(harga);
        main.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              buttonBeli(useless);
              
            }
          });
        // //Cari tau berapa jumlah tersisa di toko
        int jumlah = toko.getJumlah(useless);
        System.out.println(jumlah);
        //Tulis teks di atas kolom yang tersedia
        Text textnama = new Text(namaProduk);
        textnama.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        Text textharga = new Text("Harga: " + harga);
        textharga.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 10));
        Text textjumlah = new Text("Jumlah: " + jumlah);
        textjumlah.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 10));
        main.getChildren().addAll(textnama);
        main.getChildren().addAll(textharga, textjumlah);
        textnama.setLayoutY(94);
        textharga.setLayoutY(106);     
        textjumlah.setLayoutY(106);
        
        textnama.setLayoutX(5);
        textharga.setLayoutX(5);
        textjumlah.setLayoutX(55);
        //v.setLayoutY(82);
        return main;
    }
    public void show(){
        App.getPane().getChildren().add(this);
    }

    public void hide(){
        App.getPane().getChildren().remove(this);
    }

    public void showError(String err){
        TextField errorText = new TextField(err);
        errorText.setAlignment(Pos.CENTER);
        errorText.setPrefWidth(489);
        errorText.setFont(Font.font("Arial", 24));
        errorText.setStyle("-fx-text-inner-color: red; -fx-background-color: transparent;");
        place(errorText, 0.0, -45.0);
    }

    private void place(Node a, Double x, Double y){
        AnchorPane.setTopAnchor(a, y);
        AnchorPane.setLeftAnchor(a, x);
        getChildren().add(a);
    }

    private void buttonBeli(Produk produk){
        try {
			try {
				enjin.getCurrentPemain().BeliProdukKeToko(toko, produk.getNama());
			} catch (TidakMampuBeliException | ProdukTidakDijualException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DeckPenuhException e) {
			e.printStackTrace();
		}
    }
}
