package com.st.code;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AsyncSumActivity extends Activity {

	EditText editNumber;
	TextView textSum, textProgress;
	int sum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_sum);
		editNumber = (EditText) findViewById(R.id.editNumber);
		textSum = (TextView) findViewById(R.id.textSum);
		textProgress = (TextView) findViewById(R.id.textProgress);
	}

	public void calculateSum(View v) {
		 SumTask task = new SumTask();
		 task.execute( editNumber.getText().toString());
	}

    class SumTask extends AsyncTask<String, Integer, Integer> {
        int progress=0;

		// UI Thread
        @Override
        protected void onPreExecute() {
        	textSum.setText("");
        	textProgress.setText( "About to start!");
		}
		// UI Thread
		@Override
		protected void onPostExecute(Integer result) {
            textSum.setText( result.toString());
            textProgress.setText( "");
		}

		// UI Thread
		@Override
		protected void onProgressUpdate(Integer... values) {
			textProgress.setText( "Adding number --> " + values[0]);
		}
		@Override
		protected Integer doInBackground(String ... number) {
			    int num = Integer.parseInt( number[0]);
				sum = 0;
				for (int i = 1; i <= num; i++) {
					try {
						Thread.sleep(1000);
					}
					catch (Exception ex) {
					}
					progress ++;
					sum += i;
					this.publishProgress(progress);
				}
				return   sum;
		}
    }

}

Layout  - activity_async_sum.xml
--------------------------------

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/LinearLayout1"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".AsyncSumActivity" >

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
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge" />
    
     <TextView
        android:id="@+id/textProgress"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="" />
</LinearLayout>



