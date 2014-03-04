//***************************************************************
// Copyright (C) 2013 Baraan Soft
//***************************************************************
//      Project Name : DataUsageCalculator
//      Created At   : 03-Mar-2014
//      Author       : prasad
//      Class Name   : ListAdapter.java
//
//***************************************************************
// Class Description :
//
//***************************************************************
package com.example.datausagecalculator;

import java.util.List;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author prasad
 *
 */
public class ListAdapter extends ArrayAdapter<RunningAppProcessInfo>{

	private Context context;
	private List<RunningAppProcessInfo> runningProcesses;
	/**
	 * @param context
	 * @param runningProcesses
	 */
	public ListAdapter(Context context, List<RunningAppProcessInfo> runningProcesses) {
		super(context, R.layout.main, runningProcesses);
		this.context = context;
		this.runningProcesses = runningProcesses;
	}

	private ViewHolder viewHolder;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.main, null);
			viewHolder = new ViewHolder();
			viewHolder.appInfo = (TextView) convertView.findViewById(R.id.textview);
			viewHolder.sent = (TextView) convertView.findViewById(R.id.tv_sent);
			viewHolder.received = (TextView) convertView.findViewById(R.id.tv_received);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		RunningAppProcessInfo processInfo = runningProcesses.get(position);
		PackageManager pm = context.getPackageManager();
		long send = TrafficStats.getUidTxBytes(processInfo.uid);
		long received = TrafficStats.getUidRxBytes(processInfo.uid);
		CharSequence name = "";
		Drawable left = getContext().getResources().getDrawable( R.drawable.ic_launcher );
		try {
			name = pm.getApplicationLabel(pm.getApplicationInfo(processInfo.processName, PackageManager.GET_META_DATA));
			left = pm.getApplicationLogo(pm.getApplicationInfo(processInfo.processName, PackageManager.GET_META_DATA));
		} catch (NameNotFoundException e) {
			name = processInfo.processName;
			e.printStackTrace();
		}
		viewHolder.appInfo.setText(name);
		viewHolder.appInfo.setCompoundDrawables(left, null, null, null);
		if (send == -1 || received == -1) {
			viewHolder.sent.setText("NA");
			viewHolder.received.setText("NA");
		} else if(send > 0 || received > 0){
			viewHolder.sent.setText(send/1000+" kb");
			viewHolder.received.setText(""+received/1000+"kb");
		} else {
			viewHolder.sent.setText("0 kb");
			viewHolder.received.setText("0kb");
		}
		return convertView;
	}
	
	/**
	 * 
	 */
	private void getAppLabel() {
		
	}
	
	class ViewHolder {
		TextView appInfo;
		TextView sent;
		TextView received;
	}
}
