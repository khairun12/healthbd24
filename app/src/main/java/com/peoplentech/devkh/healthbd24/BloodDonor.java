package com.peoplentech.devkh.healthbd24;

/**
 * Created by User on 4/16/2018.
 */

public class BloodDonor {
    private int donorId;
    private String donorName;
    private String donorEmail;
    private String donorContact;
    private String donorArea;
    private String bloodType;

    public BloodDonor (int id, String name, String area, String phone, String email, String blood) {
        this.donorId = id;
        this.donorEmail = email;
        this.donorName = name;
        this.donorContact = phone;
        this.donorArea = area;
        this.bloodType = blood;

    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int id) {
        this.donorId = id;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String email) {
        this.donorEmail = email;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String name) {
        this.donorName = name;
    }

    public String getDonorContact() {
        return donorContact;
    }

    public void setDonorContact(String mobile) {
        this.donorContact = mobile;
    }

    public String getDonorArea() {
        return donorArea;
    }

    public void setDonorArea(String fax) {
        this.donorArea = fax;
    }

    public String getBloodType() { return bloodType;}

    public void setBloodType(String blood) { this.bloodType = blood; }
}
