package com.pillll.pillll.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Pardoux Romain on 17/01/2019
 */

@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis", onDelete = CASCADE, onUpdate = CASCADE),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class VoiesAdministration {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private long id;
    @ColumnInfo(name = "voies_administration")
    @SerializedName("voies_administration")
    @Expose
    private String voiesAdministration;
    @ColumnInfo(name = "specialite_id_code_cis")
    @SerializedName("specialite_id_code_cis")
    @Expose
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
