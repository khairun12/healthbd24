package com.peoplentech.devkh.healthbd24;

/**
 * Created by User on 3/22/2018.
 */
public class Doctor {
    private int id;
    private String name;
    private String email;
    private String contact;
    private String area;
    private String job_inst;
    private String job_deg;
    private String edu_inst;
    private String edu_deg;
    private String edu_country;
    private String cham_name;
    private String cham_number;
    private String cham_address;

    public Doctor(int id, String name, String email, String contact, String area, String job_inst,String job_deg,
                  String edu_inst,String edu_deg,String edu_country, String cham_name,String cham_address,String cham_number ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.contact = contact;
        this.area = area;
        this.job_deg = job_deg;
        this.job_inst = job_inst;
        this.edu_country = edu_country;
        this.edu_deg = edu_deg;
        this.edu_inst = edu_inst;
        this.cham_address = cham_address;
        this.cham_name = cham_name;
        this.cham_number = cham_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String mobile) {
        this.contact = contact;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String fax) {
        this.area = area;
    }

    public String getJobDegree() {
        return job_deg;
    }

    public void setJobDegree(String job_deg) {
        this.job_deg = job_deg;
    }

    public String getJobInstitute() {
        return job_inst;
    }

    public void setJobInstitute(String job_inst) {
        this.job_inst = job_inst;
    }

    public String getEduCountry() {
        return edu_country;
    }

    public void setEduCountry(String edu_country) {
        this.edu_country = edu_country;
    }

    public String getEduDegree() {
        return edu_deg;
    }

    public void setEduDegree(String edu_deg) {
        this.edu_deg = edu_deg;
    }

    public String getEduInstitute() {
        return edu_inst;
    }

    public void setEduInstitute(String edu_inst) {
        this.edu_inst = edu_inst;
    }

    public String getChamberAddress() {
        return cham_address;
    }

    public void setChamberAddress(String cham_address) {
        this.cham_address = cham_address;
    }

    public String getChamberName() {
        return cham_name;
    }

    public void setChamberName(String cham_name) {
        this.cham_name = cham_name;
    }

    public String getChamberNumber() {
        return cham_number;
    }

    public void setChamberNumber(String cham_number) {
        this.cham_number = cham_number;
    }
}