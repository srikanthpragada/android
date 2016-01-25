package com.st.devicefeaturesdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SendSMSWithIntent  extends Activity {

	private EditText editPhoneNumber;
	private EditText editMessage;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sendsms);
		editPhoneNumber = (EditText)  this.findViewById(R.id.editPhoneNumber);
		editMessage = (EditText)  this.findViewById(R.id.editMessage);
    }
	
    public void sendMessage(View v) {
		Uri uri = Uri.parse("smsto:" +  editPhoneNumber.getText().toString());
    	Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
    	sendIntent.putExtra( "sms_body", editMessage.getText().toString());
    	startActivity(sendIntent);
    } 
}
