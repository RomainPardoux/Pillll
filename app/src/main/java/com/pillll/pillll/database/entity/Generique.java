package com.pillll.pillll.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pardoux Romain on 03/01/2019
 */

@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis"),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class Generique {

    @PrimaryKey
    @ColumnInfo(name = "specialite_id_code_cis")
    @SerializedName("specialiteIdCodeCis")
    @Expose
    private long specialiteIdCodeCis;
    @ColumnInfo(name = "identifiant_groupe")
    @SerializedName("identifiantGroupe")
    @Expose
    private String identifiantGroupe;
    @ColumnInfo(name = "libelle_groupe")
    @SerializedName("libelleGroupe")
    @Expose
    private String libelleGroupe;
    @SerializedName("type")
    @Expose
    private String type;
    @ColumnInfo(name = "numero_tri")
    @SerializedName("numeroTri")
    @Expose
    private String numeroTri;

    public Generique(long specialiteIdCodeCis, String identifiantGroupe, String libelleGroupe, String type, String numeroTri) {
        this.specialiteIdCodeCis = specialiteIdCodeCis;
        this.identifiantGroupe = identifiantGroupe;
        this.libelleGroupe = libelleGroupe;
        this.type = type;
        this.numeroTri = numeroTri;
    }

    public Generique() {
    }

    // GETTER AND SETTER
    public long getSpecialiteIdCodeCis() {
        return specialiteIdCodeCis;
    }

    public void setSpecialiteIdCodeCis(long specialiteIdCodeCis) {
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }

    public String getIdentifiantGroupe() {
        return identifiantGroupe;
    }

    public void setIdentifiantGroupe(String identifiantGroupe) {
        this.identifiantGroupe = identifiantGroupe;
    }

    public String getLibelleGroupe() {
        return libelleGroupe;
    }

    public void setLibelleGroupe(String libelleGroupe) {
        this.libelleGroupe = libelleGroupe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumeroTri() {
        return numeroTri;
    }

    public void setNumeroTri(String numeroTri) {
        this.numeroTri = numeroTri;
    }
}
