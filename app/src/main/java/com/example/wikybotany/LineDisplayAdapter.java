package com.example.wikybotany;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LineDisplayAdapter extends ArrayAdapter<LineDisplay>
{
    Context context;
    LineDisplay[] object;

    public LineDisplayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull LineDisplay[] objects) {
        super(context, resource, textViewResourceId, objects);

        this.context = context;
        this.object = objects;

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
        LineDisplay lineDisplay = object[position];

        IMplantPic.setImageBitmap(lineDisplay.getPlantPic());
        TVplantName.setText(lineDisplay.getPlantName());
        TVeditorName.setText(lineDisplay.getEditorName());

        return view;
    }
}
