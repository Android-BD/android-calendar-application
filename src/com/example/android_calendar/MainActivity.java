package com.example.android_calendar;

//import com.example.helloworld.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }      
    }
	public void startJSONActivity(View v) {
		Toast toast = Toast.makeText(this,
				"Opening another activity!", Toast.LENGTH_LONG);
		toast.show();
		Intent intent = new Intent(this, JSON_Activity.class);
		startActivity(intent);
	}
	
	public void startListView(View v){
		Toast toast = Toast.makeText(this, "Opening another activity!", Toast.LENGTH_LONG);
		toast.show();
		Intent listIntent = new Intent(this,CalendarListView.class);
		startActivity(listIntent);
	}
	
	public void startGridView(View v){
		Toast toast = Toast.makeText(this,  "Opening another activity!", Toast.LENGTH_SHORT);
		toast.show();
		Intent gridIntent = new Intent(this,CalendarGridView.class);
		startActivity(gridIntent);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    /**Added methods placed below*/

}
