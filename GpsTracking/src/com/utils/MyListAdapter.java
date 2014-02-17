package com.utils;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gpstracking.R;

public class MyListAdapter extends BaseAdapter{

	private LayoutInflater mLayoutInflater;
	private ViewHolder viewHolder = null;
	
	Activity context;
	List<Points> pointsList;
	
	public MyListAdapter(Activity context,List<Points> pointsList) {
		this.context = context;
		this.pointsList = pointsList;
	}
	
	@Override
	public int getCount() {
		return pointsList.size();
	}

	@Override
	public Object getItem(int position) {
		return pointsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return pointsList.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) 
		{
			mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mLayoutInflater.inflate(R.layout.points_list_item, null);

			viewHolder = new ViewHolder();
			viewHolder.xLabel = (TextView) convertView.findViewById(R.id.x_label);
			viewHolder.yLabel = (TextView) convertView.findViewById(R.id.y_label);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.xLabel.setText(""+pointsList.get(position).getLatitude());
		viewHolder.yLabel.setText(""+pointsList.get(position).getLongitude());
		return convertView;
	}

	private final class ViewHolder 
	{
		public TextView xLabel;
		public TextView yLabel;
	}
}
