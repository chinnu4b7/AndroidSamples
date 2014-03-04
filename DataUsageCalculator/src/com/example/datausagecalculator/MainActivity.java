package com.example.datausagecalculator;

import java.util.List;

import android.app.ActivityManager;
import android.app.ListActivity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends ListActivity
{

	private static final String TAG = "HI";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningProcesses = activityManager.getRunningAppProcesses();
		if (runningProcesses != null && runningProcesses.size() > 0) {
			setListAdapter(new ListAdapter(this, runningProcesses));
		} else {
			Toast.makeText(getApplicationContext(), "No application was running.", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
