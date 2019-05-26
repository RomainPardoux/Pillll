package com.pill.pill.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import com.pill.pill.database.converter.DateConverter;
import java.util.Date;

/**
 * Created by Pardoux Romain on 02/01/2019
 */

@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis"),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class Presentation {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "code_cip7")
    private String codeCip7;
    @ColumnInfo(name = "code_cip13")
    private String codeCip13;
    private String libelle;
    @ColumnInfo(name = "statut_administratif")
    private String statutAdministratif;
    @ColumnInfo(name = "etat_commercialisation")
    private String etatCommercialisation;
    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "date_commercialisation")
    private Date dateCommercialisation;
    @ColumnInfo(name = "agrement_collectivites")
    private boolean agrementCollectivites;
    @ColumnInfo(name = "taux_remboursement")
    private int tauxRemboursement;
    @ColumnInfo(name = "prix_euros")
    private double prixEuros;
    @ColumnInfo(name = "precision_remboursement")
    private String precisionRemboursement;
    @ColumnInfo(name = "specialite_id_code_cis")
    private long specialiteIdCodeCis;

    public Presentation(String codeCip7, String codeCip13, String libelle, String statutAdministratif, String etatCommercialisation, Date dateCommercialisation, boolean agrementCollectivites, int tauxRemboursement, double prixEuros, String precisionRemboursement, long specialiteIdCodeCis) {
        this.codeCip7 = codeCip7;
        this.codeCip13 = codeCip13;
        this.libelle = libelle;
        this.statutAdministratif = statutAdministratif;
        this.etatCommercialisation = etatCommercialisation;
        this.dateCommercialisation = dateCommercialisation;
        this.agrementCollectivites = agrementCollectivites;
        this.tauxRemboursement = tauxRemboursement;
        this.prixEuros = prixEuros;
        this.precisionRemboursement = precisionRemboursement;
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }

    public Presentation() {
    }

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

    public Date getDateCommercialisation() {
        return dateCommercialisation;
    }

    public void setDateCommercialisation(Date dateCommercialisation) {
        this.dateCommercialisation = dateCommercialisation;
    }

    public boolean getAgrementCollectivites() {
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

    public String getPrecisionRemboursement() {
        return precisionRemboursement;
    }

    public void setPrecisionRemboursement(String precisionRemboursement) {
        this.precisionRemboursement = precisionRemboursement;
    }

    public long getSpecialiteIdCodeCis() {
        return specialiteIdCodeCis;
    }

    public void setSpecialiteIdCodeCis(long specialiteIdCodeCis) {
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }
}
