package com.example.android_calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class readSharedPref extends Activity {
	EditText temp0, temp1, temp2;
	String filename = "wsuCalendarApp.txt";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpref_layout);

        temp0 = (EditText)findViewById(R.id.editText1);

	}
	
	public void onClickLoad(View view)
	{
        SharedPreferences sp = getSharedPreferences(filename, MODE_PRIVATE);
		String savedString = sp.getString("calData", null);
		temp0.setText(savedString);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

    	switch (item.getItemId()) {
    	case R.id.calendarOption:
    		//do something
    		Intent gridIntent = new Intent(this,CalendarGridView.class);
    		startActivity(gridIntent);
    		return true;
    	case R.id.listOption:
    		//do something
    		Intent listIntent = new Intent(this,CalendarListView.class);
    		startActivity(listIntent);
    		return true;
    	case R.id.updateOption:
    		//do something
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
