package com.libo.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * ��ǩ���ݿ�Ľ���
 * 
 * @author
 * 
 */
public class MarkHelper extends SQLiteOpenHelper {
	private static String DATABASE_NAME = "mark.db";
	private static int DATABASE_VERSION = 5;
	private String PATH = "path";
	private String s = null;

	public MarkHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.s = "markhelper";
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("TAG", s);
		db.execSQL("DROP TABLE IF  EXISTS " + s +";");
		String sql ="CREATE TABLE " + s + " ( " + PATH + " text not null, "+ " _id integer primary key,"
				+ "begin int not null default 0,"
				+ " word text not null , time text not null);";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
