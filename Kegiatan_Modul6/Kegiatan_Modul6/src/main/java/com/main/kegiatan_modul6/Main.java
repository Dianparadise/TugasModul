package com.main.kegiatan_modul6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.security.*;

public class Main extends Application {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Generate RSA key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();

            // Set up the initial stage for Morse code input
            primaryStage.setTitle("Library System Login");

            VBox layout = new VBox(10);
            layout.setPadding(new Insets(20));

            Label warningLabel = new Label("AWAS PROGRAM INI MENGANDUNG ZAT BERBAHAYA");
            Label promptLabel = new Label("Masukkan kode morse bang:");
            TextField morseCodeField = new TextField();
            Button submitButton = new Button("Submit");
            Label resultLabel = new Label();

            submitButton.setOnAction(e -> {
                String message = morseCodeField.getText();
                if (message.equals("231204")) {
                    try {
                        Signature rsa = Signature.getInstance("SHA256withRSA");
                        rsa.initSign(privateKey);
                        rsa.update(message.getBytes());
                        byte[] signature = rsa.sign();

                        rsa.initVerify(publicKey);
                        rsa.update(message.getBytes());
                        boolean verifies = rsa.verify(signature);

                        if (verifies) {
                            LibrarySystem app = new LibrarySystem();
                            app.start(new Stage());
                            primaryStage.close();
                        } else {
                            resultLabel.setText("Verifikasi Gagal.");
                        }
                    } catch (Exception ex) {
                        resultLabel.setText("Error: " + ex.getMessage());
                    }
                } else {
                    resultLabel.setText("Pesan tidak valid, coba lagi.");
                }
            });

            layout.getChildren().addAll(warningLabel, promptLabel, morseCodeField, submitButton, resultLabel);

            Scene scene = new Scene(layout, 400, 200);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
