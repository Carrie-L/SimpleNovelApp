package com.libo.novelapp;


import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.libo.helper.BookDB;;

public class MyApplication extends Application {
	
	public static BookDB bookDB;
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		initDateBase();
	}

	private static void initDateBase() {
		bookDB = new BookDB(context, "book");
	}
	

	
}
