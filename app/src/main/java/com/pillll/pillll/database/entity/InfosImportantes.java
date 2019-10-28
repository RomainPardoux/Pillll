package com.pillll.pillll.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfosImportantes {

    @SerializedName("data")
    @Expose
    private List<InfoImportante> infosImportantes;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<InfoImportante> getData() {
        return infosImportantes;
    }

    public void setData(List<InfoImportante> data) {
        this.infosImportantes = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
