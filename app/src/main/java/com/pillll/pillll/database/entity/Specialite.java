package com.pillll.pillll.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pillll.pillll.database.converter.DateConverter;
import java.util.Date;

/**
 * Created by Pardoux Romain on 02/01/2019
 */

@Entity
public class Specialite {
    @PrimaryKey
    @ColumnInfo(name = "id_code_cis")
    @SerializedName("idCodeCis")
    @Expose
    private long idCodeCis;
    @SerializedName("denomination")
    @Expose
    private String denomination;
    @ColumnInfo(name = "forme_pharmaceutique")
    @SerializedName("formePharmaceutique")
    @Expose
    private String formePharmaceutique;
    @ColumnInfo(name = "statut_administratif_amm")
    @SerializedName("statutAdministratifAmm")
    @Expose
    private String statutAdministratifAmm;
    @ColumnInfo(name = "type_procedure_amm")
    @SerializedName("typeProcedureAmm")
    @Expose
    private String typeProcedureAmm;
    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "date_amm")
    @SerializedName("dateAmm")
    @Expose
    private Date dateAmm;
    @ColumnInfo(name = "statut_bdm")
    @SerializedName("statutBdm")
    @Expose
    private String statutBdm;
    @ColumnInfo(name = "numero_autorisation_euro")
    @SerializedName("numeroAutorisationEuro")
    @Expose
    private String numeroAutorisationEuro;
    @ColumnInfo(name = "surveillance_renforcee")
    @SerializedName("surveillanceRenforcee")
    @Expose
    private boolean surveillanceRenforcee;
    @ColumnInfo(name = "etat_commercialisation")
    @SerializedName("etatCommercialisation")
    @Expose
    private String etatCommercialisation;

    public Specialite(long idCodeCis, String denomination, String formePharmaceutique, String statutAdministratifAmm, String typeProcedureAmm, Date dateAmm, String statutBdm, String numeroAutorisationEuro, boolean surveillanceRenforcee, String etatCommercialisation) {
        this.idCodeCis = idCodeCis;
        this.denomination = denomination;
        this.formePharmaceutique = formePharmaceutique;
        this.statutAdministratifAmm = statutAdministratifAmm;
        this.typeProcedureAmm = typeProcedureAmm;
        this.dateAmm = dateAmm;
        this.statutBdm = statutBdm;
        this.numeroAutorisationEuro = numeroAutorisationEuro;
        this.surveillanceRenforcee = surveillanceRenforcee;
        this.etatCommercialisation = etatCommercialisation;
    }

    public Specialite() {
    }

    // GETTER AND SETTER
    public long getIdCodeCis() {
        return idCodeCis;
    }

    public void setIdCodeCis(long idCodeCis) {
        this.idCodeCis = idCodeCis;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getFormePharmaceutique() {
        return formePharmaceutique;
    }

    public void setFormePharmaceutique(String formePharmaceutique) {
        this.formePharmaceutique = formePharmaceutique;
    }

    public String getStatutAdministratifAmm() {
        return statutAdministratifAmm;
    }

    public void setStatutAdministratifAmm(String statutAdministratifAmm) {
        this.statutAdministratifAmm = statutAdministratifAmm;
    }

    public String getTypeProcedureAmm() {
        return typeProcedureAmm;
    }

    public void setTypeProcedureAmm(String typeProcedureAmm) {
        this.typeProcedureAmm = typeProcedureAmm;
    }

    public Date getDateAmm() {
        return dateAmm;
    }

    public void setDateAmm(Date dateAmm) {
        this.dateAmm = dateAmm;
    }

    public String getStatutBdm() {
        return statutBdm;
    }

    public void setStatutBdm(String statutBdm) {
        this.statutBdm = statutBdm;
    }

    public String getNumeroAutorisationEuro() {
        return numeroAutorisationEuro;
    }

    public void setNumeroAutorisationEuro(String numeroAutorisationEuro) {
        this.numeroAutorisationEuro = numeroAutorisationEuro;
    }

    public boolean isSurveillanceRenforcee() {
        return surveillanceRenforcee;
    }

    public void setSurveillanceRenforcee(boolean surveillanceRenforcee) {
        this.surveillanceRenforcee = surveillanceRenforcee;
    }

    public String getEtatCommercialisation() {
        return etatCommercialisation;
    }

    public void setEtatCommercialisation(String etatCommercialisation) {
        this.etatCommercialisation = etatCommercialisation;
    }
}