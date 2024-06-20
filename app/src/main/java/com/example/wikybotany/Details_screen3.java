package com.example.wikybotany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Details_screen3 extends AppCompatActivity implements View.OnClickListener {

    Button BTback3;
    TextView TVplantName3, TVplantTime,TVpickTime, TVdifficult, TVhigh, TVwater, TVlight, TVdetails;
    Intent intentGetScreen2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen3);

        intentGetScreen2 = getIntent();

        BTback3 = findViewById(R.id.BTback3);
        BTback3.setOnClickListener(this);


        TVplantName3 = findViewById(R.id.TVplantName3);
        TVplantName3.setText(intentGetScreen2.getStringExtra("plant name"));

        TVplantTime = findViewById(R.id.TVplantTime);
        TVplantTime.setText("עונת שתילה:"+"\n"+intentGetScreen2.getExtras().getString("plant time"));

        TVpickTime = findViewById(R.id.TVpickTime);
        TVpickTime.setText("עונת קטיף:"+"\n"+intentGetScreen2.getExtras().getString("pick time"));

        TVdifficult = findViewById(R.id.TVdifficult);
        TVdifficult.setText("רמת קושי:"+"\n"+intentGetScreen2.getExtras().getString("difficult"));

        TVhigh = findViewById(R.id.TVhigh);
        TVhigh.setText("גובה:"+"\n"+intentGetScreen2.getExtras().getInt("high"));

        TVwater = findViewById(R.id.TVwater);
        TVwater.setText("כמות מים:"+"\n"+intentGetScreen2.getExtras().getString("water"));

        TVlight = findViewById(R.id.TVlight);
        TVlight.setText("כמות אור:"+"\n"+intentGetScreen2.getExtras().getString("light"));

        TVdetails = findViewById(R.id.TVdetails);
        TVdetails.setText(intentGetScreen2.getExtras().getString("details"));


    }

    @Override
    public void onClick(View view)
    {
        if (view==BTback3)
        {
            finish();
        }
    }
}