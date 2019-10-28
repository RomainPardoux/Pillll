package com.pillll.pillll.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TitulairesSpecialites {

    @SerializedName("data")
    @Expose
    private List<TitulaireSpecialite> titulaireSpecialites;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<TitulaireSpecialite> getData() {
        return titulaireSpecialites;
    }

    public void setData(List<TitulaireSpecialite> data) {
        this.titulaireSpecialites = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
