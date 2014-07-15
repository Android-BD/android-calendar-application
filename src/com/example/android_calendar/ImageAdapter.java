package com.example.android_calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter{
	private Context context;
//	private Integer[] imageIds = {R.drawable.rect2985};
	
	public ImageAdapter(Context context){
		this.context=context;
	}

	@Override
	public int getCount() {
	//	return imageIds.length;
		return 1;
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
		ImageView imageView;
		if(convertView==null){
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(10,10));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(5,5,5,5);
		}
		else{
			imageView = (ImageView) convertView;
		}
		
	//	imageView.setImageResource(imageIds[position]);
		return imageView;
	}

}
