package com.acadgildbatch5.services.UnBoundServicesEXMP;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Divyanshu on 15-01-2017.
 */

public class MyUnBoundService extends Service {

    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(),"On Service onCreate()",Toast.LENGTH_SHORT).show();

        startTimer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"On Destroy called ",Toast.LENGTH_SHORT).show();
        stopTimer();
    }

    public void startTimer(){
        timer = new Timer();
        inititializerTimerTask();
        timer.schedule(timerTask,5000,10000);
    }


    public void stopTimer(){
        if(timer !=null){
            timer.cancel();
            timer = null;
        }
    }

    public void inititializerTimerTask(){
        timerTask = new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:mm:yyyy HH:mm:ss a");
                        final  String strDate = simpleDateFormat.format(calendar.getTime());

                       Toast.makeText(getApplicationContext(),"Date is : "+strDate,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
    }
}
