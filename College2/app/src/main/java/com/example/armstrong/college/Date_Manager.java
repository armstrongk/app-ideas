package com.example.armstrong.college;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Date_Manager {
    Calendar day = Calendar.getInstance();
    Date today= new Date();
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
    String date;
    Context context;
    static PendingIntent pendingIntent;
    static AlarmManager alarmManager;

    public String getDate(int i) {
        i=0-i;
        date=String.valueOf(sdf.format(today));
        try {
            day.setTime(sdf.parse(date));
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        day.add(Calendar.DATE,i);
        return sdf.format(day.getTime());
    }

    public String getFormatedDate(Date date){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd '@' HH:mm:ss");
        return dateFormat.format(date);
    }

    public String dateToString(Date date){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

}

