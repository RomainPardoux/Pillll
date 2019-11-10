package com.pillll.pillll.database.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pillll.pillll.database.entity.Specialite;

import java.util.List;

public class Specialites {

    @SerializedName("data")
    @Expose
    private List<com.pillll.pillll.database.entity.Specialite> specialites;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<com.pillll.pillll.database.entity.Specialite> getData() {
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
