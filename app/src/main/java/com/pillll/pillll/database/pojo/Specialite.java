package com.pillll.pillll.database.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pardoux Romain on 09/11/2019
 */

public class Specialite {

    @SerializedName("id")
    @Expose
    private long idCodeCis;
    @SerializedName("denomination")
    @Expose
    private String denomination;
    @SerializedName("forme_pharmaceutique")
    @Expose
    private String formePharmaceutique;
    @SerializedName("statut_administratif_amm")
    @Expose
    private String statutAdministratifAmm;
    @SerializedName("type_procedure_amm")
    @Expose
    private String typeProcedureAmm;
    @SerializedName("date_amm")
    @Expose
    private String dateAmm;
    @SerializedName("statut_bdm")
    @Expose
    private String statutBdm;
    @SerializedName("numero_autorisation_euro")
    @Expose
    private String numeroAutorisationEuro;
    @SerializedName("surveillance_renforcee")
    @Expose
    private boolean surveillanceRenforcee;
    @SerializedName("etat_commercialisation")
    @Expose
    private String etatCommercialisation;

    @SerializedName("compositions")
    @Expose
    private List<Composition> compositions;
    @SerializedName("asmrs")
    @Expose
    private List<Asmr> asmrs;
    @SerializedName("smrs")
    @Expose
    private List<Smr> smrs;
    @SerializedName("condition_prescriptions")
    @Expose
    private List<ConditionPrescription> conditionPrescriptions;
    @SerializedName("info_importantes")
    @Expose
    private List<InfoImportante> infoImportantes;
    @SerializedName("titulaire_specialites")
    @Expose
    private List<TitulaireSpecialite> titulaireSpecialites;
    @SerializedName("voies_administrations")
    @Expose
    private List<VoiesAdministration> voiesAdministrations;
    @SerializedName("generique")
    @Expose
    private Generique generique;

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

    public String getDateAmm() {
        return dateAmm;
    }

    public void setDateAmm(String dateAmm) {
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

    public List<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }

    public List<Asmr> getAsmrs() {
        return asmrs;
    }

    public void setAsmrs(List<Asmr> asmrs) {
        this.asmrs = asmrs;
    }

    public List<Smr> getSmrs() {
        return smrs;
    }

    public void setSmrs(List<Smr> smrs) {
        this.smrs = smrs;
    }

    public List<ConditionPrescription> getConditionPrescriptions() {
        return conditionPrescriptions;
    }

    public void setConditionPrescriptions(List<ConditionPrescription> conditionPrescriptions) {
        this.conditionPrescriptions = conditionPrescriptions;
    }

    public List<InfoImportante> getInfoImportantes() {
        return infoImportantes;
    }

    public void setInfoImportantes(List<InfoImportante> infoImportantes) {
        this.infoImportantes = infoImportantes;
    }

    public List<TitulaireSpecialite> getTitulaireSpecialites() {
        return titulaireSpecialites;
    }

    public void setTitulaireSpecialites(List<TitulaireSpecialite> titulaireSpecialites) {
        this.titulaireSpecialites = titulaireSpecialites;
    }

    public List<VoiesAdministration> getVoiesAdministrations() {
        return voiesAdministrations;
    }

    public void setVoiesAdministrations(List<VoiesAdministration> voiesAdministrations) {
        this.voiesAdministrations = voiesAdministrations;
    }

    public Generique getGenerique() {
        return generique;
    }

    public void setGenerique(Generique generique) {
        this.generique = generique;
    }
}
