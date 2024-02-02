package com.example.lab2;

public class Student {

    private int trackNumber;
    private int id;
    private String name;
    private String major;

    public Student(){
        this.trackNumber = 0;
        this.id = 0;
        this.name = "";
        this.major = "";
    }

    public Student(int trackNumber, int id, String name, String major){
        this.trackNumber = trackNumber;
        this.id = id;
        this.name = name;
        this.major = major;
    }

    public void setTrackNumber(int trackNumber) { this.trackNumber = trackNumber; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMajor(String major){
        this.major = major;
    }

    public int getTrackNumber() { return trackNumber; }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getMajor(){
        return major;
    }
}
