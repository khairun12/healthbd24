package com.peoplentech.devkh.healthbd24;

/**
 * Created by User on 4/16/2018.
 */

public class Kidney {
    private int kidneyId;
    private String kidneyName;
    private String kidneyAddress;
    private String kidneyNumber;
    private String kidneyWebsite;

    public Kidney () {

    }

    public Kidney (int id, String name,  String address, String number, String website ) {
        this.kidneyId = id;
        this.kidneyName = name;
        this.kidneyNumber = number;
        this.kidneyAddress = address;
        this.kidneyWebsite = website;
    }

    public int getKidneyId() {
        return kidneyId;
    }

    public void setKidneyId(int id) {
        this.kidneyId = id;
    }


    public String getKidneyName() {
        return kidneyName;
    }

    public void setKidneyName(String name) {
        this.kidneyName = name;
    }

    public String getKidneyAddress() {
        return kidneyAddress;
    }

    public void setKidneyAddress(String address) {
        this.kidneyAddress = address;
    }

    public String getKidneyNumber() {
        return kidneyNumber;
    }

    public void setKidneyNumber(String number) {
        this.kidneyNumber = number;
    }

    public String getKidneyWebsite() {
        return kidneyWebsite;
    }

    public void setKidneyWebsite(String website) {
        this.kidneyWebsite = website;
    }

}