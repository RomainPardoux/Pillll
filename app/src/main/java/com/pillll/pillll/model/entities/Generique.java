package com.pillll.pillll.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * This class will have a mapping SQLite table in the database for Generique entity.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis", onDelete = CASCADE, onUpdate = CASCADE),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class Generique {

    @PrimaryKey
    @ColumnInfo(name = "specialite_id_code_cis")
    private long specialiteIdCodeCis;
    @ColumnInfo(name = "identifiant_groupe")
    private String identifiantGroupe;
    @ColumnInfo(name = "libelle_groupe")
    private String libelleGroupe;
    private String type;
    @ColumnInfo(name = "numero_tri")
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
