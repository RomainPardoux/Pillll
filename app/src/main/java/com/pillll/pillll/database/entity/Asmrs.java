package com.pillll.pillll.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Asmrs {

    @SerializedName("data")
    @Expose
    private List<Asmr> asmrs;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Asmr> getData() {
        return asmrs;
    }

    public void setData(List<Asmr> data) {
        this.asmrs = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
