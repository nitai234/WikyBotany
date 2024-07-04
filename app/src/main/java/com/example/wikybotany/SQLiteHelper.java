package com.example.wikybotany;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper
{

    //SQL עזרים
    public static final String DATABASE_NAME="product.db";
    public static final String TABLE_PLANT="tblplant";
    public static final int DATABASE_VERSION=1;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PLANT_NAME = "plant_name";
    public static final String COLUMN_PLANT_TIME = "plant_time";
    public static final String COLUMN_PICK_TIME = "pick_time";
    public static final String COLUMN_DIFFICULT = "difficalt";
    public static final String COLUMN_HIGH = "high";
    public static final String COLUMN_WATER = "water";
    public static final String COLUMN_LIGHT = "light";
    public static final String COLUMN_DETAILS = "details";

    SQLiteDatabase database;

    //שאילתה שיוצרת טבלא אם היא לא קיימת
    private static final String CREATE_TABLE_PLANT="CREATE TABLE IF NOT EXISTS " +
            TABLE_PLANT + "(" + COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PLANT_NAME + " VARCHAR," + COLUMN_PLANT_TIME + " VARCHAR,"
            + COLUMN_PICK_TIME + " VARCHAR," + COLUMN_DIFFICULT + " VARCHAR,"
            + COLUMN_HIGH +   " INTEGER,"  +  COLUMN_WATER + " VARCHAR," +  COLUMN_LIGHT + " VARCHAR," + COLUMN_DETAILS + " VARCHAR " + ");";

    //פעולה שיוצרת מערך של כל העמודות
    String[] allColumns={COLUMN_PLANT_NAME, COLUMN_PLANT_TIME, COLUMN_PICK_TIME, COLUMN_DIFFICULT, COLUMN_HIGH, COLUMN_WATER, COLUMN_LIGHT, COLUMN_DETAILS};


    //פעולה שאין לי מושג מה היא עושה אבל חייב לממש אותה או משו כזה
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    //פעולה שיוצרת טבלה חדשה
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PLANT);
      //  AppHelp.arrayListToSQL(DownloadJSON.getJsondata(AppHelp.JSONhttp), this);
        Log.d("SQL", "Table " + TABLE_PLANT + "is create");
    }

    //פעולה שמוחקת את הטבלה הישנה ויוצרת את המעודכנת
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i1, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANT);
        onCreate(sqLiteDatabase);
        Log.d("SQL", "Table " + TABLE_PLANT + "is upgrade");
    }


    public void deleteAndCreate()
    {
        database = this.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANT);
        onCreate(database);
    }

    //פעולה שפותחת קישור לדאטאבייס
    public void open()
    {
        database=this.getWritableDatabase();
        Log.i("SQL", "Database connection open");
    }


    public void addPlantToTable(Plant plant)
    {
        ContentValues values=new ContentValues();
        values.put(COLUMN_PLANT_NAME, plant.getPlantName());
        values.put(COLUMN_PLANT_TIME, plant.getPlantTime());
        values.put(COLUMN_PICK_TIME, plant.getPickTime());
        values.put(COLUMN_DIFFICULT, plant.getDifficult());
        values.put(COLUMN_HIGH, plant.getHigh());
        values.put(COLUMN_WATER, plant.getWater());
        values.put(COLUMN_LIGHT, plant.getLight());
        values.put(COLUMN_DETAILS, plant.getDetails());

        //database.insert(TABLE_PLANT, null, values);
        long id = database.insert(TABLE_PLANT, null, values);
        Log.i("SQL", "Plant " + id + "insert to database");
    }

    //פעולה שמחזירה רשימה של כל הצמחים בטבלה
    public ArrayList<Plant> getAllPlants() {

        ArrayList<Plant> plantArrayList = new ArrayList<Plant>();
        Cursor cursor=database.query(TABLE_PLANT, allColumns, null, null, null, null, null);

        if(cursor.getCount()>0)
        {
            while(cursor.moveToNext())
            {
                String plantName=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLANT_NAME));
                String plantTime = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLANT_TIME));
                String pickTime = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PICK_TIME));
                String difficult = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIFFICULT));
                int high= cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HIGH));
                String water= cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WATER));
                String light= cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LIGHT));
                String details = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS));
                Plant plant = new Plant(plantName, plantTime, pickTime, difficult, high, water, light, details);
                plantArrayList.add(plant);
            }
        }
        return plantArrayList;
    }

    public ArrayList<Plant> getByWordPlant(String selection, String[] selectionArgs)
    {
        ArrayList<Plant> plantArrayList = new ArrayList<Plant>();
        Cursor cursor=database.query(TABLE_PLANT, allColumns, selection, selectionArgs, null, null, null);
        if(cursor.getCount()>0)
        {
            while(cursor.moveToNext())
            {
                String plantName=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLANT_NAME));
                String plantTime = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLANT_TIME));
                String pickTime = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PICK_TIME));
                String difficult = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIFFICULT));
                int high= cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HIGH));
                String water= cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WATER));
                String light= cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LIGHT));
                String details = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS));
                Plant plant = new Plant(plantName, plantTime, pickTime, difficult, high, water, light, details);
                plantArrayList.add(plant);
            }

        }

        return plantArrayList;
    }
    public ArrayList<Plant> getByFilter(String selection)
    {
        ArrayList<Plant> plantArrayList = new ArrayList<Plant>();
        Cursor cursor=database.query(TABLE_PLANT, allColumns, selection, null, null, null, null);
        if(cursor.getCount()>0)
        {
            while(cursor.moveToNext())
            {
                String plantName=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLANT_NAME));
                String plantTime = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLANT_TIME));
                String pickTime = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PICK_TIME));
                String difficult = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIFFICULT));
                int high= cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HIGH));
                String water= cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WATER));
                String light= cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LIGHT));
                String details = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS));
                Plant plant = new Plant(plantName, plantTime, pickTime, difficult, high, water, light, details);
                plantArrayList.add(plant);
            }

        }

        return  plantArrayList;
    }


}
