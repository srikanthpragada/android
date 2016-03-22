package com.st.advancedtopics;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapterActivity extends Activity {
	ListView l1;
	List<Course> courses;
	TextView tvTotal;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_courses_adapter);
		
		l1 = (ListView) findViewById( R.id.listCourses);
		tvTotal  = (TextView) findViewById( R.id.tvTotal);
		courses = new ArrayList<Course>();
		courses.add( new Course("Java",10000,1));
		courses.add( new Course("Oracle",5000,1));
		courses.add( new Course("Android",6000,1));


		CoursesAdapter ca = new CoursesAdapter(this,courses);
		l1.setAdapter(ca);
	}

    // it changes only model -  complete it by updating view 
	public void  unselectAll(View v) {

		for(Course c : courses) {
		     c.setSelected(false); // change model
		}
		tvTotal.setText("0");
	}


	public void  updateTotal() {
		int total = 0;
		for(Course c : courses) {
			if ( c.isSelected())
			   total += c.getFee();
		}
        tvTotal.setText( String.valueOf(total));


	}
	

}
