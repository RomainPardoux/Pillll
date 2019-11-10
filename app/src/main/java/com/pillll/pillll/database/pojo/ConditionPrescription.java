package com.pillll.pillll.database.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pardoux Romain on 09/11/2019
 */

public class ConditionPrescription {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("condition_prescription")
    @Expose
    private String conditionPrescription;

    // GETTER AND SETTER

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConditionPrescription() {
        return conditionPrescription;
    }

    public void setConditionPrescription(String conditionPrescription) {
        this.conditionPrescription = conditionPrescription;
    }
}
