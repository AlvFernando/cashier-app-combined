package com.cashier.app.cashierApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {
        //exit program by code
        @GetMapping("/exit")
        public void exitApplication(){
            System.exit(0);
        }
}
