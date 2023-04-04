package com.cashier.app.cashierApp.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.cashier.app.cashierApp.Model.Entity.ProductKey;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ProductKeyService {
    private static final String COLLECTION_NAME = "product_key";

    //select all
    public List<ProductKey> getProductKey() throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
    
        Iterable<DocumentReference> documentReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();
    
        List<ProductKey> productKeyList = new ArrayList<>();
        ProductKey productKey = null;
    
        while(iterator.hasNext()){
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();
            productKey = document.toObject(ProductKey.class);
            productKeyList.add(productKey);
        }
    
        return productKeyList;
    }

    //null safety with annotation
    //select 1 by name
    public ProductKey getProductKey(@javax.annotation.Nonnull String key) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(key);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        ProductKey productKey = null;
        if(document.exists()){
            productKey = document.toObject(ProductKey.class);
            return productKey;
        }else{
            ProductKey productKey2 = new ProductKey(
                false, "null"
            );
            return productKey2;
        }
    }

    //update spesific field
    public String update(@javax.annotation.Nonnull Boolean isActive,@javax.annotation.Nonnull String uniqueKey) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();        
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(uniqueKey).update("isActive",isActive);
        return collectionApiFuture.get().getUpdateTime().toString();
    }
}
