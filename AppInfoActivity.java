package com.st.devicefeaturesdemo;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
  
public class AppInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_info);
		TextView tv = (TextView) findViewById(R.id.textAppInfo);
		try {
		  PackageManager pm = this.getPackageManager();
		  for (ApplicationInfo ainfo: pm.getInstalledApplications( PackageManager.GET_META_DATA)) {
			  tv.append( pm.getApplicationLabel(ainfo)  + "\n"); // Application name
			  tv.append( ainfo.packageName + "\n\n");  // package name
		  }
		}
		catch(Exception ex) {
			tv.setText("Error :" + ex.getMessage());
		}
	}

	
}
