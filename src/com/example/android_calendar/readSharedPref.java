package com.example.android_calendar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
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
	
}
