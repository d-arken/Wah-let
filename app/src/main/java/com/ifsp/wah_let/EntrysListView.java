package com.ifsp.wah_let;

import java.util.Date;


public class EntrysListView {
    private float value;
    private String type;
    private Date date;

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



}