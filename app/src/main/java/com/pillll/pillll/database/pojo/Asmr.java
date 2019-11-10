package com.pillll.pillll.database.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pardoux Romain on 09/11/2019
 */

public class Asmr {

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
    private LienCt lienCt;

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

    public LienCt getLienCt() {
        return lienCt;
    }

    public void setLienCt(LienCt lienCt) {
        this.lienCt = lienCt;
    }
}
