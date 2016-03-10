package com.st.storage;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteCourseFromCPActivity extends Activity {
	private EditText editName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_course_row);
		editName = (EditText) this.findViewById(R.id.editName);
    }


	public void deleteCourse(View v) {
		try {
			int count = getContentResolver().delete(
					    Uri.parse("content://com.st.courses"),
                        "name = ?",
					    new String[] {editName.getText().toString()});

            if (count == 1)
			  Toast.makeText(this, "Deleted Course Successfully!",
					Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(this, "Course Not Found!",
						Toast.LENGTH_SHORT).show();
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}


Layout - activity_delete_course_row.xml
=========================================
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <TextView
        android:id="@+id/textView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Course Name" />

    <EditText
        android:id="@+id/editName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

        <requestFocus />
    </EditText>



    <Button
        android:id="@+id/button1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="deleteCourse"
        android:text="Delete Course" />

</LinearLayout>
