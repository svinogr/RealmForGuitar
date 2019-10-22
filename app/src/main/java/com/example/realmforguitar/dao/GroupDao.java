package com.example.realmforguitar.dao;

import com.example.realmforguitar.model.Group;
import com.example.realmforguitar.model.Song;

import io.realm.Realm;

public class GroupDao {
    private static Realm realm = Realm.getDefaultInstance();

    public Group createItem(Group item) {
        int id = incrementId();
        item.setId(id);

        realm.beginTransaction();
        item = realm.copyToRealmOrUpdate(item);

        for (Song song: item.getListSongs()){
            song.setParentId(item.getId());
        }
        realm.commitTransaction();

        return item;
    }
    public boolean getByName(String name) {
        Group name1 = realm.where(Group.class).equalTo("name", name).findFirst();
        return  name1 != null;
    }

    private int incrementId() {
        Number id = realm.where(Group.class).max("id");

        if (id == null) {
            id = 1;
        } else {
            id = id.intValue() + 1;
        }

        return id.intValue();
    }
}
