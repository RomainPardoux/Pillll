package com.pillll.pillll.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Presentations {

    @SerializedName("data")
    @Expose
    private List<Presentation> presentations;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Presentation> getData() {
        return presentations;
    }

    public void setData(List<Presentation> data) {
        this.presentations = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
