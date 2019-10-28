package com.pillll.pillll.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class VoiesAdministrations {

    @SerializedName("data")
    @Expose
    private List<VoiesAdministration> voiesAdministrations;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<VoiesAdministration> getData() {
        return voiesAdministrations;
    }

    public void setData(List<VoiesAdministration> data) {
        this.voiesAdministrations = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
