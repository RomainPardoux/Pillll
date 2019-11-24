package com.pillll.pillll.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.pillll.pillll.model.converter.DateConverter;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

/**
 * This class will have a mapping SQLite table in the database for Asmr entity.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Entity(foreignKeys = {@ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = LienCt.class, parentColumns = "code_dossier_has", childColumns = "lien_ct_code_dossier_has", onDelete = CASCADE, onUpdate = CASCADE)},
        indices = {@Index(value = {"lien_ct_code_dossier_has"}),
                @Index(value = {"specialite_id_code_cis"})})
public class Asmr {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "motif_evaluation")
    private String motifEvaluation;
    @ColumnInfo(name = "date_avis_ct")
    @TypeConverters({DateConverter.class})
    private Date dateAvisCt;
    private String valeur;
    private String libelle;
    @ColumnInfo(name = "lien_ct_code_dossier_has")
    private String lienCtCodeDossierHas;
    @ColumnInfo(name = "specialite_id_code_cis")
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
