package com.pillll.pillll.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * This class will have a mapping SQLite table in the database for Condition Prescription entity.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis", onDelete = CASCADE, onUpdate = CASCADE),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class ConditionPrescription {

    @PrimaryKey
    private long id;
    @ColumnInfo(name = "condition_prescription")
    private String conditionPrescription;
    @ColumnInfo(name = "specialite_id_code_cis")
    private long specialiteIdCodeCis;

    public ConditionPrescription( String conditionPrescription, long specialiteIdCodeCis) {
        this.conditionPrescription = conditionPrescription;
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }

    public ConditionPrescription() {
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
