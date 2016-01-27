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

		// final String sortOrder = CallLog.Calls.DATE + " DESC";
		Cursor cursor = null;
		try {
			cursor =getContentResolver().query(
					CallLog.Calls.CONTENT_URI,
					null,
					CallLog.Calls.TYPE  + " = " + CallLog.Calls.MISSED_TYPE ,
					null,
					CallLog.Calls.DEFAULT_SORT_ORDER);

			if (cursor != null) {
				Log.d("MissedCalls","Got Calls Details");
				while (cursor.moveToNext()) {
					String callNumber = cursor
							.getString(cursor
									.getColumnIndex(CallLog.Calls.NUMBER));

					String name  = cursor
							.getString(cursor
									.getColumnIndex(CallLog.Calls.CACHED_NAME));

					// no. of millisecond since 1-1-1970
					String callDate = cursor
							.getString(cursor
									.getColumnIndex(CallLog.Calls.DATE));
					
					long  secs = Long.parseLong(callDate);
					Date d = new Date (secs);
					String callType = cursor
							.getString(cursor
									.getColumnIndex(CallLog.Calls.TYPE));

     				tv.append( "Number : "  + callNumber + " (" + name + ")\nAt : " + d.toString() + "\n\n");

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
