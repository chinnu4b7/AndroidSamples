package com.example.autostartatboot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartAtBootServiceReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("HI", "--ON-RECEIVE called--");
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			Intent i = new Intent();
			i.setAction("com.example.autostartatboot.Moniter");
			context.startService(i);
		}
	}
}
