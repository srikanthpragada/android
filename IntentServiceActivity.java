package com.st.code;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class IntentServiceActivity extends AppCompatActivity {

    EditText editNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        editNumber = (EditText) findViewById(R.id.editNumber);

    }

    public void startSumService(View v) {
        Intent intent =  new Intent(this, SumIntentService.class );
        intent.putExtra("number", Integer.parseInt(editNumber.getText().toString()));
        startService(intent);

    }

}
