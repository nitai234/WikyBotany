package com.example.wikybotany;

import androidx.appcompat.app.AppCompatActivity;

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
    ArrayList<LineDisplay> plantsList;

    LineDisplayAdapter plantsListAdapter;
    int listCounter; //מספר האיברים ברשימה
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list_screen2);

        Button BTback;

        BTback = findViewById(R.id.BTback);
        BTback.setOnClickListener(this);

        plantsList = new ArrayList<LineDisplay>();
        //כאן יהיה לולאה/ פעולה שתוסיף איברים למערך הנ"ל

        plantsListAdapter = new LineDisplayAdapter(this, 0, 0, plantsList);
        LVplants = findViewById(R.id.LVplant);
        LVplants.setAdapter(plantsListAdapter);

    }

    @Override
    public void onClick(View view)
    {


    }
}