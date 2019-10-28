package com.pillll.pillll.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Generiques {

    @SerializedName("data")
    @Expose
    private List<Generique> generiques;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Generique> getData() {
        return generiques;
    }

    public void setData(List<Generique> data) {
        this.generiques = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
