package com.example.android_calendar;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class HandleJSON extends JSON_Activity {
   private String urlString = null;
   int arrayLength;
   int i=0;
   String calDate;
   String[] items = { "", "","", "" };
   String[]	parts = { "", "", ""};
   String[][] splitItems =	{  { "","","","" },
							   { "","","","" },
							   //{ "","","","" },
							   //{ "","","","" },
   					  		};
   String[] _temp = { "", "", "", "" };
   String filename = "wsuCalendarApp.txt";
   String dataStore = "";
   //FileOutputStream outputStream;  
   public volatile boolean parsingComplete = true;
   public HandleJSON(String url){
      this.urlString = url;
   }

   public String[][] getArray(){
	   return splitItems;
   }
   @SuppressLint("NewApi")
   public void readAndParseJSON(String in) {
      try {
    	  //feed, entry, title, $t : textForReturnString
    	  JSONObject parentObject = new JSONObject(in);
          JSONObject feed = parentObject.getJSONObject("feed");
    	  //_temp = feed.getString("entry");
          JSONArray entry = feed.getJSONArray("entry");
          
          arrayLength = entry.length();
          do {
        	  _temp[i] = entry.getString(i);
        	  JSONObject _json = new JSONObject(_temp[i]);
        	  JSONObject title = _json.getJSONObject("title");
        	  items[i] = title.getString("$t");   
        	  
              //gd$when[0],startTime : 2014-07-16
        	  JSONArray whenArr = _json.getJSONArray("gd$when"); 
        	  String tempDate = whenArr.getString(0);
        	  JSONObject whenObj = new JSONObject(tempDate); 
        	  calDate = whenObj.getString("startTime");
        	  
              String[] parts = items[i].split("\\.");
              //[menuOfTheDay][item#]
              //parts[0] is just the #1
              splitItems[i][0] = calDate;
              splitItems[i][1] = parts[1];
              splitItems[i][2] = parts[2];
              splitItems[i][3] = parts[3];
              dataStore += splitItems[i][0] + "#" + splitItems[i][1] + "#" + splitItems[i][1] + ":";
        	  i++;} while (i < arrayLength);
         parsingComplete = false;

        } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
        }
   }
   
   public void fetchJSON(){
      Thread thread = new Thread(new Runnable(){
         @Override
         public void run() {
         try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
         InputStream stream = conn.getInputStream();

      String data = convertStreamToString(stream);

      readAndParseJSON(data);
         stream.close();

         } catch (Exception e) {
            e.printStackTrace();
         }
         }
      });

       thread.start(); 		
   }
   static String convertStreamToString(java.io.InputStream is) {
      java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
      return s.hasNext() ? s.next() : "";
   }
}