package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * POJO used to retrieve JSON Specialite data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class SpecialiteData {

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
    private List<CompositionData> compositions;
    @SerializedName("asmrs")
    @Expose
    private List<AsmrData> asmrs;
    @SerializedName("smrs")
    @Expose
    private List<SmrData> smrs;
    @SerializedName("condition_prescriptions")
    @Expose
    private List<ConditionPrescriptionData> conditionPrescriptions;
    @SerializedName("info_importantes")
    @Expose
    private List<InfoImportanteData> infoImportantes;
    @SerializedName("titulaire_specialites")
    @Expose
    private List<TitulaireSpecialiteData> titulaireSpecialites;
    @SerializedName("voies_administrations")
    @Expose
    private List<VoiesAdministrationData> voiesAdministrations;
    @SerializedName("generique")
    @Expose
    private GeneriqueData generique;

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

    public List<CompositionData> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<CompositionData> compositions) {
        this.compositions = compositions;
    }

    public List<AsmrData> getAsmrs() {
        return asmrs;
    }

    public void setAsmrs(List<AsmrData> asmrs) {
        this.asmrs = asmrs;
    }

    public List<SmrData> getSmrs() {
        return smrs;
    }

    public void setSmrs(List<SmrData> smrs) {
        this.smrs = smrs;
    }

    public List<ConditionPrescriptionData> getConditionPrescriptions() {
        return conditionPrescriptions;
    }

    public void setConditionPrescriptions(List<ConditionPrescriptionData> conditionPrescriptions) {
        this.conditionPrescriptions = conditionPrescriptions;
    }

    public List<InfoImportanteData> getInfoImportantes() {
        return infoImportantes;
    }

    public void setInfoImportantes(List<InfoImportanteData> infoImportantes) {
        this.infoImportantes = infoImportantes;
    }

    public List<TitulaireSpecialiteData> getTitulaireSpecialites() {
        return titulaireSpecialites;
    }

    public void setTitulaireSpecialites(List<TitulaireSpecialiteData> titulaireSpecialites) {
        this.titulaireSpecialites = titulaireSpecialites;
    }

    public List<VoiesAdministrationData> getVoiesAdministrations() {
        return voiesAdministrations;
    }

    public void setVoiesAdministrations(List<VoiesAdministrationData> voiesAdministrations) {
        this.voiesAdministrations = voiesAdministrations;
    }

    public GeneriqueData getGenerique() {
        return generique;
    }

    public void setGenerique(GeneriqueData generique) {
        this.generique = generique;
    }
}
