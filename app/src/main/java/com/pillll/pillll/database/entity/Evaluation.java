package com.pillll.pillll.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import com.pillll.pillll.database.converter.DateConverter;
import java.util.Date;

/**
 * Created by Pardoux Romain on 04/01/2019
 */

@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis"),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class Evaluation {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @TypeConverters({DateConverter.class})
    private Date date;
    private String risque;
    private String efficacite;
    @ColumnInfo(name = "interet_pharmaceutique")
    private String interetPharmaceutique;
    private String libelle;
    @ColumnInfo(name = "specialite_id_code_cis")
    private long specialiteIdCodeCis;

    public Evaluation( Date date, String risque, String efficacite, String interetPharmaceutique, String libelle, long specialiteIdCodeCis) {
        this.date = date;
        this.risque = risque;
        this.efficacite = efficacite;
        this.interetPharmaceutique = interetPharmaceutique;
        this.libelle = libelle;
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }

    // GETTER AND SETTER
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRisque() {
        return risque;
    }

    public void setRisque(String risque) {
        this.risque = risque;
    }

    public String getEfficacite() {
        return efficacite;
    }

    public void setEfficacite(String efficacite) {
        this.efficacite = efficacite;
    }

    public String getInteretPharmaceutique() {
        return interetPharmaceutique;
    }

    public void setInteretPharmaceutique(String interetPharmaceutique) {
        this.interetPharmaceutique = interetPharmaceutique;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public long getSpecialiteIdCodeCis() {
        return specialiteIdCodeCis;
    }

    public void setSpecialiteIdCodeCis(long specialiteIdCodeCis) {
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }
}
