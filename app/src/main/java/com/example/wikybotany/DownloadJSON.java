package com.example.wikybotany;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class DownloadJSON extends AsyncTask<String, Integer, ArrayList<Plant>>  {

    ProgressDialog p;
    SQLiteHelper sqLiteHelper;
    Context context;

    public DownloadJSON( SQLiteHelper sqLiteHelper, Context context)
    {
        super();
        this.sqLiteHelper = sqLiteHelper;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // TODO Auto-generated method stub
        p=ProgressDialog.show(context, "Upload JSON files","Loading please wait...",true);
        p.setCancelable(true);
        p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        p.setMessage("Loading...");
        p.show();


    }
    @Override
    protected ArrayList<Plant> doInBackground(String... params) {
        // TODO Auto-generated method stub
        ArrayList<Plant> data=null;
      //  HttpURLConnection urlConnection=null;
      //  URL url = null;
        try
        {
            data = getJsondata(params[0]);
        }
        catch(Exception e)
        {
            Log.e("Error", "JSON error"+ e.getMessage());
        }
        return data;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(ArrayList<Plant> result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        if(result!=null)
        {
            arrayListToSQL(result);
            p.dismiss();
        }
    }


    public ArrayList<Plant> getJsondata(String strurl)
    {
        ArrayList<Plant>arrayList=new ArrayList<Plant>();
        String line="";
        //String res="";
        InputStream in=null;
        try
        {
//            HttpURLConnection urlConnection=null;
//            URL url = null;
            try
            {
                URL myURL = new URL(strurl);
                HttpsURLConnection ucon = (HttpsURLConnection) myURL.openConnection();
                in = ucon.getInputStream();
            }
            catch (Exception e)
            {
                Log.e("Error",e.getMessage());
            }


            BufferedReader x=new BufferedReader(new InputStreamReader(in,"UTF-8"));

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
            }
            catch(JSONException e)
            {

            }
            return arrayList;
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public void arrayListToSQL(ArrayList<Plant> plantArrayList)
    {

        sqLiteHelper.open();
        for (int i=0; i< plantArrayList.size(); i++)
        {

            sqLiteHelper.addPlantToTable(plantArrayList.get(i));
        }
        sqLiteHelper.close();
    }

}


//    public static ArrayList<Plant> getJsondata(String strurl)
//    {
//        ArrayList<Plant> arrayList=new ArrayList<Plant>();
//        String line="";
//        String res="";
//        InputStream in=null;
//        try
//        {
//            //   HttpURLConnection urlConnection=null;
//            URL url = null;
//            try
//            {
//
//                URL myURL = new URL(strurl);
//                HttpsURLConnection ucon = (HttpsURLConnection) myURL.openConnection();
//                in = ucon.getInputStream();
//            }
//
//            catch (Exception e)
//            {
//                Log.e("Error",e.getMessage() + " 1");
//            }
//
//
//            BufferedReader x=new BufferedReader(new InputStreamReader(in,"iso-8859-1"));
//
//            StringBuffer sb=new StringBuffer("");
//
//            String l="";
//            //String nl=System.getProperty("line.separator");
//            while((l=x.readLine())!=null)
//            {
//                sb.append(l+"\n");
//            }
//
//            in.close();
//            x.close();
//            line=sb.toString();
//            try
//            {
//                JSONArray jArray = new JSONArray(line);
//                for(int i=0;i<jArray.length();i++)
//                {
//
//                    Log.d("JSON","try json"+i);
//                    JSONObject json_data = jArray.getJSONObject(i);
//                    String plantName = json_data.getString("Name");
//                     String plantTime = json_data.getString("plant Time");
//                     String pickTime = json_data.getString("pick Time");
//                     String difficult = json_data.getString("difficult Level");
//                     int high = json_data.getInt("high");
//                     String water = json_data.getString("water");
//                     String light = json_data.getString("light");
//                     String details = json_data.getString("detail");
//
//                    Plant plant= new Plant(plantName, plantTime, pickTime, difficult, high, water, light, details);
//                    arrayList.add(plant);
//
//                }
//                return arrayList;
//
//            }
//            catch(JSONException e)
//            {
//
//            }
//        }
//        catch (Exception e) {
//            Log.e("Error",e.getMessage() + " 2");
//        }
//        return null;
//
//    }




