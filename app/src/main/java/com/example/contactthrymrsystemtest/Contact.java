package com.example.contactthrymrsystemtest;

import java.io.Serializable;

public class Contact implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String phone1;
    private String companyName;
    private int isFavourite;
    private int isRecent;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }

    public int getIsRecent() {
        return isRecent;
    }

    public void setIsRecent(int isRecent) {
        this.isRecent = isRecent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int isFavourite() {
        return isFavourite;
    }

    public void setFavourite(int favourite) {
        isFavourite = favourite;
    }

    public int isRecent() {
        return isRecent;
    }

    public void setRecent(int recent) {
        isRecent = recent;
    }
}
