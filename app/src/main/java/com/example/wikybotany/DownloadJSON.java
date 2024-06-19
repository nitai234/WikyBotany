package com.example.wikybotany;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class DownloadJSON {


//    public ArrayList<Plant> getData() {
//        ArrayList<Plant> data = null;
//        HttpURLConnection urlConnection = null;
//        URL url = null;
//        try {
//            data = getJsondata(AppHelp.JSONhttp);
//        }
//        catch (Exception e) {
//
//        }
//        return data;
//    }







    public static ArrayList<Plant> getJsondata(String strurl)
    {
        ArrayList<Plant> arrayList=new ArrayList<Plant>();
        String line="";
        String res="";
        InputStream in=null;
        try
        {
            HttpURLConnection urlConnection=null;
            URL url = null;
            try
            {
                URL myURL = new URL(strurl);
                URLConnection ucon = myURL.openConnection();
                in = ucon.getInputStream();
            } catch (Exception e)
            {
                Log.e("Error",e.getMessage());
            }


            BufferedReader x=new BufferedReader(new InputStreamReader(in,"iso-8859-1"));

            StringBuffer sb=new StringBuffer("");

            String l="";
            //String nl=System.getProperty("line.separator");
            while((l=x.readLine())!=null)
            {
                sb.append(l+"\n");
            }

            in.close();
            x.close();
            line=sb.toString();
            try
            {
                JSONArray jArray = new JSONArray(line);
                for(int i=0;i<jArray.length();i++)
                {

                    Log.d("JSON","try json"+i);
                    JSONObject json_data = jArray.getJSONObject(i);
                    String plantName = json_data.getString("Name");
                     String plantTime = json_data.getString("plant Time");
                     String pickTime = json_data.getString("pick Time");
                     String difficult = json_data.getString("difficult Level");
                     int high = json_data.getInt("high");
                     String water = json_data.getString("water");
                     String light = json_data.getString("light");
                     String details = json_data.getString("detail");

                    Plant plant= new Plant(plantName, plantTime, pickTime, difficult, high, water, light, details);
                    arrayList.add(plant);

                }
                return arrayList;

            }
            catch(JSONException e)
            {

            }
        }
        catch (Exception e) {
        }
        return null;

    }


}
