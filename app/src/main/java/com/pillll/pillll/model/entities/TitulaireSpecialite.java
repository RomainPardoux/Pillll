package com.pillll.pillll.model.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * This class will have a mapping SQLite table in the database for Titulaire Specialite entity.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis", onDelete = CASCADE, onUpdate = CASCADE),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class TitulaireSpecialite {

    @PrimaryKey
    private long id;
    private String titulaire;
    @ColumnInfo(name = "specialite_id_code_cis")
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
