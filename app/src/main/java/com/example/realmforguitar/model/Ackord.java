package com.example.realmforguitar.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Ackord  extends RealmObject {
    private String name;
    private byte[] imageData;
    @PrimaryKey
    private int id;

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

    @Override
    public String toString() {
        return "Ackord{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
