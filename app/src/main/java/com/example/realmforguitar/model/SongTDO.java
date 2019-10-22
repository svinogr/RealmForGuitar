package com.example.realmforguitar.model;

import java.util.ArrayList;
import java.util.List;

public class SongTDO implements Comparable<SongTDO>{
    private String name;
    private String text;
    private String clearText;
    private List<AckordTDO> ackordListTDO = new ArrayList<>();

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

    public List<AckordTDO> getAckordListTDO() {
        return ackordListTDO;
    }

    public void setAckordListTDO(List<AckordTDO> ackordListTDO) {
        this.ackordListTDO = ackordListTDO;
    }

    public String getClearText() {
        return clearText;
    }

    public void setClearText(String clearText) {
        this.clearText = clearText;
    }

    @Override
    public int compareTo(SongTDO o) {
        return this.name.compareTo(o.getName());
    }
}
