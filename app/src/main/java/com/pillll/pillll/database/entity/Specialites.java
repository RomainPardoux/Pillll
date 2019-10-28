package com.pillll.pillll.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Specialites {

    @SerializedName("data")
    @Expose
    private List<Specialite> specialites;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Specialite> getData() {
        return specialites;
    }

    public void setData(List<Specialite> data) {
        this.specialites = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
