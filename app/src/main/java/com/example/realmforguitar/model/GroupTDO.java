package com.example.realmforguitar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupTDO implements Comparable<GroupTDO> {
    private String name;
    private String pic;
    private List<SongTDO> tdoSongList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public List<SongTDO> getTdoSongList() {
        return tdoSongList;
    }

    public void setTdoSongList(List<SongTDO> tdoSongList) {
        this.tdoSongList = tdoSongList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupTDO groupTDO = (GroupTDO) o;
        return Objects.equals(name, groupTDO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    @Override
    public int compareTo(GroupTDO o) {
        return this.name.compareTo(o.getName());
    }
}
