package com.example.android_calendar;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;

public class HandleJSON {
   private String country = "county";
   private String temperature = "temperature";
   private String humidity = "humidity";
   private String pressure = "pressure";
   private String urlString = null;
   
   private String item0 = "temp0";
   private String item1 = "temp1";
   private String item2 = "temp2";
   private String item3 = "temp3";

   public volatile boolean parsingComplete = true;
   public HandleJSON(String url){
      this.urlString = url;
   }
   public String getCountry(){
      return item0;
   }
   public String getTemperature(){
      return item1;
   }
   public String getHumidity(){
      return item2;
   }
   public String getPressure(){
      //return pressure;
	   return item3;
   }

   @SuppressLint("NewApi")
   public void readAndParseJSON(String in) {
      try {
    	  //feed, entry, title, $t : textForReturnString
    	  JSONObject parentObject = new JSONObject(in);
          JSONObject feed = parentObject.getJSONObject("feed");
    	  //temp = feed.getString("entry");
          JSONArray entry = feed.getJSONArray("entry");
          item0 = entry.getString(0);
          item1 = entry.getString(1);
          item2 = entry.getString(2);
          item3 = entry.getString(3);
          //JSONObject entry = feed.getJSONObject("entry");
    	  //JSONObject title = entry.getJSONObject("title");
    	  //temp = title.getString("$t");
    	  
    	  
    	  
    	  
    	/* JSONObject reader = new JSONObject(in);
         JSONObject sys  = reader.getJSONObject("sys");
         country = sys.getString("country");
         JSONObject main  = reader.getJSONObject("main");
         temperature = main.getString("temp");
         pressure = main.getString("pressure");
         humidity = main.getString("humidity"); */

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