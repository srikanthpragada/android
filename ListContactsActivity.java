package com.st.storage;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.widget.TextView;

public class ListContactsActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listcontacts);

		TextView textList = (TextView) this.findViewById(R.id.textList);

		Log.d("Storage", ContactsContract.Contacts.CONTENT_URI.toString());

		Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
				null,null,null,null);

		// display all columns coming from query
		for (int i = 0; i < c.getColumnCount(); i++) {
			Log.d("Storage", String.format("Postion %d   Name : %s", i, c.getColumnName(i)));
		}

		textList.setText("");
		while (c.moveToNext()) {
			// get id
			String id = c.getString(c.getColumnIndex( ContactsContract.Contacts._ID ));
			textList.append("Id : " + id + "\n");
			textList.append(c.getString(c.getColumnIndex( ContactsContract.Contacts.DISPLAY_NAME ) ) + "\n");

			// get phone numbers for this contact
			Log.d("Storage", CommonDataKinds.Phone.CONTENT_URI.toString());
			Log.d("Storage", CommonDataKinds.Phone.NUMBER.toString());

			Cursor phones = getContentResolver().query( CommonDataKinds.Phone.CONTENT_URI,
					null, CommonDataKinds.Phone.CONTACT_ID + " = ?", new  String[] { id }  ,null);
			if ( phones != null) {

				// display all columns coming from query
				for (int i = 0; i < phones.getColumnCount(); i++) {
					Log.d("Storage", String.format("Postion %d   Name : %s", i, phones.getColumnName(i)));
				}

				while (phones.moveToNext()) {
					textList.append(phones.getString(phones.getColumnIndex(CommonDataKinds.Phone.NUMBER)) + "\n");
				}
			}
			else
				textList.append("No contacts found!\n");


			// displaying email addresses

			Cursor emails  = getContentResolver().query( CommonDataKinds.Email.CONTENT_URI,
					null, CommonDataKinds.Email.CONTACT_ID + " = ?", new  String[] { id }  ,null);

			Log.d("Storage","Email URI " + CommonDataKinds.Email.CONTENT_URI);
			Log.d("Storage","Email Address column name  " + CommonDataKinds.Email.ADDRESS);

			if ( emails != null) {

				// display all columns coming from query
				for (int i = 0; i < emails.getColumnCount(); i++) {
					Log.d("Storage", String.format("Postion %d   Name : %s", i, emails.getColumnName(i)));
				}

				while (emails.moveToNext()) {
					textList.append(emails.getString(emails.getColumnIndex(CommonDataKinds.Email.ADDRESS)) + "\n");
				}
			}
			else
				textList.append("No Emails found!\n");

		}

	}

}

Layout 
=========

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

    <TextView
        android:id="@+id/textList"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="List Of Contacts" />

    </ScrollView>
</LinearLayout>
