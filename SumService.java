package com.st.code;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SumService extends Service {
    public SumService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Code", "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Code", "onDestroy()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int number = intent.getIntExtra("number",0);
        Log.d("Code","Procesing number =  " + number);
        int sum =0;
        for(int i =1; i <= number; i ++) {
            try {
                Thread.sleep(1000);
            }
            catch(Exception ex) {

            }
            sum += i;
        }

        Log.d ("Code","Sum = " + sum);
        stopSelf(); // stop service
        return  START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
