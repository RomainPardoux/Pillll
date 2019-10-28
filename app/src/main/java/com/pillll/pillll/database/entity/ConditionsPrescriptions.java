package com.pillll.pillll.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ConditionsPrescriptions {

    @SerializedName("data")
    @Expose
    private List<ConditionPrescription> conditionPrescriptions;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<ConditionPrescription> getData() {
        return conditionPrescriptions;
    }

    public void setData(List<ConditionPrescription> data) {
        this.conditionPrescriptions = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
