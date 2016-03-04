package com.st.code;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SumActivity extends Activity {

	EditText editNumber;
	TextView textSum;
	int sum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sum);
		editNumber = (EditText) findViewById(R.id.editNumber);
		textSum = (TextView) findViewById(R.id.textSum);
	}

	public void reset(View v) {
		editNumber.setText("");
		textSum.setText("");
	}


	public void calculateSum(View v) {
		SumThread st = new SumThread();
		st.start();
	}
	
	class UpdateView implements Runnable {
		@Override
		public void run() {
			textSum.setText(String.valueOf(sum));
		}
	}
	// inner class for worker thread
	class SumThread extends Thread {
		
		  public void run() {
			    int num = Integer.parseInt(editNumber.getText().toString());
				sum = 0;
				for (int i = 1; i <= num; i++) {
					try {
						Thread.sleep(1000);
					}
					catch (Exception ex) {
					}
					sum += i;
				}
				textSum.post( new UpdateView());  // Runnable that runs in UI Thread
		  }
	}
}

Layout -  activity_sum.xml
--------------------------
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/LinearLayout1"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".SumActivity" >

    <TextView
        android:id="@+id/textView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Enter a number : " />

    <EditText
        android:id="@+id/editNumber"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:ems="10"
        android:inputType="number" >

        <requestFocus />
    </EditText>

    <Button
        android:id="@+id/btnCalculateSum"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="calculateSum"
        android:text="Calculate Sum" />

    <TextView
        android:id="@+id/textSum"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge" />

    <Button
        android:id="@+id/btnReset"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="reset"
        android:text="Reset" />

</LinearLayout>


