package com.st.storage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class CoursesContentProvider extends ContentProvider {
    public CoursesContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        STDatabase dbhelper = new STDatabase(getContext());
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        try {
            int count = db.delete(STDatabase.COURSES_TABLE_NAME, selection, selectionArgs);
            return count;
        }
        catch(Exception ex){
            Log.d("Storage", "Error in delete() --> " + ex.getMessage());
            return 0;
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
       return null;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        STDatabase dbhelper = new STDatabase(getContext());
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor  courses = db.query(
                STDatabase.COURSES_TABLE_NAME,null,null,null,null,null,null);
        return courses;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}
