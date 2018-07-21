package com.peoplentech.devkh.healthbd24;

/**
 * Created by User on 3/27/2018.
 */

public class Ambulance {
    private int ambId;
    private String ambulanceName;
    private String ambulanceNumber;
    private String ambulanceAddress;

    public Ambulance () {

    }

    public Ambulance (int id, String name,  String number, String address ) {
        this.ambId = id;
        this.ambulanceName = name;
        this.ambulanceNumber = number;
        this.ambulanceAddress = address;
    }

    public int getAmbulanceId() {
        return ambId;
    }

    public void setAmbulanceId(int id) {
        this.ambId = id;
    }


    public String getAmbulanceName() {
        return ambulanceName;
    }

    public void setAmbulanceName(String name) {
        this.ambulanceName = name;
    }

    public String getAmbulanceAddress() {
        return ambulanceAddress;
    }

    public void setAmbulanceAddress(String address) {
        this.ambulanceAddress = address;
    }

    public String getAmbulanceNumber() {
        return ambulanceNumber;
    }

    public void setAmbulanceNumber(String number) {
        this.ambulanceNumber = number;
    }
}