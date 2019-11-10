package com.pillll.pillll.database.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pillll.pillll.database.entity.Presentation;

import java.util.List;

public class Presentations {

    @SerializedName("data")
    @Expose
    private List<com.pillll.pillll.database.entity.Presentation> presentations;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<com.pillll.pillll.database.entity.Presentation> getData() {
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
