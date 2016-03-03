package com.st.code;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BoundServiceClientActivity extends Activity {
    SumBoundService sumService;
    EditText editNumber;
    TextView textSum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boundserviceclient);
        editNumber = (EditText) findViewById(R.id.editNumber);
        textSum = (TextView) findViewById(R.id.textSum);
        Intent intent = new Intent(this, SumBoundService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    public void getSum(View v) {
        int sum = sumService.getSum(Integer.parseInt(editNumber.getText().toString()));
        textSum.setText(String.valueOf(sum));
    }
    private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            SumBoundService.SumBinder binder = (SumBoundService.SumBinder) service;
            sumService = binder.getService();

        }

        public void onServiceDisconnected(ComponentName arg0) {
       
        }
    };
}

Layout - boundserviceclient.xml
================================

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <EditText
        android:id="@+id/editNumber"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <Button
        android:id="@+id/btnGetMessage"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="getSum"
        android:text="Get Sum" />
    
    <TextView
        android:id="@+id/textSum"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:gravity="center" />
</LinearLayout>

