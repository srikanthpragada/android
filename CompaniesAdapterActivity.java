package com.st.advancedtopics;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;

public class CompaniesAdapterActivity extends ListActivity {

	private  ArrayList<Company>  companies;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		companies = new ArrayList<Company>();
		companies.add( new Company("Srikanth Technologies","9059057000","http://www.srikanthtechnologies.com"));
		companies.add( new Company("Microsoft","18001021100","http://www.microsoft.com"));
		companies.add( new Company("Dell","18004254051","http://www.dell.com"));
		
		CompaniesAdapter adapter = new CompaniesAdapter(this,companies);
		getListView().setAdapter(adapter);
	}

	
	
	
}


