package com.example.android_calendar;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

public class HandleJSON extends JSON_Activity {
   private String urlString = null;
   int arrayLength;
   int i=0;
   
   String[] items = { "Day 1", "Day 2","Day 3", "Day 4" },
		    parts = { "0.", "1.", "2."};
   String[][] splitItems =	{  { "1", "2","3" },
							   { "1", "2","3" },
							   { "1", "2","3" },
							   { "1", "2","3" },
   					  		};
   String[] _temp = { "Day 5", "Day 6", "Day 7", "Day 8" };
   String filename = "wsuCalendarApp.txt";
   String dataStore = "";
   FileOutputStream outputStream;  
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
        	  
              String[] parts = items[i].split("\\.");
              //[menuOfTheDay][item#]
              //parts[0] is just the #1
              splitItems[i][0] = parts[1];
              splitItems[i][1] = parts[2];
              splitItems[i][2] = parts[3];
              dataStore += splitItems[i][0] + "#" + splitItems[i][1] + "#" + splitItems[i][1] + ":";
        	  i++;} while (i < arrayLength);
          
         //Shared Preference saved
          /*SharedPreferences sp = getSharedPreferences(filename, MODE_PRIVATE);
          SharedPreferences.Editor spe = sp.edit();
          spe.putString("calData", dataStore);
          spe.commit();*/
          
          /*  Save to internal storage file
          try {
            outputStream = openFileOutput(filename, MODE_PRIVATE);
            //splitItems[i][2] = outputStream.getFileStreamPath("wsuCaledarApp.txt");
            outputStream.write(dataStore.getBytes());
            outputStream.close();
          } catch (Exception e) {
            e.printStackTrace();
          }*/
          
         //FileOutputStream fOut = openFileOutput("wsuCalendarApp");//,MODE_WORLD_READABLE);
         // String str = "data";
         // fOut.write(str.getBytes());
         // fOut.close();
          
         //Read A File
         /* FileInputStream fin = openFileInput(file);
          int c;
          String temp="";
          while( (c = fin.read()) != -1){
             temp = temp + Character.toString((char)c);
          }
          //string temp contains all the data of the file.
          fin.close();*/
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