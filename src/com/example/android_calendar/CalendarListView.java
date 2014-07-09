package com.example.android_calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CalendarListView extends Activity{
	
	public void onCreate(Bundle savedInstanceState){
        String[] items = { "Day 1", "Day 2",
                         "Day 3", "Day 4",   "Day 5",
                         "Day 6", "Day 7", "Day 8" };
         
         super.onCreate(savedInstanceState);
         setContentView(R.layout.list_layout);

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                         android.R.layout.simple_list_item_1, items);

         ListView mylist = (ListView) findViewById(R.id.mainlist);
         mylist.setAdapter(adapter);
 }



}
