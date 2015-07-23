package com.libo.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class BookDB extends SQLiteOpenHelper {
	private String TAG="BookDB.class";
	private static String DATABASE_NAME = "mbook.db";
	private static int DATABASE_VERSION = 3;
	private String PATH = "path";
	private String TYPE = "type";
	private String TABLE = null;
	
	public BookDB(Context context, String table) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.TABLE ="book";
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql = "CREATE TABLE " + TABLE + " (  _id integer primary key, "+"  bookname text not null, "+"  parent text not null, "
				+ PATH + " text not null, " + TYPE + "  text not null"
				+ ");";
		db.execSQL(sql);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 Log.d(TAG,"onUpgrade*******");
		
		
        onCreate(db);
	}
}