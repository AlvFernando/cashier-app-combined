package com.cashier.app.cashierApp.Helper;

public class SpaceHelper {
    public static String spaceHelper(String itemName,Integer quantity, Integer price){
        Integer spacesLeft = 23 - itemName.length() - String.valueOf(quantity).length();
        Integer spacesRight = 32 - 23 - String.valueOf(price).length();
        String spacesStringLeft = "";
        String spacesStringRight = "";
        for(int i=0;i<spacesLeft;i++){
            spacesStringLeft+=" ";
        }
        for(int i=0;i<spacesRight;i++){
            spacesStringRight+=" ";
        }
        return itemName+spacesStringLeft+quantity+spacesStringRight+price;
    }
}
