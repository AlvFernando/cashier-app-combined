package com.cashier.app.cashierApp.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.print.PrintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cashier.app.cashierApp.Helper.SpaceHelper;
import com.cashier.app.cashierApp.Model.ResponseHandler;
import com.cashier.app.cashierApp.Model.Entity.Item;
import com.cashier.app.cashierApp.Model.Entity.PaymentMethod;
import com.cashier.app.cashierApp.Model.Entity.Printer;
import com.cashier.app.cashierApp.Model.Entity.Transaction;
import com.cashier.app.cashierApp.Repository.ItemRepository;
import com.cashier.app.cashierApp.Repository.PaymentMethodRepository;
import com.cashier.app.cashierApp.Repository.PrinterRepository;
import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.output.PrinterOutputStream;

@Controller
@RequestMapping("/api")
public class PrinterController {
    @Autowired
    PrinterRepository printerRepository;

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    ItemRepository itemRepository;
    
    @CrossOrigin
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

    @CrossOrigin
    @GetMapping("/usedprinterdevice")
    public ResponseEntity<Object> getUserdPrinterDevice(){
        try {
            Printer usedPrinterDevice = printerRepository.findOneById(1);
            if(usedPrinterDevice == null){
                //insert data to database
                Printer printer = new Printer(1,"");
                printerRepository.save(printer);
                return ResponseHandler.generateResponse("Success with created data; id 1", HttpStatus.OK, printer);
            }
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, usedPrinterDevice);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @CrossOrigin
    @PutMapping("/usedprinterdevice")
    public ResponseEntity<Object> updateUserdPrinterDevice(@RequestBody Printer newPrinter){
        try {
            if(newPrinter.getPrinterName() == null){
                return ResponseHandler.generateResponse("Input field is null", HttpStatus.INTERNAL_SERVER_ERROR, newPrinter);
            }
            Printer oldPrinter = printerRepository.findOneById(1);
            oldPrinter.setPrinterName(newPrinter.getPrinterName());
            oldPrinter = printerRepository.save(oldPrinter);
            return ResponseHandler.generateResponse("Update success", HttpStatus.OK, oldPrinter); 
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @CrossOrigin
    @PostMapping("/printreceipt")
    public ResponseEntity<Object> printReceipt(@RequestBody Transaction transaction){
        try {
            //init variable data
            Integer payment = transaction.getTransactionHeader().getPayment();
            Integer paymentMethodId = transaction.getTransactionHeader().getPaymentMethodId();
            Integer totalPrice = 0;
            Integer change = 0;
            List<Item> itemList = new ArrayList<>();
            LocalDateTime localDateTime = LocalDateTime.now();
            PaymentMethod paymentMethod = paymentMethodRepository.findOneById(paymentMethodId);
            Printer usedPrinterDevice = printerRepository.findOneById(1);

            //validation transaction header
            if(payment == null || paymentMethodId == null){
                return ResponseHandler.generateResponse("Transaction Header Data is Invalid", HttpStatus.BAD_REQUEST, transaction);
            }
            if(paymentMethod == null){
                return ResponseHandler.generateResponse("Transaction Header Payment Method ID Data is Invalid", HttpStatus.BAD_REQUEST, transaction);
            }

            //validation transaction detail
            for(int i=0;i<transaction.getTransactionDetail().size();i++){
                Item tempItem = itemRepository.findOneByUuid(transaction.getTransactionDetail().get(i).getItemId());
                if(tempItem == null){
                    return ResponseHandler.generateResponse("Transaction Detail Data at index "+ i +" is Invalid", HttpStatus.BAD_REQUEST, transaction);
                }
                if(transaction.getTransactionDetail().get(i).getAmount() > tempItem.getItemQty()){
                    return ResponseHandler.generateResponse("Transaction Detail Data Quantity at index "+i+" is more than the stock quantity", HttpStatus.BAD_REQUEST, transaction);
                }
                totalPrice = totalPrice+(tempItem.getItemPrice()*transaction.getTransactionDetail().get(i).getAmount());
                itemList.add(tempItem);
            }

            //validation payment and totalPrice
            if(totalPrice>payment){
                return ResponseHandler.generateResponse("Payment is less than the total price", HttpStatus.BAD_REQUEST, transaction);
            }

            //printer device validation
            if(usedPrinterDevice.getPrinterName().isEmpty()){
                return ResponseHandler.generateResponse("No printer device selected. Please check your settings", HttpStatus.INTERNAL_SERVER_ERROR, transaction);
            }

            change = payment - totalPrice;

            //start printing receipt
            PrintService printService = PrinterOutputStream.getPrintServiceByName(usedPrinterDevice.getPrinterName());
            EscPos escpos;
            escpos = new EscPos(new PrinterOutputStream(printService));
            
            Style title = new Style()
                    .setFontSize(Style.FontSize._2, Style.FontSize._2)
                    .setJustification(EscPosConst.Justification.Center);

            Style subtitle = new Style(escpos.getStyle())
                    .setBold(true)
                    .setUnderline(Style.Underline.OneDotThick);
            Style bold = new Style(escpos.getStyle())
                    .setBold(true);

            EscPos customReceipt = 
                escpos
                .writeLF(title,"Cashier App")
                .feed(3)
                .write("Payment Method: ").writeLF(subtitle, paymentMethod.getPaymentMethod().toString())
                .feed(3)
                .writeLF(localDateTime.toString())
                .writeLF("--------------------------------");
            for(int i=0;i<itemList.size();i++){
                customReceipt.writeLF(
                    SpaceHelper.spaceHelper(
                        itemList.get(i).getItemName(),
                        transaction.getTransactionDetail().get(i).getAmount(),
                        transaction.getTransactionDetail().get(i).getAmount()*itemList.get(i).getItemPrice()
                    )
                );
            }

            customReceipt.feed(2)
            .writeLF("--------------------------------")
            .writeLF(bold,"Total        Rp."+totalPrice)
            .writeLF(bold,"Payment      Rp."+payment)
            .writeLF(bold,"Change       Rp."+change)
            .writeLF("--------------------------------")
            .feed(5)
            .cut(EscPos.CutMode.FULL);
            
            escpos.close();
            //end printing receipt
            
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, transaction);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
