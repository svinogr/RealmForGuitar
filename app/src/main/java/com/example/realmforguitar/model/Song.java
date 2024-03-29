package com.example.realmforguitar.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Song extends RealmObject {
    private String name;
    private String text;
    private boolean isUser;
    private boolean isFavorite;
    private RealmList<Ackord> ackords = new RealmList<>();
    private int parentId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public RealmList<Ackord> getAckords() {
        return ackords;
    }

    public void setAckords(RealmList<Ackord> ackords) {
        this.ackords = ackords;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", ackords=" + ackords.toString() +
                '}';
    }
}
