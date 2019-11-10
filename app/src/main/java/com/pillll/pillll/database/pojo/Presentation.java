package com.pillll.pillll.database.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pardoux Romain on 09/11/2019
 */

public class Presentation {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("code_cip7")
    @Expose
    private String codeCip7;
    @SerializedName("code_cip13")
    @Expose
    private String codeCip13;
    @SerializedName("libelle")
    @Expose
    private String libelle;
    @SerializedName("statut_administratif")
    @Expose
    private String statutAdministratif;
    @SerializedName("etat_commercialisation")
    @Expose
    private String etatCommercialisation;
    @SerializedName("date_commercialisation")
    @Expose
    private String dateCommercialisation;
    @SerializedName("agrement_collectivites")
    @Expose
    private boolean agrementCollectivites;
    @SerializedName("taux_remboursement")
    @Expose
    private int tauxRemboursement;
    @SerializedName("prix_euros")
    @Expose
    private double prixEuros;
    @SerializedName("prix_euros_hors_honoraire")
    @Expose
    private double prixEurosHorsHonoraire;
    @SerializedName("honoraire")
    @Expose
    private double honoraire;
    @SerializedName("precision_remboursement")
    @Expose
    private String precisionRemboursement;
    @SerializedName("specialite")
    @Expose
    private Specialite specialite;

    //    GETTER AND SETTER

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodeCip7() {
        return codeCip7;
    }

    public void setCodeCip7(String codeCip7) {
        this.codeCip7 = codeCip7;
    }

    public String getCodeCip13() {
        return codeCip13;
    }

    public void setCodeCip13(String codeCip13) {
        this.codeCip13 = codeCip13;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getStatutAdministratif() {
        return statutAdministratif;
    }

    public void setStatutAdministratif(String statutAdministratif) {
        this.statutAdministratif = statutAdministratif;
    }

    public String getEtatCommercialisation() {
        return etatCommercialisation;
    }

    public void setEtatCommercialisation(String etatCommercialisation) {
        this.etatCommercialisation = etatCommercialisation;
    }

    public String getDateCommercialisation() {
        return dateCommercialisation;
    }

    public void setDateCommercialisation(String dateCommercialisation) {
        this.dateCommercialisation = dateCommercialisation;
    }

    public boolean isAgrementCollectivites() {
        return agrementCollectivites;
    }

    public void setAgrementCollectivites(boolean agrementCollectivites) {
        this.agrementCollectivites = agrementCollectivites;
    }

    public int getTauxRemboursement() {
        return tauxRemboursement;
    }

    public void setTauxRemboursement(int tauxRemboursement) {
        this.tauxRemboursement = tauxRemboursement;
    }

    public double getPrixEuros() {
        return prixEuros;
    }

    public void setPrixEuros(double prixEuros) {
        this.prixEuros = prixEuros;
    }

    public double getPrixEurosHorsHonoraire() {
        return prixEurosHorsHonoraire;
    }

    public void setPrixEurosHorsHonoraire(double prixEurosHorsHonoraire) {
        this.prixEurosHorsHonoraire = prixEurosHorsHonoraire;
    }

    public double getHonoraire() {
        return honoraire;
    }

    public void setHonoraire(double honoraire) {
        this.honoraire = honoraire;
    }

    public String getPrecisionRemboursement() {
        return precisionRemboursement;
    }

    public void setPrecisionRemboursement(String precisionRemboursement) {
        this.precisionRemboursement = precisionRemboursement;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
}
