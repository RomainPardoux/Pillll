package com.pill.pill.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Pardoux Romain on 02/01/2019
 */

@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis"),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class ConditionPrescription {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "condition_prescription")
    private String conditionPrescription;
    @ColumnInfo(name = "specialite_id_code_cis")
    private long specialiteIdCodeCis;

    public ConditionPrescription( String conditionPrescription, long specialiteIdCodeCis) {
        this.conditionPrescription = conditionPrescription;
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }

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

    public long getSpecialiteIdCodeCis() {
        return specialiteIdCodeCis;
    }

    public void setSpecialiteIdCodeCis(long specialiteIdCodeCis) {
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }
}
