package com.acadgildbatch5.sqlite;

/**
 * Created by Divyanshu on 18-12-2016.
 */

public class Contacts {

    int id;
    String name;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String phone_number;

    public Contacts(){

    }

    public Contacts(int id, String name, String phone_number){
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
    }
    public Contacts( String name, String phone_number){
        this.name = name;
        this.phone_number = phone_number;
    }



}
