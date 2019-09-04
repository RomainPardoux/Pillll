package com.pillll.pillll.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pillll.pillll.database.converter.DateConverter;
import java.util.Date;

/**
 * Created by Pardoux Romain on 04/01/2019
 */

@Entity(foreignKeys = {@ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis"),
        @ForeignKey(entity = LienCt.class, parentColumns = "code_dossier_has", childColumns = "lien_ct_code_dossier_has")},
        indices = {@Index(value = {"lien_ct_code_dossier_has"}),
                @Index(value = {"specialite_id_code_cis"})})
public class Asmr {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private long id;
    @ColumnInfo(name = "motif_evaluation")
    @SerializedName("motifEvaluation")
    @Expose
    private String motifEvaluation;
    @ColumnInfo(name = "date_avis_ct")
    @TypeConverters({DateConverter.class})
    @SerializedName("dateAvisCt")
    @Expose
    private Date dateAvisCt;
    @SerializedName("valeur")
    @Expose
    private String valeur;
    @SerializedName("libelle")
    @Expose
    private String libelle;
    @ColumnInfo(name = "lien_ct_code_dossier_has")
    @SerializedName("lienCtCodeDossierHas")
    @Expose
    private String lienCtCodeDossierHas;
    @ColumnInfo(name = "specialite_id_code_cis")
    @SerializedName("specialiteIdCodeCis")
    @Expose
    private long specialiteIdCodeCis;

    public Asmr( String motifEvaluation, Date dateAvisCt, String valeur, String libelle, String lienCtCodeDossierHas, long specialiteIdCodeCis) {
        this.motifEvaluation = motifEvaluation;
        this.dateAvisCt = dateAvisCt;
        this.valeur = valeur;
        this.libelle = libelle;
        this.lienCtCodeDossierHas = lienCtCodeDossierHas;
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }

    public Asmr() {
    }

    // GETTER AND SETTER
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMotifEvaluation() {
        return motifEvaluation;
    }

    public void setMotifEvaluation(String motifEvaluation) {
        this.motifEvaluation = motifEvaluation;
    }

    public Date getDateAvisCt() {
        return dateAvisCt;
    }

    public void setDateAvisCt(Date dateAvisCt) {
        this.dateAvisCt = dateAvisCt;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLienCtCodeDossierHas() {
        return lienCtCodeDossierHas;
    }

    public void setLienCtCodeDossierHas(String lienCtCodeDossierHas) {
        this.lienCtCodeDossierHas = lienCtCodeDossierHas;
    }

    public long getSpecialiteIdCodeCis() {
        return specialiteIdCodeCis;
    }

    public void setSpecialiteIdCodeCis(long specialiteIdCodeCis) {
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }
}
