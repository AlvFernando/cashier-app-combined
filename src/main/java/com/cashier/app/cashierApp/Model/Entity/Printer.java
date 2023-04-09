package com.cashier.app.cashierApp.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "printerdevice")
public class Printer {
    @Id
    @Column(name = "id")
    Integer id;
    @Column(name = "printerdevicename")
    String printerName;

    public Printer(Integer id, String printerName) {
        this.id = id;
        this.printerName = printerName;
    }

    public Printer(String printerName) {
        this.printerName = printerName;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    @Override
    public String toString() {
        return "Printer [printerName=" + printerName + "]";
    }
    
}
