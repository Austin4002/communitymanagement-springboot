package com.gyy.boot;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SimpleTime {

    @Test
    public void test() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.add(Calendar.DAY_OF_WEEK, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(cal.getTime()));

        cal.add(Calendar.MONTH, 1);
        System.out.println(format.format(cal.getTime()));

        cal.add(Calendar.MONTH, 3);
        System.out.println(format.format(cal.getTime()));

        cal.add(Calendar.YEAR, 1);
        System.out.println(format.format(cal.getTime()));


    }

    @Test
    public void test2(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = format.format(new Date());
        try {
            Date date = format.parse(currentTime);
            System.out.println(new Date());
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        double num = 2.2222;
        num = num*100;
        System.out.println(num);
    }

    @Test
    public void test4(){
        Date date = new Date();
        long time = date.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(time);
    }
}
