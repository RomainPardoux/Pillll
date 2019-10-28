package com.pillll.pillll.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Smrs {

    @SerializedName("data")
    @Expose
    private List<Smr> smrs;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Smr> getData() {
        return smrs;
    }

    public void setData(List<Smr> data) {
        this.smrs = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
