package com.example.wikybotany;

public class Filters
{

    //1-= כלום
    private int plantTime; // 0= כל השנה, 1= סתיו, 2= חורף, 3= אביב, 4= קיץ,
    private int pickTime; //כנ"ל
    private int difficult; // 0= קל, 1= בינוני, 2= קשה
    private int highMin;
    private int highMax;
    private int water; // 0= קצת, 1= בינוני, 2= הרבה
    private int light; //כנ"ל

    public Filters(int plantTime, int pickTime, int difficult, int highMin, int highMax, int water, int light)
    {
        this.plantTime = plantTime;
        this.pickTime = pickTime;
        this.difficult = difficult;
        this.highMin = highMin;
        this.highMax = highMax;
        this.water = water;
        this.light = light;
    }

    public Filters()
    {
        this.plantTime = -1;
        this.pickTime = -1;
        this.difficult = -1;
        this.highMin = 0;
        this.highMax = 0;
        this.water = -1;
        this.light = -1;
    }


    //פיולה שמאפסת את המשתנים
    public void clean()
    {
        this.plantTime = -1;
        this.pickTime = -1;
        this.difficult = -1;
        this.highMin = 0;
        this.highMax = 0;
        this.water = -1;
        this.light = -1;
    }


    public int getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(int plantTime) {
        this.plantTime = plantTime;
    }

    public int getPickTime() {
        return pickTime;
    }

    public void setPickTime(int pickTime) {
        this.pickTime = pickTime;
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public int getHighMin() {
        return highMin;
    }

    public void setHighMin(int high) {
        this.highMin = high;
    }

    public int getHighMax() {
        return highMax;
    }

    public void setHighMax(int high) {
        this.highMax = high;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

}
