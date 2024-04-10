package com.example.wikybotany;

import android.graphics.Bitmap;

public class LineDisplay
{
    private Bitmap plantPic;
    private String plantName;
    private String editorName;

    public LineDisplay(Bitmap plantPic, String plantName, String editorName)
    {
        this.plantPic = plantPic;
        this.plantName = plantName;
        this.editorName = editorName;
    }

    public Bitmap getPlantPic()
    {
        return this.plantPic;
    }
    public String getPlantName()
    {
        return this.plantName;
    }
    public String getEditorName()
    {
        return this.editorName;
    }

    public void setPlantPic(Bitmap plantPic)
    {
        this.plantPic = plantPic;
    }
    public void setPlantName(String plantName)
    {
        this.plantName = plantName;
    }
    public void setEditorName(String editorName)
    {
        this.editorName = editorName;
    }


}
