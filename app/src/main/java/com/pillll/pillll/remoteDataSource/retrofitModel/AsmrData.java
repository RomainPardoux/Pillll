package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO used to retrieve JSON Asmr data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class AsmrData {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("motif_evaluation")
    @Expose
    private String motifEvaluation;
    @SerializedName("date_avis_ct")
    @Expose
    private String dateAvisCt;
    @SerializedName("valeur")
    @Expose
    private String valeur;
    @SerializedName("libelle")
    @Expose
    private String libelle;
    @SerializedName("lien_ct")
    @Expose
    private LienCtData lienCt;

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

    public String getDateAvisCt() {
        return dateAvisCt;
    }

    public void setDateAvisCt(String dateAvisCt) {
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

    public LienCtData getLienCt() {
        return lienCt;
    }

    public void setLienCt(LienCtData lienCt) {
        this.lienCt = lienCt;
    }
}
