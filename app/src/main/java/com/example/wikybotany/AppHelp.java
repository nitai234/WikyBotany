package com.example.wikybotany;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.logging.Filter;

public class AppHelp {

    
    public static final String JSONhttp = "https://www.isaac770.live/media/plant_table_v1.json";


    //פעולה שמקבלת פילטר וממירה אותו למערך סטרינג של הקריטריונים
    public static String[] filterToString(Filters filters)
    {
        String[] StringfiltersArr = new String[7]; // 0= זמן שתילה, 1= זמן קטיף, 2= רמת קושי, 3= גובה מינימלי, 4= גובה מקסימלי, 5= כמות מים, 6= כמות אור
        String[] seasons = {"כל השנה", "סתיו", "חורף", "אביב", "קיץ"};
        String[] difficult = {"קל", "בינוני", "קשה"};
        String[] amount = {"קצת", "בינוני", "הרבה"};

        if (filters.getPlantTime() == -1)
        {
            StringfiltersArr[0] = null;
        }
        else {
            Log.i("check index", filters.getPlantTime() + "");
            StringfiltersArr[0] = seasons[filters.getPlantTime()];
            Log.i("filter", StringfiltersArr[0]);
        }

        if (filters.getPickTime() == -1)
        {
            StringfiltersArr[1] = null;
        }
        else {
            Log.i("check index", filters.getPickTime() + "");
            StringfiltersArr[1] = seasons[filters.getPickTime()];
        }

        if (filters.getDifficult() == -1) {
            StringfiltersArr[2] = null;
        }
        else {
            Log.i("check index", filters.getDifficult() + "");
            StringfiltersArr[2] = difficult[filters.getDifficult()];
        }

        if (filters.getHighMin() <= 0)
        {
            StringfiltersArr[3] = null;
        }
        else {
            StringfiltersArr[3] = ""+filters.getHighMin();
        }

        if (filters.getHighMax() <= 0 || filters.getHighMax() <= filters.getHighMin())
        {
            StringfiltersArr[4] = null;
        }
        else {
            StringfiltersArr[4] = ""+filters.getHighMax();
        }

        if (filters.getWater() == -1){
            StringfiltersArr[5] = null;
        }
        else {
            StringfiltersArr[5] = amount[filters.getWater()];
        }

        if (filters.getLight() == -1){
            StringfiltersArr[6] = null;
        }
        else {
            StringfiltersArr[6] = amount[filters.getLight()];
        }

        return StringfiltersArr;
    }









}
