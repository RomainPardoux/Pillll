package com.pillll.pillll.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Pardoux Romain on 02/01/2019
 */

@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis", onDelete = CASCADE, onUpdate = CASCADE),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class Composition {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private long id;
    @ColumnInfo(name = "element_pharmaceutique")
    @SerializedName("element_pharmaceutique")
    @Expose
    private String elementPharmaceutique;
    @ColumnInfo(name = "code_substance")
    @SerializedName("code_substance")
    @Expose
    private String codeSubstance;
    @ColumnInfo(name = "denomination_substance")
    @SerializedName("denomination_substance")
    @Expose
    private String denominationSubstance;
    @ColumnInfo(name = "dosage_substance")
    @SerializedName("dosage_substance")
    @Expose
    private String dosageSubstance;
    @ColumnInfo(name = "reference_dosage")
    @SerializedName("reference_dosage")
    @Expose
    private String referenceDosage;
    @ColumnInfo(name = "nature_composant")
    @SerializedName("nature_composant")
    @Expose
    private String natureComposant;
    @ColumnInfo(name = "numero_liaison")
    @SerializedName("numero_liaison")
    @Expose
    private int numeroLiaison;
    @ColumnInfo(name = "specialite_id_code_cis")
    @SerializedName("specialite_id_code_cis")
    @Expose
    private long specialiteIdCodeCis;

    public Composition(String elementPharmaceutique, String codeSubstance, String denominationSubstance, String dosageSubstance, String referenceDosage, String natureComposant, int numeroLiaison, long specialiteIdCodeCis) {
        this.elementPharmaceutique = elementPharmaceutique;
        this.codeSubstance = codeSubstance;
        this.denominationSubstance = denominationSubstance;
        this.dosageSubstance = dosageSubstance;
        this.referenceDosage = referenceDosage;
        this.natureComposant = natureComposant;
        this.numeroLiaison = numeroLiaison;
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }

    public Composition() {
    }

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

    public long getSpecialiteIdCodeCis() {
        return specialiteIdCodeCis;
    }

    public void setSpecialiteIdCodeCis(long specialiteIdCodeCis) {
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }
}
