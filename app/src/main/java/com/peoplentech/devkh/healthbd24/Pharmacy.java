package com.peoplentech.devkh.healthbd24;

/**
 * Created by User on 3/27/2018.
 */

public class Pharmacy {
    private int pharmaId;
    private String pharmacyName;
    private String pharmacyNumber;
    private String pharmacyAddress;

    public Pharmacy() {

    }

    public Pharmacy (int id, String name,  String number, String address ) {
        this.pharmaId = id;
        this.pharmacyName = name;
        this.pharmacyNumber = number;
        this.pharmacyAddress = address;
    }

    public int getPharmacyId() {
        return pharmaId;
    }

    public void setPharmacyId(int id) {
        this.pharmaId = id;
    }


    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String name) {
        this.pharmacyName = name;
    }

    public String getPharmacyAddress() {
        return pharmacyAddress;
    }

    public void setPharmacyAddress(String address) {
        this.pharmacyAddress = address;
    }

    public String getPharmacyNumber() {
        return pharmacyNumber;
    }

    public void setPharmacyNumber(String number) {
        this.pharmacyNumber = number;
    }
}