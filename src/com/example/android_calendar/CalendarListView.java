package com.example.android_calendar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 This is the class file that handles all of the list view operations for the applications.
 This class has the position value from the intent created in CalendarGridView passed to it
 So that it know what day to open.
 */

public class CalendarListView extends Activity{
	String filename = "wsuCalendarApp.txt";
	public String delims = "[-#:]+";
	int i,j = 0;
	List<String> menuChoices = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		//Take the value passed in the position from CalendarGridView
		Bundle pos = getIntent().getExtras();
		int position = pos.getInt("position");

		//Get shared preferences 
        SharedPreferences sp = getSharedPreferences(filename, MODE_PRIVATE);
      	String savedString = sp.getString("calData", null);
      	
      	//Save the menu items in an array, parse them with a delimiter
      	String[] date = savedString.split(delims);
		int arrayLength = date.length;
		
		//For loop to loop through the menu items and if a day matches the position value passed
		//from the CalendarGridView intent, then add the menu items into the menuChoices List.
		for(i=2;i<arrayLength;i=i+6){			
			if(Integer.parseInt(date[i])==position){
			menuChoices.add(date[i+1]);
			menuChoices.add(date[i+2]);
			menuChoices.add(date[i+3]);
			}

		}//End for	
      	
		
         super.onCreate(savedInstanceState);
         setContentView(R.layout.list_layout);

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                         android.R.layout.simple_list_item_1, menuChoices);

         ListView mylist = (ListView) findViewById(R.id.mainlist);
         mylist.setAdapter(adapter);
 }
	
}
