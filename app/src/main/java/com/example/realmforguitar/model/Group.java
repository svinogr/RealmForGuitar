package com.example.realmforguitar.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Group  extends RealmObject {
    @Required
    private String name = "";
    @Required
    private String descriptionGroup = "";
    private boolean isUser;
    private byte[] imgData;
    private boolean isFavorite;
    private RealmList<Song> listSongs = new RealmList<>();
    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public RealmList<Song> getListSongs() {
        return listSongs;
    }

    public void setListSongs(RealmList<Song> listSongs) {
        this.listSongs = listSongs;
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
        return isFavorite;
    }

    public void setFavoritel(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {


        return "Group{" +
                "name='" + name + '\'' + listSongs.toString()
               + '}';
    }
}
