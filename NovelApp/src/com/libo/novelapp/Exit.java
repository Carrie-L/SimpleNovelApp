package com.libo.novelapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

public class Exit implements OnClickListener {
	private Context context;

	public Exit(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		Activity activity = null;
	
		
		System.exit(0);

	}

	public void exit() {
		Activity activity = null;
		
		
		System.exit(0);

	}
}
