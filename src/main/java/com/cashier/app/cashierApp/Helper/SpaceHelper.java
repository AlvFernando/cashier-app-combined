package com.cashier.app.cashierApp.Helper;

public class SpaceHelper {
    public static String spaceHelper(String itemName,Integer quantity, Integer price){
        String initialValue = itemName+quantity+price;
        Integer spaces = 32-initialValue.length()-1;
        String spacesString = "";
        for(int i=0;i<spaces;i++){
            spacesString+=" ";
        }
        return itemName+spacesString+quantity+" "+price;
    }
}
