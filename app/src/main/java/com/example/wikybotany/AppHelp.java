package com.example.wikybotany;

import android.widget.ListView;

import com.example.wikybotany.link_list.LineDisplayAdapter;

import java.util.ArrayList;

public class AppHelp {




    //פעולה שמקבלת פילטר וממירה אותו למערך סטרינג של הקריטריונים
    public static String[] filterToString(Filters filters)
    {
        String[] StringfiltersArr = new String[5]; // 0= זמן שתילה, 1= זמן קטיף, 2= רמת קושי, 3= כמות מים, 4= כמות אור
        String[] seasons = {"כל השנה", "סתיו", "חורף", "אביב", "קיץ"};
        String[] difficult = {"קל", "בינוני", "קשה"};
        String[] amount = {"קצת", "בינוני", "הרבה"};

        if (filters.getPlantTime() == -1)
        {
            StringfiltersArr[0] = null;
        }
        else {
            StringfiltersArr[0] = seasons[filters.getPlantTime()];
        }

        if (filters.getPickTime() == -1)
        {
            StringfiltersArr[1] = null;
        }
        else {
            StringfiltersArr[1] = seasons[filters.getPickTime()];
        }

        if (filters.getDifficult() == -1) {
            StringfiltersArr[2] = null;
        }
        else {
            StringfiltersArr[2] = difficult[filters.getDifficult()];
        }

        if (filters.getWater() == -1){
            StringfiltersArr[3] = null;
        }
        else {
            StringfiltersArr[3] = amount[filters.getWater()];
        }

        if (filters.getLight() == -1){
            StringfiltersArr[4] = null;
        }
        else {
            StringfiltersArr[4] = amount[filters.getLight()];
        }

        return StringfiltersArr;
    }



}
