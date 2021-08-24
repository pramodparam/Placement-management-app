package com.example.myapp;

public class DisplayStudent {
    String pdName,pdUsn,pdEmail,pdCompName,pdCompAddress,pdDepart;

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getPdUsn() {
        return pdUsn;
    }

    public void setPdUsn(String pdUsn) {
        this.pdUsn = pdUsn;
    }

    public String getPdEmail() {
        return pdEmail;
    }

    public void setPdEmail(String pdEmail) {
        this.pdEmail = pdEmail;
    }

    public String getPdCompName() {
        return pdCompName;
    }

    public void setPdCompName(String pdCompName) {
        this.pdCompName = pdCompName;
    }

    public String getPdCompAddress() {
        return pdCompAddress;
    }

    public void setPdCompAddress(String pdCompAddress) {
        this.pdCompAddress = pdCompAddress;
    }

    public String getPdDepart() {
        return pdDepart;
    }

    public void setPdDepart(String pdDepart) {
        this.pdDepart = pdDepart;
    }

    public DisplayStudent(String pdName, String pdUsn, String pdEmail, String pdCompName, String pdCompAddress, String pdDepart) {
        this.pdName = pdName;
        this.pdUsn = pdUsn;
        this.pdEmail = pdEmail;
        this.pdCompName = pdCompName;
        this.pdCompAddress = pdCompAddress;
        this.pdDepart = pdDepart;
    }
}
