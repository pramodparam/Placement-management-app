package com.example.myapp;

public class User {
String coName,rDate,location,department;

public User(){

}


    public User(String coName, String rDate, String location, String department) {
        this.coName = coName;
        this.rDate = rDate;
        this.location = location;
        this.department = department;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


}
