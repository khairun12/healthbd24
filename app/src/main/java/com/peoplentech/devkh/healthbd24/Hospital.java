package com.peoplentech.devkh.healthbd24;

/**
 * Created by User on 3/25/2018.
 */

public class Hospital {
    private int hosId;
    private String hospitalName;
    private String hospitalWebsite;
    private String hospitalNumber;
    private String hospitalAddress;

    public Hospital() {

    }

    public Hospital (int id, String name, String web, String number, String address ) {
        this.hosId = id;
        this.hospitalName = name;
        this.hospitalWebsite = web;
        this.hospitalNumber = number;
        this.hospitalAddress = address;
    }

    public int getHospitalId() {
        return hosId;
    }

    public void setHospitalId(int id) {
        this.hosId = id;
    }

    public String getHospitalWebsite() {
        return hospitalWebsite;
    }

    public void setHospitalWebsite(String website) {
        this.hospitalWebsite = website;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String name) {
        this.hospitalName = name;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String address) {
        this.hospitalAddress = address;
    }

    public String getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(String number) {
        this.hospitalNumber = number;
    }
}