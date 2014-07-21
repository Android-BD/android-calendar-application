package com.example.android_calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;



public class CalendarAdapter extends BaseAdapter{
	private Context mContext;
	int i,j,k=0;
	//private String splitItems[][];
	int arrayLength;
	Calendar calendar = Calendar.getInstance();
	int month = calendar.get(Calendar.MONTH);
	int currMonth = (month+1);
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	public TextView currentMonth;
	SimpleDateFormat simpleDate = new SimpleDateFormat("LLLL"); //This formats it to the months full name.
	public String thisMonth = simpleDate.format(calendar.getTime()); //Hold the current value for this current month
	public int[] totalDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //Integer array to hold all possible lengths of days
	public int[] calendarDays = new int[50];
	public int dayCount; 
	ArrayList<String> newDateList = new ArrayList<String>();
	static String filename = "wsuCalendarApp.txt";
	public String delims = "[-#]+";
	public String[] date;
	List<Integer> daysList = new ArrayList<Integer>();
	List<Integer> monthList = new ArrayList<Integer>();

	
	public CalendarAdapter(Context c){
		this.mContext = c; //I don't know what this does
		
		//Get the amount of days in a month
		dayCount = daysOfMonth(month);		
		
		//This for loop will populate the array with the correct number of days to show
		for(i=1;i<dayCount+1;i++){
			calendarDays[i]=i;
			newDateList.add(Integer.toString(calendarDays[i]));
		}
		
		//Get the shared preferences
		SharedPreferences sp = mContext.getSharedPreferences(filename, 0);
		String savedString = sp.getString("calData", null);
		date = savedString.split(delims);
		arrayLength=date.length;
		String[] datesForMenu = new String[arrayLength];
		
		//Completed for loop that gets the dates that are needed to be shown as food.
		for(i=2;i<arrayLength;i=i+5){
			datesForMenu[j]=date[i];
			k=j-1;
				if(datesForMenu[j].length() < 3){
					daysList.add(Integer.parseInt(datesForMenu[j]));
				}				
			j++;
		}//End for
		
		//A non-working for loop that is supposed to only highlight certain days based off the correct
		//Month being chosen. 
		for(i=1;i<arrayLength;i=i+5){
			datesForMenu[j]=date[i];			
			if(datesForMenu[j].length() < 3 && Integer.parseInt(datesForMenu[j])==currMonth){
				monthList.add(Integer.parseInt(datesForMenu[j]));
			}
		}
		
	}

	@Override
	public int getCount() {
		return dayCount + 1;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//TextView of whatever the day of the calendar is
		TextView theDay;
		
		//Set up the calendar's view
		if(convertView == null){
			theDay = new TextView(mContext);
			theDay.setLayoutParams(new GridView.LayoutParams(200,200));
						
			//If statement to set the current day to a certain color
			if(day == calendarDays[position])
			theDay.setTextColor(Color.MAGENTA);
			
			//If statement for the calendar to show days that have food in them 
			if(daysList.contains(calendarDays[position]))
			theDay.setTextColor(Color.RED);

		}
		else{
			theDay = (TextView) convertView;
		}
		//Choose what is put into the calendar
		theDay.setText(""+calendarDays[position]);
		return theDay;
	}
	
	//Function to return how many days are in the current month
	private int daysOfMonth(int a){
		return totalDays[a];
	}
}
