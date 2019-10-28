package com.pillll.pillll.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Compositions {

    @SerializedName("data")
    @Expose
    private List<Composition> compositions;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Composition> getData() {
        return compositions;
    }

    public void setData(List<Composition> data) {
        this.compositions = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
