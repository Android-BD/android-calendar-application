package com.example.android_calendar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CalendarListView extends Activity{
	
	String filename = "wsuCalendarApp.txt";
	public String delims = "[-#:]+";
	int i = 0;
	int j=0;
	int k=2;

	public String checkIfDate;
	int isInt;
	
	@Override
	public void onCreate(Bundle savedInstanceState){

        SharedPreferences sp = getSharedPreferences(filename, MODE_PRIVATE);
      	String savedString = sp.getString("calData", null);
      	
      	String[] menuItems = savedString.split(delims); 
      	
		String[] date = savedString.split(delims);
		int arrayLength = date.length;
		String[] datesForMenu = new String[arrayLength];
      	
		for(i=0;i<arrayLength;i++){
//			checkIfDate=date[i];
//			isInt = Integer.parseInt(checkIfDate);
			//if(date[i].equals("25"))
			datesForMenu[j]=date[i];			
			j++;
		}
      	
         super.onCreate(savedInstanceState);
         setContentView(R.layout.list_layout);

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                         android.R.layout.simple_list_item_1, datesForMenu);

         ListView mylist = (ListView) findViewById(R.id.mainlist);
         mylist.setAdapter(adapter);
 }
}
