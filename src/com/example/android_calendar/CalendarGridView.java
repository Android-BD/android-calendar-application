package com.example.android_calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarGridView extends Activity{
	
	static String filename = "wsuCalendarApp.txt";
	public String delims = "[-#]+";
	int i,j=0;
	//private String splitItems[][];
	int arrayLength;
	public String dataStore = "";
	Calendar calendar = Calendar.getInstance();
	int month = calendar.get(Calendar.MONTH);
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	public TextView currentMonth;
	SimpleDateFormat simpleDate = new SimpleDateFormat("LLLL"); //This formats it to the months full name.
	public String thisMonth = simpleDate.format(calendar.getTime()); //Hold the current value for this current month
	public int[] totalDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //Integer array to hold all possible lengths of days
	public int[] calendarDays = new int[50];
	public int dayCount; 
	ArrayList<String> newDateList = new ArrayList<String>();
	public String[] datesForMenu = new String[100];

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_layout);
		
		//Get numerical count of days in month e.g. 28, 31, 30
		dayCount = daysOfMonth(month);
		
		//Save preference file
        SharedPreferences sp = getSharedPreferences(filename, MODE_PRIVATE);
		String savedString = sp.getString("calData", null);
		
		//Set the current month to the textview
		currentMonth = (TextView) this.findViewById(R.id.currentMonth);
		currentMonth.setText(thisMonth);
//		
//		//Set the grid view up
		GridView gridview = (GridView) findViewById(R.id.calendargrid);
		gridview.setAdapter(new CalendarAdapter(this));
//		gridview.setBackgroundColor(0x30FF0000);
//		
//
//		
//		
//		//This can be removed later.
		String[] date = savedString.split(delims);
		arrayLength = date.length;
		
		for(i=2;i<arrayLength;i=i+4){
			datesForMenu[j]=date[i];
			j++;
		}
		

		//Creating Adapter
		gridview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View v, int position, long id){
				//TextView currentDay = (TextView) v.findViewById(R.id.);
				//gridview.getChildAt(day).setBackgroundColor(Color.RED);
				Intent listsIntent = new Intent(getApplicationContext(),CalendarListView.class);
				listsIntent.putExtra("position",position+1);
				startActivity(listsIntent);
				Toast.makeText(CalendarGridView.this, "" + (position+1), Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	
	
	//Return amount of days in a month
	private int daysOfMonth(int a){
		return totalDays[a];
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
