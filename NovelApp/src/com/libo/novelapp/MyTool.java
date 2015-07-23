package com.libo.novelapp;

import android.app.Activity;
import android.content.Context;
import android.view.Display;

public class MyTool {
	public static Context context;
	private static Display display;

	public static int getWidth() {
		if (context==null) {
			return 720;
		}
		try {
			display = ((Activity) context).getWindowManager().getDefaultDisplay();
			return display.getWidth();
		} catch (Exception e) {
			return 720;
		}
		
	}

	public static int getHight() {
		if (context==null) {
			return 1280;
		}
		try {
			display = ((Activity) context).getWindowManager().getDefaultDisplay();
			return display.getHeight();
		} catch (Exception e) {
			return 1280;
		}
		
	}

}
