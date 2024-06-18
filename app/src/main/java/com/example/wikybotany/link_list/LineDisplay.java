package com.example.wikybotany.link_list;

import android.graphics.Bitmap;

public class LineDisplay
{
    private Bitmap plantPic;
    private String plantName;
    private String difficult;

    public LineDisplay(Bitmap plantPic, String plantName, String difficult)
    {
        this.plantPic = plantPic;
        this.plantName = plantName;
        this.difficult = difficult;
    }

    public Bitmap getPlantPic()
    {
        return this.plantPic;
    }
    public String getPlantName()
    {
        return this.plantName;
    }
    public String getDifficult()
    {
        return this.difficult;
    }

    public void setPlantPic(Bitmap plantPic)
    {
        this.plantPic = plantPic;
    }
    public void setPlantName(String plantName)
    {
        this.plantName = plantName;
    }
    public void setDifficult(String editorName)
    {
        this.difficult = editorName;
    }


}
