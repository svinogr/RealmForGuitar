package com.example.realmforguitar.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Ackord  extends RealmObject {
    @Required
    private String name = "";
    private byte[] imageData;
    private boolean isUser = false;
    @PrimaryKey
    private int id = 0;

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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    @Override
    public String toString() {
        return "Ackord{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
