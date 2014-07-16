package com.example.android_calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CalendarListView extends Activity{
	
	String filename = "wsuCalendarApp.txt";
	public String delims = "[#:]+";
	
	@Override
	public void onCreate(Bundle savedInstanceState){

        SharedPreferences sp = getSharedPreferences(filename, MODE_PRIVATE);
      	String savedString = sp.getString("calData", null);
      	
      	String[] menuItems = savedString.split(delims);      	
         
         super.onCreate(savedInstanceState);
         setContentView(R.layout.list_layout);

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                         android.R.layout.simple_list_item_1, menuItems);

         ListView mylist = (ListView) findViewById(R.id.mainlist);
         mylist.setAdapter(adapter);
 }



}
