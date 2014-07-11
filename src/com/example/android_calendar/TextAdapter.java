package com.example.android_calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class TextAdapter extends BaseAdapter{
	private Context context;
	private String[] dates = {"1", "2", "3", "4", "5", "6"};
	
	public TextAdapter(Context context){
		this.context=context;
	}

	@Override
	public int getCount() {
		return dates.length;
	}

	@Override
	public Object getItem(int position) {
		return 0;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv;
		if(convertView==null){
			tv = new TextView(context);
			tv.setLayoutParams(new GridView.LayoutParams(85,85));
		}
		else{
			tv = (TextView) convertView;
		}
		
		tv.setText(dates[position]);
		return tv;
	}
	
	

}
