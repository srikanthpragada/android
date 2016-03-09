package com.st.storage;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListSongsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_songs);
		try {

			List<String> names = new ArrayList<>();
			Cursor albums = getContentResolver().query( MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
		 
			while (albums.moveToNext()) {
				String title = albums.getString(albums.getColumnIndex(MediaStore.Audio.Media.TITLE));
				int duration = albums.getInt(albums.getColumnIndex(MediaStore.Audio.Media.DURATION));
				names.add( title + " - " + duration / (1000 * 60) + " mins" );
			}

	 		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1  , names);
		    ListView listCourses = (ListView) this.findViewById( R.id.listAlbums);
		    listCourses.setAdapter(adapter);
			
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}

Layout - activity_list_songs.xml
=================================
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <ListView
        android:id="@+id/listSongs"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >
    </ListView>

</LinearLayout>
