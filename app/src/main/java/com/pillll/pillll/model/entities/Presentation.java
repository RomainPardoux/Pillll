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
 * This class will have a mapping SQLite table in the database for Presentation entity.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis", onDelete = CASCADE, onUpdate = CASCADE),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class Presentation {

    @PrimaryKey
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
    @ColumnInfo(name = "prix_euros_hors_honoraire")
    private double prixEurosHorsHonoraire;
    private double honoraire;
    @ColumnInfo(name = "precision_remboursement")
    private String precisionRemboursement;
    @ColumnInfo(name = "specialite_id_code_cis")
    private long specialiteIdCodeCis;

    public Presentation(long id, String codeCip7, String codeCip13, String libelle, String statutAdministratif, String etatCommercialisation, Date dateCommercialisation, boolean agrementCollectivites, int tauxRemboursement, double prixEuros, double prixEurosHorsHonoraire, double honoraire, String precisionRemboursement, long specialiteIdCodeCis) {
        this.id = id;
        this.codeCip7 = codeCip7;
        this.codeCip13 = codeCip13;
        this.libelle = libelle;
        this.statutAdministratif = statutAdministratif;
        this.etatCommercialisation = etatCommercialisation;
        this.dateCommercialisation = dateCommercialisation;
        this.agrementCollectivites = agrementCollectivites;
        this.tauxRemboursement = tauxRemboursement;
        this.prixEuros = prixEuros;
        this.prixEurosHorsHonoraire = prixEurosHorsHonoraire;
        this.honoraire = honoraire;
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

    public boolean isAgrementCollectivites() {
        return agrementCollectivites;
    }

    public double getPrixEurosHorsHonoraire() {
        return prixEurosHorsHonoraire;
    }

    public double getHonoraire() {
        return honoraire;
    }

    public void setPrixEurosHorsHonoraire(double prixEurosHorsHonoraire) {
        this.prixEurosHorsHonoraire = prixEurosHorsHonoraire;
    }

    public void setHonoraire(double honoraire) {
        this.honoraire = honoraire;
    }
}
