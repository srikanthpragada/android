package com.st.devicefeaturesdemo;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendSMSActivity extends Activity {

	private EditText editPhoneNumber;
	private EditText editMessage;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sendsms);
		editPhoneNumber = (EditText)  this.findViewById(R.id.editPhoneNumber);
		editMessage = (EditText)  this.findViewById(R.id.editMessage);
    }
	
    public void sendMessage(View v) {
    	SmsManager smsManager = SmsManager.getDefault();
    	String phoneNumber = editPhoneNumber.getText().toString();
    	
    	if (!PhoneNumberUtils.isWellFormedSmsAddress(phoneNumber))
    	{
    		Toast.makeText(this, "Invalid Phone Number!", Toast.LENGTH_LONG).show();
    		return;
    	}

    	smsManager.sendTextMessage( phoneNumber, null,editMessage.getText().toString(), null,null );
    	Toast.makeText(this, "Message Sent!", Toast.LENGTH_LONG).show();
    } 

}
