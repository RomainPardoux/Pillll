package com.pill.pill.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Pardoux Romain on 17/01/2019
 */

@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis"),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class VoiesAdministration {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "voies_administration")
    private String voiesAdministration;
    @ColumnInfo(name = "specialite_id_code_cis")
    private long specialiteIdCodeCis;

    public VoiesAdministration(String voiesAdministration, long specialiteIdCodeCis) {
        this.voiesAdministration = voiesAdministration;
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }

    public VoiesAdministration() {
    }

    // GETTER AND SETTER
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVoiesAdministration() {
        return voiesAdministration;
    }

    public void setVoiesAdministration(String voiesAdministration) {
        this.voiesAdministration = voiesAdministration;
    }

    public long getSpecialiteIdCodeCis() {
        return specialiteIdCodeCis;
    }

    public void setSpecialiteIdCodeCis(long specialiteIdCodeCis) {
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }
}
