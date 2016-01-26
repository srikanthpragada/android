package com.st.devicefeaturesdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MakeCallWithIntent extends Activity {

	private EditText editPhoneNumber;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_makecall);
		editPhoneNumber = (EditText)  this.findViewById(R.id.editPhoneNumber);
		
    }
	
    public void makeCall(View v) {
    	Intent  intent = new Intent( Intent.ACTION_CALL,
				  Uri.parse( "tel:"  + editPhoneNumber.getText().toString()));
    	startActivity(intent);
    } 
}
