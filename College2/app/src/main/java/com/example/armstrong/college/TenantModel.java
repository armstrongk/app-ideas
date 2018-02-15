package com.example.armstrong.college;

public class TenantModel {
    String name;
    String contact;

    public TenantModel(){

    }

    public TenantModel(String name, String contact){
        this.name = name;
        this.contact = contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}
