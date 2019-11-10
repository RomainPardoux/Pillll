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
 * Created by Pardoux Romain on 03/01/2019
 */

@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis", onDelete = CASCADE, onUpdate = CASCADE),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class TitulaireSpecialite {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("titulaire")
    @Expose
    private String titulaire;
    @ColumnInfo(name = "specialite_id_code_cis")
    @SerializedName("specialite_id_code_cis")
    @Expose
    private long specialiteIdCodeCis;

    public TitulaireSpecialite(String titulaire, long specialiteIdCodeCis) {
        this.titulaire = titulaire;
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }

    public TitulaireSpecialite() {
    }

    // GETTER AND SETTER
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    public long getSpecialiteIdCodeCis() {
        return specialiteIdCodeCis;
    }

    public void setSpecialiteIdCodeCis(long specialiteIdCodeCis) {
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }
}
