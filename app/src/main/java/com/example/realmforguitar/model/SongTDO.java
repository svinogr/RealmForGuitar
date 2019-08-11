package com.example.realmforguitar.model;

import java.util.ArrayList;
import java.util.List;

public class SongTDO {
    private String name;
    private String text;
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
}
