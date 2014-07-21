package com.example.android_calendar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
         mylist.setOnItemClickListener(new OnItemClickListener(){        	 
          	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        		
        		AlertDialog.Builder alertDialog = new AlertDialog.Builder(CalendarListView.this);
        			alertDialog.setTitle("");
        			alertDialog.setMessage("Choose " + parent.getItemAtPosition(position) + " as favorite?");
        			alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
        				public void onClick(DialogInterface dialog, int id){
        					//Do stuff
        				}
        			});
        			alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
        				public void onClick(DialogInterface dialog, int id){
        					dialog.cancel();
        				}
        			});
        			alertDialog.show();
        	}         	 
         });    	
 }
	

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }*/
    	switch (item.getItemId()) {
    	case R.id.calendarOption:
    		Intent gridIntent = new Intent(this,CalendarGridView.class);
    		startActivity(gridIntent);
    		return true;
    	case R.id.updateOption:
    		Intent intent = new Intent(this, JSON_Activity.class);
    		startActivity(intent);
    		return true;
    	case R.id.dataOption:
    		Intent dataIntent = new Intent(this, readSharedPref.class);
    		startActivity(dataIntent);
    		return true;
		default:
			return super.onOptionsItemSelected(item);

    	}
    }
}
