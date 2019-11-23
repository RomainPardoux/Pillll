package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO used to retrieve JSON Composition data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class CompositionData {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("element_pharmaceutique")
    @Expose
    private String elementPharmaceutique;
    @SerializedName("code_substance")
    @Expose
    private String codeSubstance;
    @SerializedName("denomination_substance")
    @Expose
    private String denominationSubstance;
    @SerializedName("dosage_substance")
    @Expose
    private String dosageSubstance;
    @SerializedName("reference_dosage")
    @Expose
    private String referenceDosage;
    @SerializedName("nature_composant")
    @Expose
    private String natureComposant;
    @SerializedName("numero_liaison")
    @Expose
    private int numeroLiaison;

    //   GETTER AND SETTER

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getElementPharmaceutique() {
        return elementPharmaceutique;
    }

    public void setElementPharmaceutique(String elementPharmaceutique) {
        this.elementPharmaceutique = elementPharmaceutique;
    }

    public String getCodeSubstance() {
        return codeSubstance;
    }

    public void setCodeSubstance(String codeSubstance) {
        this.codeSubstance = codeSubstance;
    }

    public String getDenominationSubstance() {
        return denominationSubstance;
    }

    public void setDenominationSubstance(String denominationSubstance) {
        this.denominationSubstance = denominationSubstance;
    }

    public String getDosageSubstance() {
        return dosageSubstance;
    }

    public void setDosageSubstance(String dosageSubstance) {
        this.dosageSubstance = dosageSubstance;
    }

    public String getReferenceDosage() {
        return referenceDosage;
    }

    public void setReferenceDosage(String referenceDosage) {
        this.referenceDosage = referenceDosage;
    }

    public String getNatureComposant() {
        return natureComposant;
    }

    public void setNatureComposant(String natureComposant) {
        this.natureComposant = natureComposant;
    }

    public int getNumeroLiaison() {
        return numeroLiaison;
    }

    public void setNumeroLiaison(int numeroLiaison) {
        this.numeroLiaison = numeroLiaison;
    }
}
