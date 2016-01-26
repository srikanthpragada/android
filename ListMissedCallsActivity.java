package com.st.devicefeaturesdemo;

import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.widget.TextView;

public class ListMissedCallsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_missed_calls);
		TextView tv = (TextView) findViewById(R.id.textMissedCalls);
		tv.setText("");

		final String sortOrder = CallLog.Calls.DATE + " DESC";
		Cursor cursor = null;
		try {
			cursor = getContentResolver().query(
					Uri.parse("content://call_log/calls"), null, null, null,
					sortOrder);
			if (cursor != null) {
				Log.d("MissedCalls","Got Calls Details");
				while (cursor.moveToNext()) {
					String callNumber = cursor
							.getString(cursor
									.getColumnIndex(CallLog.Calls.NUMBER));
					String callDate = cursor
							.getString(cursor
									.getColumnIndex(CallLog.Calls.DATE));
					
					long  secs = Long.parseLong(callDate);
					Date d = new Date (secs);
					String callType = cursor
							.getString(cursor
									.getColumnIndex(CallLog.Calls.TYPE));

					if (Integer.parseInt(callType) == CallLog.Calls.MISSED_TYPE) {
						tv.append( "Number : "  + callNumber + "\nAt : " + d.toString() + "\n\n");
					}
				}
			} else
				Log.d("MissedCalls", "No missed calss!");
			
			

		} catch (Exception ex) {
			Log.d("MissedCalls", ex.getMessage());
		} finally {
			if ( cursor != null)
				 cursor.close();
		}
	}

}
