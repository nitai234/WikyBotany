package com.example.wikybotany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.wikybotany.link_list.LineDisplay;
import com.example.wikybotany.link_list.LineDisplayAdapter;

import java.util.ArrayList;

//מסך ב'
public class PlantList_screen2 extends AppCompatActivity implements View.OnClickListener {

    ListView LVplants;
    ArrayList<Plant> plantsList;
    SQLiteHelper sqLiteHelper;
    LineDisplayAdapter lineDisplayAdapter;

    Intent intentGetMain;
    Button BTback;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list_screen2);

        LVplants = findViewById(R.id.LVplant);

        BTback = findViewById(R.id.BTback);
        BTback.setOnClickListener(this);

        sqLiteHelper = new SQLiteHelper(this);
        plantsList = new ArrayList<Plant>();


        if (intentGetMain.getExtras().containsKey("search_all"))
        {
            sqLiteHelper.open();
            plantsList = sqLiteHelper.getAllPlants();
            sqLiteHelper.close();

            lineDisplayAdapter = new LineDisplayAdapter(this, 0, plantsList);
            LVplants.setAdapter(lineDisplayAdapter);
        }

        else if (intentGetMain.getExtras().containsKey("search_by_word"))
        {

        }

        else if (intentGetMain.getExtras().containsKey("search_by_filter"))
        {

        }

    }

    @Override
    public void onClick(View view)
    {
        if (view == BTback)
        {
            finish();
        }

    }
}