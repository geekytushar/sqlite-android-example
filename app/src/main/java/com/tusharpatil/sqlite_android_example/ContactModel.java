package com.tusharpatil.sqlite_android_example;

/**
 * Created by tushar on 02/09/18.
 */

public class ContactModel {
    int id;
    String name, mobile;

    public ContactModel() {
    }

    public ContactModel(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public ContactModel(int id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
