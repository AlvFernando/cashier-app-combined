package com.cashier.app.cashierApp;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CashierAppApplication {

	public static void main(String[] args) {
		openHomePage();
		SpringApplication.run(CashierAppApplication.class, args);
		
	}

    private static void openHomePage() {
        try {
            URI homepage = new URI("http://localhost:8080/");
            Desktop.getDesktop().browse(homepage);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
