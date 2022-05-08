package com.istvanbalint.eletbiztositas;

public class User {
    String uID;
    String firstName;
    String lastName;
    String emailAddr;
    int age;
    String insuranceType;

    public User(){}


    public User(String uID,String firstName, String lastName, String emailAddr, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddr = emailAddr;
        this.age = age;
        this.uID=uID;
        this.insuranceType="Nincs jelenleg élő biztosítása";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getinsuranceType() {
        return insuranceType;
    }

    public void setinsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }
}
