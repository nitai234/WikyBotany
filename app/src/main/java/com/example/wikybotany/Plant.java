package com.example.wikybotany;

public class Plant {
    private String plantName;
    private String plantTime;
    private String pickTime;
    private String difficult;
    private int high;
    private String water;
    private String light;
    private String details;

    public Plant(String plantName, String plantTime, String pickTime, String difficult, int high, String water, String light, String details) {
        this.plantName = plantName;
        this.plantTime = plantTime;
        this.pickTime = pickTime;
        this.difficult = difficult;
        this.high = high;
        this.water = water;
        this.light = light;
        this.details = details;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(String plantTime) {
        this.plantTime = plantTime;
    }

    public String getPickTime() {
        return pickTime;
    }

    public void setPickTime(String pickTime) {
        this.pickTime = pickTime;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
