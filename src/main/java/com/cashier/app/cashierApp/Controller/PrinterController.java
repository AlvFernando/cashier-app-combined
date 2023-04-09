package com.cashier.app.cashierApp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cashier.app.cashierApp.Model.ResponseHandler;
import com.cashier.app.cashierApp.Model.Entity.Printer;
import com.cashier.app.cashierApp.Model.Entity.Transaction;
import com.cashier.app.cashierApp.Repository.PrinterRepository;
import com.github.anastaciocintra.output.PrinterOutputStream;

@Controller
@RequestMapping("/api")
public class PrinterController {
    @Autowired
    PrinterRepository printerRepository;
    
    @GetMapping("/getprinterdevice")
    public ResponseEntity<Object> getPrinterDevice(){
        try {
            String[] printServicesNames = PrinterOutputStream.getListPrintServicesNames();
            List<Printer> printerDeviceList  = new ArrayList<>();
            for(String printServiceName: printServicesNames){
                printerDeviceList.add(new Printer(printServiceName));
            }
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, printerDeviceList);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/usedprinterdevice")
    public ResponseEntity<Object> getUserdPrinterDevice(){
        try {
            Printer usedPrinterDevice = printerRepository.findOneById(1);
            if(usedPrinterDevice == null){
                //insert data to database
                Printer printer = new Printer(1,"");
                printerRepository.save(printer);
            }
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, usedPrinterDevice);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping("/usedprinterdevice")
    public ResponseEntity<Object> updateUserdPrinterDevice(@RequestBody Printer printer){
        //update logic
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, printer);
    }

    @PostMapping("/printreceipt")
    public ResponseEntity<Object> printReceipt(@RequestBody Transaction transaction){
        //get printer device name
        //get payment method name in database
        //get item name

        return ResponseHandler.generateResponse("Success", HttpStatus.OK, transaction);
    }
}
