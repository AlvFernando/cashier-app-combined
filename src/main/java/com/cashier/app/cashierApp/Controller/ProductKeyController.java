package com.cashier.app.cashierApp.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cashier.app.cashierApp.Model.ResponseHandler;
import com.cashier.app.cashierApp.Model.Entity.ProductKey;
import com.cashier.app.cashierApp.Repository.ProductKeyRepository;
import com.cashier.app.cashierApp.Service.ProductKeyService;

@RestController
@RequestMapping("/productkey")
public class ProductKeyController {
    @Autowired
    private ProductKeyService productKeyService;

    @Autowired ProductKeyRepository productKeyRepository;

    //get all
    @GetMapping("/getall")
    public List<ProductKey> getAll() throws ExecutionException, InterruptedException{
        return productKeyService.getProductKey();
    }

    @GetMapping("/isvalid")
    public ResponseEntity<Object> isKeyValid(){
        ProductKey localData = productKeyRepository.getOneById(1);
        if(localData.getIsActive().equals(false)){
            return ResponseHandler.generateResponse("App is not activated", HttpStatus.OK, localData);
        }
        return ResponseHandler.generateResponse("App is activated", HttpStatus.OK, localData);
    }

    //activation function
    @PostMapping("/activation")
    public ResponseEntity<Object> addTransaction(@RequestBody ProductKey productKey){
        try {
            //non null string check || validation input check
            if(productKey.getProductKey().isEmpty()){
                return ResponseHandler.generateResponse("input productKey is null", HttpStatus.INTERNAL_SERVER_ERROR, null); 
            }
            //check if data exist
            ProductKey data = productKeyService.getProductKey(productKey.getProductKey());

            if(data.equals(null)){
                return ResponseHandler.generateResponse("Product key not valid", HttpStatus.INTERNAL_SERVER_ERROR, null);
            }

            System.out.println(data.toString());

            if(data.getIsActive() == false){
                //update key to local database
                ProductKey localData = productKeyRepository.getOneById(1);
                localData.setProductKey(data.getProductKey());
                localData.setIsActive(true);
                productKeyRepository.save(localData);

                //update key to firebase
                String firebaseData = productKeyService.update(true, data.getProductKey());
            }else{
                return ResponseHandler.generateResponse("Product key already used", HttpStatus.OK, null);
            }

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
