package com.example.wikybotany;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements /*AdapterView.OnItemSelectedListener,*/ View.OnClickListener {

    Button BTsearch, BTshowAll, BTlocation, BTfilter;
    EditText ETsearch;
    Dialog DIfilters;
    Filters filters;
    Intent intentToScreen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Spinner spinner;
        //  spinner = findViewById(R.id.Spcategories);
       // spinner.setOnItemSelectedListener(this);
       // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);

        intentToScreen2 = new Intent(this, PlantList_screen2.class);

        filters = new Filters();

        ETsearch = findViewById(R.id.ETsearch);

        BTfilter = findViewById(R.id.BTfilter);
        BTfilter.setOnClickListener(this);

        BTsearch = findViewById(R.id.BTsrc);
        BTsearch.setOnClickListener(this);

        BTshowAll = findViewById(R.id.BTsrcall);
        BTshowAll.setOnClickListener(this);

        BTlocation = findViewById(R.id.BTclothestLocation);
        BTlocation.setOnClickListener(this);



     //   spinner.setAdapter(adapter);
    }




    @Override
    public void onClick(View view)
    {
        if (view == BTfilter)
        {
            createFilterDialog();
        }

        else if (view == BTshowAll)
        {
            intentToScreen2.putExtra("search_all",0);
            startActivity(intentToScreen2);
        }
        else if (view == BTsearch)
        {
            intentToScreen2.putExtra("search_by_word", ETsearch.getText().toString());
            startActivity(intentToScreen2);
        }

    }

    public void createFilterDialog()
    {
        DIfilters = new Dialog(this);
        DIfilters.setContentView(R.layout.dialog_filters);
        DIfilters.setTitle("מסננים");
        DIfilters.setCancelable(true);


        RadioGroup RGplant = DIfilters.findViewById(R.id.RGplntTime);
        RGplant.check(filters.getPlantTime());

        RadioGroup RGpick = DIfilters.findViewById(R.id.RGpickTime);
        RGpick.check(filters.getPickTime());

        RadioGroup RGdifficult = DIfilters.findViewById(R.id.RGdifficult);
        RGdifficult.check(filters.getDifficult());

        RadioGroup RGwater = DIfilters.findViewById(R.id.RGwater);
        RGwater.check(filters.getWater());

        RadioGroup RGlight = DIfilters.findViewById(R.id.RGlight);
        RGlight.check(filters.getLight());

        EditText ETDhighMin, ETDhighMax;
        ETDhighMin = DIfilters.findViewById(R.id.ETDhighMin);
        ETDhighMax = DIfilters.findViewById(R.id.ETDhighMax);



        Button BTDsearch, BTDback;
        BTDback = DIfilters.findViewById(R.id.BTDback);
        BTDsearch = DIfilters.findViewById(R.id.BTDsearch);

        if (filters.getHighMin() != 0)
        {
            ETDhighMin.setText(""+filters.getHighMin());
        }
        if (filters.getHighMax() != 0)
        {
            ETDhighMax.setText(""+filters.getHighMax());
        }

        DIfilters.show();




        RGplant.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                filters.setPlantTime(i);
            }
        });

        RGpick.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                filters.setPickTime(i);
            }
        });

        RGdifficult.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                filters.setDifficult(i);
            }
        });

        RGwater.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                filters.setWater(i);
            }
        });

        RGlight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                filters.setLight(i);
            }
        });


        BTDback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filters.clean();
                DIfilters.dismiss();
            }
        });

        BTDsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ETDhighMin.getText().toString().equals(""))
                {
                    filters.setHighMin(Integer.parseInt(ETDhighMin.getText().toString()));
                }
                else if (ETDhighMin.getText().toString().equals(""))
                {
                    filters.setHighMin(0);
                }
                if (!ETDhighMax.getText().toString().equals(""))
                {
                    filters.setHighMax(Integer.parseInt(ETDhighMax.getText().toString()));
                }
                else if (ETDhighMax.getText().toString().equals(""))
                {
                    filters.setHighMax(0);
                }

                String[] filterString = AppHelp.filterToString(filters);
                intentToScreen2.putExtra("search_by_filter", filterString);
                startActivity(intentToScreen2);

                DIfilters.dismiss();
            }
        });
        DIfilters.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (!ETDhighMin.getText().toString().equals(""))
                {
                    filters.setHighMin(Integer.parseInt(ETDhighMin.getText().toString()));
                }
                else if (ETDhighMin.getText().toString().equals(""))
                {
                    filters.setHighMin(0);
                }
                if (!ETDhighMax.getText().toString().equals(""))
                {
                    filters.setHighMax(Integer.parseInt(ETDhighMax.getText().toString()));
                }
                else if (ETDhighMax.getText().toString().equals(""))
                {
                    filters.setHighMax(0);
                }

            }
        });

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



//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
//    {
//        String criterion = adapterView.getItemAtPosition(i).toString();
//        Log.d("spinner choose",criterion + "is choosen");
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView)
//    {
//
//    }
//

}