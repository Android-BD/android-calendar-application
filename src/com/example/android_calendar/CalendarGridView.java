package com.example.android_calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class CalendarGridView extends Activity{
	
	static String filename = "wsuCalendarApp.txt";
	public String delims = "[#:]+";
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_layout);
		
        SharedPreferences sp = getSharedPreferences(filename, MODE_PRIVATE);
		String savedString = sp.getString("calData", null);
		
		GridView gridview = (GridView) findViewById(R.id.calendargrid);
		//gridview.setAdapter(new TextAdapter(this));
		//gridview.setAdapter(new ImageAdapter(this));
		
		String[] date = savedString.split(delims);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,date);
		gridview.setAdapter(adapter);
		
		gridview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View v, int position, long id){
				Intent listsIntent = new Intent(getApplicationContext(),CalendarListView.class);
				startActivity(listsIntent);
				Toast.makeText(CalendarGridView.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

}
