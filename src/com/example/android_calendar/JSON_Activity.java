package com.example.android_calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class JSON_Activity extends Activity{
	
	private String url1 = "http://www.google.com/calendar/feeds/gqccak2junkb7eup9ls76k919c@group.calendar.google.com/public/full?alt=json&orderby=starttime&max-results=15&singleevents=true&sortorder=ascending&futureevents=true";
	private EditText temp0, temp1, temp2, temp3;
   	private HandleJSON obj;
   	private String splitItems[][];
   	int arrayLength;
   	int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.json_main);

        temp0 = (EditText)findViewById(R.id.editText1);
        temp1 = (EditText)findViewById(R.id.editText2);
        temp2 = (EditText)findViewById(R.id.editText3);
        temp3 = (EditText)findViewById(R.id.editText4);
	}

    public void open(View view){
        //String url = location.getText().toString();
        //String finalUrl = url1 + url;
        //String finalUrl = url1;
        //country.setText(finalUrl);
        obj = new HandleJSON(url1);
        obj.fetchJSON();

        while(obj.parsingComplete);
        
        splitItems = obj.getArray();

        temp0.setText(splitItems[0][0]);
        temp1.setText(splitItems[1][1]);
        temp2.setText(splitItems[2][2]);
        temp3.setText(splitItems[3][0]);
        //temp0.setText(items[0]);
        //temp1.setText(items[1]);
        //temp2.setText(items[2]);
        //temp3.setText(items[3]);
        /*arrayLength = items.length;
        do {
        	//String _temp = "temp" + i;
        	//_temp.setText(items[i]);
        	i++;
        } while (i < arrayLength);*/
    }
}
