package com.stocks.oi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MongoClientTest {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate= formatter.format(date);
        System.out.println(strDate.replaceAll("/",""));
    }
}
