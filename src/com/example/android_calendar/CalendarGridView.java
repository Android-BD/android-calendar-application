package com.example.android_calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class CalendarGridView extends Activity{

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_layout);
		
		GridView gridview = (GridView) findViewById(R.id.calendargrid);
		gridview.setAdapter(new TextAdapter(this));
		//gridview.setAdapter(new ImageAdapter(this));
		
		gridview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View v, int position, long id){
				Toast.makeText(CalendarGridView.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

}
