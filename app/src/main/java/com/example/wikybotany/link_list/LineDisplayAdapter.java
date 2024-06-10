package com.example.wikybotany.link_list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wikybotany.R;
import com.example.wikybotany.link_list.LineDisplay;

import java.util.ArrayList;

//מייצר את הlist view
public class LineDisplayAdapter extends ArrayAdapter<LineDisplay>
{
    Context context;
    //LineDisplay[] lineDisplays;
    ArrayList<LineDisplay> lineDisplayArry ;

    public LineDisplayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull ArrayList<LineDisplay> objects) {
        super(context, resource, textViewResourceId, objects);

        this.context = context;
        this.lineDisplayArry = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_layout, parent, false);

        ImageView IMplantPic = view.findViewById(R.id.IMplantPic);
        TextView TVplantName = view.findViewById(R.id.TVplantName);
        TextView TVeditorName = view.findViewById(R.id.TVeditorName);

        //מצביע למיקום בarry list
        LineDisplay temp = lineDisplayArry.get(position) ;

        //קובע את הערכים של התיבה הנוכחית
        IMplantPic.setImageBitmap(temp.getPlantPic());
        TVplantName.setText(temp.getPlantName());
        TVeditorName.setText(temp.getEditorName());

        return view;
    }
}
