package com.peoplentech.devkh.healthbd24;

/**
 * Created by User on 4/15/2018.
 */

public class Eye {
    private int eyeId;
    private String eyeName;
    private String eyeAddress;
    private String eyeNumber;
    private String eyeWebsite;

    public Eye () {

    }

    public Eye (int id, String name,  String address, String number, String website ) {
        this.eyeId = id;
        this.eyeName = name;
        this.eyeNumber = number;
        this.eyeAddress = address;
        this.eyeWebsite = website;
    }

    public int getEyeId() {
        return eyeId;
    }

    public void setEyeId(int id) {
        this.eyeId = id;
    }


    public String getEyeName() {
        return eyeName;
    }

    public void setEyeName(String name) {
        this.eyeName = name;
    }

    public String getEyeAddress() {
        return eyeAddress;
    }

    public void setEyeAddress(String address) {
        this.eyeAddress = address;
    }

    public String getEyeNumber() {
        return eyeNumber;
    }

    public void setEyeNumber(String number) {
        this.eyeNumber = number;
    }

    public String getEyeWebsite() {
        return eyeWebsite;
    }

    public void setEyeWebsite(String website) {
        this.eyeWebsite = website;
    }

}