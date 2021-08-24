package com.example.myapp;

public class Student {
    String name,email,compname,course;
    public Student(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Student(String name, String email, String compname, String course) {
        this.name = name;
        this.email = email;
        this.compname = compname;
        this.course = course;
    }
}
