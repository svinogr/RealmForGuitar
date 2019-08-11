package com.example.realmforguitar.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CommonWithId extends RealmObject {
    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
