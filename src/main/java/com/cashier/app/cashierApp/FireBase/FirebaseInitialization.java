package com.cashier.app.cashierApp.FireBase;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;

@Service
public class FirebaseInitialization {
    @PostConstruct
    public void initialization(){
        // FileInputStream serviceAccount = null;
        String accountServiceJson = "{\r\n"+
            "\"type\": \"service_account\",\r\n"+
            "\"project_id\": \"cashier-app-82466\",\r\n"+
            "\"private_key_id\": \"4db588a1cd9994d60bd7f0218bdc08be2538ad5f\",\r\n"+
            "\"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkBhJo5Xzf5RtC\\nelAZz05DVXzet95Nq8Y5TKEOQDL+2lohcIaq/nCD675oUUKmiw/lmzR4yBMqlp5+\\nrarrZj3U1z6KX4UNq25n6KZ951euPb5ikjI3PrhUIjK1aO6tXEsGR1OF0uSHGWbH\\nRP4LWxllMC8gLfpCJgJDPOj4GbBHUn+9QgoUviJFxKaMe2oXpMsVK8sScgwoik9U\\nBHDFjTGpDEfjZyssA9zJz8hYDCXiQZTAz1ZAxOWPGEPEPERD9UORRbkoLlrRJ7X/\\nM/GE3efKQnOzzg2f1f68768M1JgQOY+19xJ921fc8j6rAOUi/zwrejF99gXLAQJ0\\nyvf8PtUtAgMBAAECggEAIC5hy8tkz//hNI6PazTxf8QE0yTDnL0cT+8xNTzS1I5O\\nOzva7C8uL6/f5JM27rqfhJbKOX1Vx1gUYjncLEHb1Ufi6R67Gfa922y5x7+NRC4w\\njaHt8MlhPH6WMOIU3BYc0S9K25mIRVpuQCAQ8PTve1s0D839iR9wd7U6Zz0RCTj9\\nUKVqetr9ivGu7B9xH8LA8jQ2ZblBetA7YJG5W5x28ZnBYwNW7QR4OnQw9g3xxwYY\\ne50QOhZE26E9BhS2l22kl+eXjfWXtiGEc3s+kYJVHEhFWZUQF2GwUYilpnsz1UWK\\nRu+kmQt50QlDu27P8EHHLM+Hs4FFEpd/bo6Cv/jAiQKBgQDlrxWfFVVjwx/uRyOs\\nGnz91vapoksQJ1HuReENTKxFu8lSqHH3iATS5t24TVS+IJDWPrxBJhGs9FVFRtiB\\nbeiU+fmw/wQE4j3/GOxHtzOx0dck1EhK44YYx/nKZa0m18lK5+bHVnF6cK1WUX9R\\n//jvSwVYaxjvGhohw1PfaF9kyQKBgQC20ReD+8qNFihZNRWePUQjfcj4horV/xDY\\nYE6pwCrwS62LCw0iT/SuS3tAA32+/YF4G/pthh8OdVcOpl5Tzbe/ghCeOhNSbgKO\\nBUUebrvfgGhea5oOC0MgnEorxEoX4e4FQS5uRTiueKqHoDftbzvEsdq6pDe9U1JC\\nMYTEhT/TRQKBgQCYAq5+oPHomsVRQAfd6ZH7tf99+YlQYZWZ6umtkacaZ9QwPode\\n2cjHhEANsDB7vLXe9ZpGS9m66+JbfS3hS0p4/pBAkiaozMtXP7lxJVCzSTvohg5q\\ncfbYA9HQIEbexEXWq3BTUWS9MkGmNT3cmVq5lJ8vC4JvDIlq+wlRfJRV8QKBgBp4\\nQdBhV51/9M2dywiDVGdTIhMh395GNRkvMJSRd2Ydfv4f1oH+U64czbaiCsXpjKJo\\nYqUTA1IjmTxMyGGX6p/25BMU05Fezk3j43MZk2gz/miVadPYu5pwJqkZnaTBmKX4\\nHxHmQeO5Iu/KOe7N6Th8i+/0MQHfm4xsAQ43QNuFAoGALqHS54hHt+REs6gENemM\\nl+901b1XUSd/wKedjGdOxFZviyp382FFtOIsXlMp3VntOrsJ1kcvXRFC5QExNLMC\\negFHbBQpyt99YP1GernoA93bZQP9s9FUJ4oRiFOKLhKXrpkBcUmfYbvt2Ho8OBCQ\\nWsR+ahyhgGkrSu6kfwivEg0=\\n-----END PRIVATE KEY-----\\n\",\r\n"+
            "\"client_email\": \"firebase-adminsdk-dyg5h@cashier-app-82466.iam.gserviceaccount.com\",\r\n"+
            "\"client_id\": \"112712334780796384802\",\r\n"+
            "\"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n"+
            "\"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n"+
            "\"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n"+
            "\"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-dyg5h%40cashier-app-82466.iam.gserviceaccount.com\"\r\n"+
          "}\r\n";

        try {
            //serviceAccount = new FileInputStream("./accountService.json");
            ByteArrayInputStream inputStream = new ByteArrayInputStream(accountServiceJson.getBytes(StandardCharsets.UTF_8));
            FirebaseOptions options;
            options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(inputStream))
            .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        
    }
}