package com.example.realmforguitar.dao;

import com.example.realmforguitar.model.Group;
import com.example.realmforguitar.model.Song;

import io.realm.Realm;

public class SongDao {
    private static Realm realm = Realm.getDefaultInstance();

    public Song createItem(Song item) {
        int id = incrementId();
        item.setId(id);

        realm.beginTransaction();
        item = realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();

        return item;
    }

    public boolean getByName(String name) {
        Song name1 = realm.where(Song.class).equalTo("name", name).findFirst();
        return name1 != null;
    }

    private int incrementId() {
        Number id = realm.where(Song.class).max("id");

        if (id == null) {
            id = 1;
        } else {
            id = id.intValue() + 1;
        }

        return id.intValue();
    }

    public void update(Song song) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(song);
        realm.commitTransaction();
    }
}
