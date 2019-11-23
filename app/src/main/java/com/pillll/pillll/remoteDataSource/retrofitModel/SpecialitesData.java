package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pillll.pillll.model.entities.Specialite;

import java.util.List;

/**
 * POJO used to retrieve JSON Specialites data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class SpecialitesData {

    @SerializedName("data")
    @Expose
    private List<com.pillll.pillll.model.entities.Specialite> specialites;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<com.pillll.pillll.model.entities.Specialite> getData() {
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
