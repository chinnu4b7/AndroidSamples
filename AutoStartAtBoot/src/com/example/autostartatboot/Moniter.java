package com.example.autostartatboot;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class Moniter extends Service{

	private static final String TAG = "HI";
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "--service created--");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "--service destroyed--");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "--service started--");
		createNotification();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@SuppressLint("NewApi")
	private void createNotification() {
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle(getString(R.string.app_name))
		        .setContentText(getString(R.string.app_name));
		// Creates an explicit intent for an Activity in your app
		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 
				PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_CANCEL_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(10, mBuilder.build());
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
