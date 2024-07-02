package com.example.wikybotany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wikybotany.link_list.LineDisplay;
import com.example.wikybotany.link_list.LineDisplayAdapter;

import java.util.ArrayList;

//מסך ב'
public class PlantList_screen2 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ListView LVplants;

    SQLiteHelper sqLiteHelper;
    LineDisplayAdapter lineDisplayAdapter;
    ArrayList<Plant> plantsList;
    Intent intentGetMain, intentToScreen3;
    Button BTback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list_screen2);

        intentToScreen3 = new Intent(this, Details_screen3.class);

        intentGetMain = getIntent();

        LVplants = findViewById(R.id.LVplant);

        BTback = findViewById(R.id.BTback);
        BTback.setOnClickListener(this);

        sqLiteHelper = new SQLiteHelper(this);
        plantsList = new ArrayList<Plant>();

        LVplants.setOnItemClickListener(this);


        if (intentGetMain.getExtras().containsKey("search_by_word")) {
            sqLiteHelper.open();
            String selection = "plant_name" + "=? ", searchWord = intentGetMain.getExtras().toString();
            String[] selectionArgs = {searchWord};
            plantsList = sqLiteHelper.getByWordPlant(selection, selectionArgs);
            sqLiteHelper.close();

            if (plantsList.isEmpty()) {
                Toast.makeText(this, "no results", Toast.LENGTH_LONG).show();
            } else {
                lineDisplayAdapter = new LineDisplayAdapter(this, R.layout.custom_layout, plantsList);
                LVplants.setAdapter(lineDisplayAdapter);
            }

        } else if (intentGetMain.getExtras().containsKey("search_all")) {
            sqLiteHelper.open();
            plantsList = sqLiteHelper.getAllPlants();
            sqLiteHelper.close();

            if (plantsList.isEmpty()) {
                Toast.makeText(this, "no results", Toast.LENGTH_LONG).show();
                intentGetMain.removeExtra("search_all");
            } else {
                lineDisplayAdapter = new LineDisplayAdapter(this, R.layout.custom_layout, plantsList);
                LVplants.setAdapter(lineDisplayAdapter);
                intentGetMain.removeExtra("search_all");
            }
        } else if (intentGetMain.getExtras().containsKey("search_by_filter")) {

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


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Plant p =plantsList.get(i);
        intentToScreen3.putExtra("plant name", p.getPlantName());
        intentToScreen3.putExtra("plant time", p.getPlantTime());
        intentToScreen3.putExtra("pick time", p.getPickTime());
        intentToScreen3.putExtra("difficult", p.getDifficult());
        intentToScreen3.putExtra("high", p.getHigh());
        intentToScreen3.putExtra("water", p.getWater());
        intentToScreen3.putExtra("light", p.getLight());
        intentToScreen3.putExtra("details", p.getDetails());
        startActivity(intentToScreen3);
    }
}