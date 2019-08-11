package com.example.realmforguitar.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Group  extends RealmObject {
    private String name;
    private String descriptionGroup;
    private boolean isUser;
    private byte[] imgData;
    private boolean isFavoritel;
    private RealmList<Song> listSings = new RealmList<>();
    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public RealmList<Song> getListSings() {
        return listSings;
    }

    public void setListSings(RealmList<Song> listSings) {
        this.listSings = listSings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionGroup() {
        return descriptionGroup;
    }

    public void setDescriptionGroup(String descriptionGroup) {
        this.descriptionGroup = descriptionGroup;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public byte[] getImgData() {
        return imgData;
    }

    public void setImgData(byte[] imgData) {
        this.imgData = imgData;
    }

    public boolean isFavoritel() {
        return isFavoritel;
    }

    public void setFavoritel(boolean favoritel) {
        isFavoritel = favoritel;
    }

    @Override
    public String toString() {


        return "Group{" +
                "name='" + name + '\'' + listSings.toString()
               + '}';
    }
}
