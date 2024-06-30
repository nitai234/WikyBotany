package com.example.wikybotany;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements /*AdapterView.OnItemSelectedListener,*/ View.OnClickListener {

    Button BTsearch, BTshowAll, BTlocation, BTfilter;
    EditText ETsearch;
    Dialog DIfilters, DIinformation, DIimprove;
    Filters filters;
    Intent intentToScreen2;

    SQLiteHelper sqLiteHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(this);

        sqLiteHelper.deleteAndCreate();
        DownloadJSON downloadJSON = new DownloadJSON(sqLiteHelper, this);
        downloadJSON.execute(AppHelp.JSONhttp);





          //  AppHelp.arrayListToSQL(DownloadJSON.getJsondata(AppHelp.JSONhttp), this);

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
        if(id == R.id.information)
        {
            createInfoDialog();
        }
        else if (id == R.id.improve)
        {
            createImpDialog();
        }
        return true;
    }

    public void createInfoDialog()
    {
        DIinformation = new Dialog(this);
        DIinformation.setContentView(R.layout.dialog_information);
        DIinformation.setTitle("מידע");
        DIinformation.setCancelable(true);
        Button BTDback2 = DIinformation.findViewById(R.id.BTDback2);

        DIinformation.show();
        BTDback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DIinformation.dismiss();
            }
        });

    }
    public void createImpDialog()
    {
        DIimprove = new Dialog(this);
        DIimprove.setContentView(R.layout.dialog_improve);
        DIimprove.setTitle("שיפור");
        DIimprove.setCancelable(true);

        EditText ETDimp = DIimprove.findViewById(R.id.ETDimp);

        Button BTDback3 = DIimprove.findViewById(R.id.BTDback3);
        Button BTDsend = DIimprove.findViewById(R.id.BTDsend);

        DIimprove.show();

        BTDsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = ETDimp.getText().toString();
                String phoneNumber="0555592598";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
                intent.putExtra("sms_body", message);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "ההודעה נשלחה, תודה על ההצעה", Toast.LENGTH_LONG).show();
                DIimprove.dismiss();
            }
        });

        BTDback3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DIimprove.dismiss();
            }
        });
    }



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

