package com.example.wikybotany.link_list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wikybotany.Plant;
import com.example.wikybotany.R;

import java.util.ArrayList;

//מייצר את הlist view
public class LineDisplayAdapter extends ArrayAdapter<LineDisplay>
{
    Context context;
    ArrayList<Plant> lineDisplayArry ;

    TextView TVplantName, TVDifficult;

    public LineDisplayAdapter(@NonNull Context context, int resource, ArrayList<Plant> plantsList) {
        super(context, resource);

        this.context = context;
        this.lineDisplayArry = plantsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_layout, parent, false);

         TVplantName = view.findViewById(R.id.TVplantName);
         TVDifficult = view.findViewById(R.id.TVdifficultLevel);

        //מצביע למיקום בarry list
        Plant temp = lineDisplayArry.get(position) ;

        //קובע את הערכים של התיבה הנוכחית
        TVplantName.setText("שם הצמח: " + temp.getPlantName());
        TVDifficult.setText( "קושי לגידול: "+ temp.getDifficult());

        return view;
    }
}
