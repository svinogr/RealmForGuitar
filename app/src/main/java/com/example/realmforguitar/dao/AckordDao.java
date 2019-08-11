package com.example.realmforguitar.dao;

import com.example.realmforguitar.model.Ackord;
import com.example.realmforguitar.model.Group;

import io.realm.Realm;

public class AckordDao {
    private static Realm realm = Realm.getDefaultInstance();

    public Ackord createItem(Ackord item) {
        int id = incrementId();
        item.setId(id);

        Ackord byName = getByName(item.getName());

        if(byName != null) {return byName;}

        realm.beginTransaction();
        item = realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();

        return item;
    }

    public Ackord getByName(String name) {
        return realm.where(Ackord.class).equalTo("name", name).findFirst();
    }

    private int incrementId() {
        Number id = realm.where(Ackord.class).max("id");

        if (id == null) {
            id = 1;
        } else {
            id = id.intValue() + 1;
        }

        return id.intValue();
    }
}
