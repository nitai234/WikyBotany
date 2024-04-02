package com.example.wikybotany;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.Spcategories);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);

        Button BTsearch, BTshowAll, BTlocation;

        BTsearch = findViewById(R.id.BTsrc);
        BTsearch.setOnClickListener(this);

        BTshowAll = findViewById(R.id.BTsrcall);
        BTshowAll.setOnClickListener(this);

        BTlocation = findViewById(R.id.BTclothestLocation);
        BTlocation.setOnClickListener(this);


        spinner.setAdapter(adapter);
    }




    @Override
    public void onClick(View view)
    {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        Log.d("menu_main, select item", item + "is choose");

        int id = item.getItemId();
        if(id == R.id.favourites)
        {

        }
        else if (id == R.id.lastSearch)
        {

        }
        else if (id == R.id.setting)
        {

        }
        return true;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        String criterion = adapterView.getItemAtPosition(i).toString();
        Log.d("spinner choose",criterion + "is choosen");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }



}