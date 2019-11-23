package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pillll.pillll.model.entities.Presentation;
import java.util.List;

/**
 * POJO used to retrieve JSON Presentations data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class PresentationsData {

    @SerializedName("data")
    @Expose
    private List<com.pillll.pillll.model.entities.Presentation> presentations;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<com.pillll.pillll.model.entities.Presentation> getData() {
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
