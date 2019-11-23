package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO used to retrieve JSON Condition Prescription data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class ConditionPrescriptionData {

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
