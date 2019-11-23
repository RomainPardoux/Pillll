package com.pillll.pillll.remoteDataSource;

import com.pillll.pillll.model.entities.Asmr;
import com.pillll.pillll.model.entities.Composition;
import com.pillll.pillll.model.entities.ConditionPrescription;
import com.pillll.pillll.model.entities.Generique;
import com.pillll.pillll.model.entities.InfoImportante;
import com.pillll.pillll.model.entities.LienCt;
import com.pillll.pillll.model.entities.Presentation;
import com.pillll.pillll.model.entities.Smr;
import com.pillll.pillll.model.entities.Specialite;
import com.pillll.pillll.model.entities.TitulaireSpecialite;
import com.pillll.pillll.model.entities.VoiesAdministration;
import com.pillll.pillll.remoteDataSource.retrofitModel.AsmrData;
import com.pillll.pillll.remoteDataSource.retrofitModel.CompositionData;
import com.pillll.pillll.remoteDataSource.retrofitModel.ConditionPrescriptionData;
import com.pillll.pillll.remoteDataSource.retrofitModel.GeneriqueData;
import com.pillll.pillll.remoteDataSource.retrofitModel.InfoImportanteData;
import com.pillll.pillll.remoteDataSource.retrofitModel.PresentationData;
import com.pillll.pillll.remoteDataSource.retrofitModel.SmrData;
import com.pillll.pillll.remoteDataSource.retrofitModel.SpecialiteData;
import com.pillll.pillll.remoteDataSource.retrofitModel.TitulaireSpecialiteData;
import com.pillll.pillll.remoteDataSource.retrofitModel.VoiesAdministrationData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Response;

/**
 * Class used to copy data receive from pillll api into each entities in order to persist it in local room database
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class CopyDataToEntity {

    private Presentation presentation;
    private Specialite specialite;
    private List<Asmr> asmrs;
    private List<Smr> smrs;
    private List<ConditionPrescription> conditionPrescriptions;
    private Generique generique;
    private List<InfoImportante> infoImportantes;
    private List<LienCt> lienCts;
    private List<TitulaireSpecialite> titulaireSpecialites;
    private List<VoiesAdministration> voiesAdministrations;
    private List<Composition> compositions;

    private Response<PresentationData> response;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public CopyDataToEntity(Response<PresentationData> response) {
        this.presentation = copyPresentationDataToEntity(response);
        this.specialite = copySpecialiteDataToEntity(response);
        this.asmrs = copyAsmrDataToEntity(response);
        this.smrs = copySmrDataToEntity(response);
        this.conditionPrescriptions = copyConditionPrescriptionDataToEntity(response);
        this.generique = copyGeneriqueDataToEntity(response);
        this.infoImportantes = copyInfoImportanteDataToEntity(response);
        this.lienCts = copyLienCtDataToEntity(response);
        this.titulaireSpecialites = copyTitulaireSpecialiteDataToEntity(response);
        this.voiesAdministrations = copyVoiesAdministrationDataToEntity(response);
        this.compositions = copyCompositionDataToEntity(response);
        this.response = response;
    }

    private List<Composition> copyCompositionDataToEntity(Response<PresentationData> response) {
        this.compositions = new ArrayList<>();
        List<CompositionData> compositionData = response.body().getSpecialite().getCompositions();
        if (compositionData!=null){
            for (CompositionData c:compositionData) {
                Composition composition = new Composition();
                composition.setCodeSubstance(c.getCodeSubstance());
                composition.setDenominationSubstance(c.getDenominationSubstance());
                composition.setDosageSubstance(c.getDosageSubstance());
                composition.setElementPharmaceutique(c.getElementPharmaceutique());
                composition.setId(c.getId());
                composition.setNatureComposant(c.getNatureComposant());
                composition.setNumeroLiaison(c.getNumeroLiaison());
                composition.setReferenceDosage(c.getReferenceDosage());
                composition.setSpecialiteIdCodeCis(response.body().getSpecialite().getIdCodeCis());
                this.compositions.add(composition);
            }
        }
        return compositions;
    }

    private List<VoiesAdministration> copyVoiesAdministrationDataToEntity(Response<PresentationData> response) {
        this.voiesAdministrations = new ArrayList<>();
        List<VoiesAdministrationData> voiesAdministrationData = response.body().getSpecialite().getVoiesAdministrations();
        if (voiesAdministrationData!=null){
            for (VoiesAdministrationData va: voiesAdministrationData) {
                VoiesAdministration voiesAdministration = new VoiesAdministration();
                voiesAdministration.setId(va.getId());
                voiesAdministration.setVoiesAdministration(va.getVoiesAdministration());
                voiesAdministration.setSpecialiteIdCodeCis(response.body().getSpecialite().getIdCodeCis());
                this.voiesAdministrations.add(voiesAdministration);
            }
        }
        return voiesAdministrations;
    }

    private List<TitulaireSpecialite> copyTitulaireSpecialiteDataToEntity(Response<PresentationData> response) {
        this.titulaireSpecialites = new ArrayList<>();
        List<TitulaireSpecialiteData> titulaireSpecialitesData = response.body().getSpecialite().getTitulaireSpecialites();
        if (titulaireSpecialitesData!=null){
            for (TitulaireSpecialiteData ts:titulaireSpecialitesData) {
                TitulaireSpecialite titulaireSpecialite = new TitulaireSpecialite();
                titulaireSpecialite.setId(ts.getId());
                titulaireSpecialite.setTitulaire(ts.getTitulaire());
                titulaireSpecialite.setSpecialiteIdCodeCis(response.body().getSpecialite().getIdCodeCis());
                this.titulaireSpecialites.add(titulaireSpecialite);
            }
        }
        return titulaireSpecialites;
    }

    private List<LienCt> copyLienCtDataToEntity(Response<PresentationData> response) {
        this.lienCts = new ArrayList<>();
        List<SmrData> smrData = response.body().getSpecialite().getSmrs();
        List<AsmrData> asmrData = response.body().getSpecialite().getAsmrs();
        if (smrData!=null){
            for (SmrData s:smrData) {
                LienCt lienCt = new LienCt();
                lienCt.setCodeDossierHas(s.getLienCt().getCodeDossierHas());
                lienCt.setLienAvisCt(s.getLienCt().getLienAvisCt());
                lienCts.add(lienCt);
            }
        }
        if (asmrData!=null){
            for (AsmrData a:asmrData) {
                LienCt lienCt = new LienCt();
                lienCt.setCodeDossierHas(a.getLienCt().getCodeDossierHas());
                lienCt.setLienAvisCt(a.getLienCt().getLienAvisCt());
                lienCts.add(lienCt);
            }
        }
        return lienCts;
    }

    private List<InfoImportante> copyInfoImportanteDataToEntity(Response<PresentationData> response) {
        this.infoImportantes = new ArrayList<>();
        List<InfoImportanteData> infoImportanteData = response.body().getSpecialite().getInfoImportantes();
        if (infoImportanteData!=null){
            for (InfoImportanteData ii:infoImportanteData) {
                InfoImportante infoImportante = new InfoImportante();

                String dateDebutString = ii.getDateDebut();
                try {
                    if (dateDebutString!=null){
                        Date dateDebut = simpleDateFormat.parse(dateDebutString);
                        infoImportante.setDateDebut(dateDebut);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String dateFinString = ii.getDateFin();
                try {
                    if (dateFinString!=null){
                        Date dateFin = simpleDateFormat.parse(dateFinString);
                        infoImportante.setDateFin(dateFin);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                infoImportante.setDescription(ii.getDescription());
                infoImportante.setId(ii.getId());
                infoImportante.setSpecialiteIdCodeCis(response.body().getSpecialite().getIdCodeCis());
                infoImportantes.add(infoImportante);
            }
        }
        return infoImportantes;
    }

    private Generique copyGeneriqueDataToEntity(Response<PresentationData> response) {
        GeneriqueData generiqueData = response.body().getSpecialite().getGenerique();
        this.generique = new Generique();
        if (generiqueData!=null){
            generique.setIdentifiantGroupe(generiqueData.getIdentifiantGroupe());
            generique.setLibelleGroupe(generiqueData.getLibelleGroupe());
            generique.setNumeroTri(generiqueData.getNumeroTri());
            generique.setType(generiqueData.getType());
            generique.setSpecialiteIdCodeCis(response.body().getSpecialite().getIdCodeCis());
        }
        return generique;
    }

    private List<ConditionPrescription> copyConditionPrescriptionDataToEntity(Response<PresentationData> response) {
        this.conditionPrescriptions = new ArrayList<>();
        List<ConditionPrescriptionData> conditionPrescriptionData = response.body().getSpecialite().getConditionPrescriptions();
        if (conditionPrescriptionData!=null){
            for (ConditionPrescriptionData cp:conditionPrescriptionData) {
                ConditionPrescription conditionPrescription = new ConditionPrescription();
                conditionPrescription.setId(cp.getId());
                conditionPrescription.setConditionPrescription(cp.getConditionPrescription());
                conditionPrescription.setSpecialiteIdCodeCis(response.body().getSpecialite().getIdCodeCis());
                this.conditionPrescriptions.add(conditionPrescription);
            }
        }
        return conditionPrescriptions;
    }

    private List<Smr> copySmrDataToEntity(Response<PresentationData> response) {
        this.smrs = new ArrayList<>();
        List<SmrData> smrData = response.body().getSpecialite().getSmrs();
        if (smrData!=null){
            for (SmrData s:smrData) {
                Smr smr = new Smr();
                smr.setId(s.getId());
                smr.setLibelle(s.getLibelle());
                smr.setLienCtCodeDossierHas(s.getLienCt().getCodeDossierHas());
                smr.setMotifEvaluation(s.getMotifEvaluation());
                smr.setValeur(s.getValeur());
                smr.setSpecialiteIdCodeCis(response.body().getSpecialite().getIdCodeCis());
                String dateAvisCtString = s.getDateAvisCt();
                try {
                    if (dateAvisCtString!=null){
                        Date dateAvisCt = simpleDateFormat.parse(dateAvisCtString);
                        smr.setDateAvisCt(dateAvisCt);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                this.smrs.add(smr);
            }
        }
        return smrs;
    }

    private List<Asmr> copyAsmrDataToEntity(Response<PresentationData> response) {
        this.asmrs = new ArrayList<>();
        List<AsmrData> asmrData = response.body().getSpecialite().getAsmrs();
        if (asmrData!=null){
            for (AsmrData a:asmrData) {
                Asmr asmr = new Asmr();
                asmr.setId(a.getId());
                asmr.setLibelle(a.getLibelle());
                asmr.setLienCtCodeDossierHas(a.getLienCt().getCodeDossierHas());
                asmr.setMotifEvaluation(a.getMotifEvaluation());
                asmr.setValeur(a.getValeur());
                asmr.setSpecialiteIdCodeCis(response.body().getSpecialite().getIdCodeCis());
                String dateAvisCtString = a.getDateAvisCt();
                try {
                    if (dateAvisCtString!=null){
                        Date dateAvisCt = simpleDateFormat.parse(dateAvisCtString);
                        asmr.setDateAvisCt(dateAvisCt);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                this.asmrs.add(asmr);
            }
        }
        return asmrs;
    }

    private Specialite copySpecialiteDataToEntity(Response<PresentationData> response) {
        SpecialiteData specialiteData = response.body().getSpecialite();
        this.specialite = new Specialite();
        if (specialiteData!=null){
            String dateAmmString = specialiteData.getDateAmm();
            try {
                if (dateAmmString!=null){
                    Date dateAmm = simpleDateFormat.parse(dateAmmString);
                    specialite.setDateAmm(dateAmm);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            specialite.setDenomination(specialiteData.getDenomination());
            specialite.setEtatCommercialisation(specialiteData.getEtatCommercialisation());
            specialite.setFormePharmaceutique(specialiteData.getFormePharmaceutique());
            specialite.setIdCodeCis(specialiteData.getIdCodeCis());
            specialite.setNumeroAutorisationEuro(specialiteData.getNumeroAutorisationEuro());
            specialite.setStatutAdministratifAmm(specialiteData.getStatutAdministratifAmm());
            specialite.setStatutBdm(specialiteData.getStatutBdm());
            specialite.setSurveillanceRenforcee(specialiteData.isSurveillanceRenforcee());
            specialite.setTypeProcedureAmm(specialiteData.getTypeProcedureAmm());
        }
        return specialite;
    }

    private Presentation copyPresentationDataToEntity(Response<PresentationData> response) {
        //copy presentation data
        this.presentation = new Presentation();
        PresentationData presentationData = response.body();
        if (presentationData!=null){
            presentation.setLibelle(presentationData.getLibelle());
            presentation.setAgrementCollectivites(presentationData.isAgrementCollectivites());
            presentation.setCodeCip7(presentationData.getCodeCip7());
            presentation.setCodeCip13(presentationData.getCodeCip13());
            //Gestion de la date !!
            String dateCommercialisationString = presentationData.getDateCommercialisation();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            try {
                if (dateCommercialisationString!=null){
                    Date dateCommercialisation = simpleDateFormat.parse(dateCommercialisationString);
                    presentation.setDateCommercialisation(dateCommercialisation);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            presentation.setEtatCommercialisation(presentationData.getEtatCommercialisation());
            presentation.setHonoraire(presentationData.getHonoraire());
            presentation.setPrecisionRemboursement(presentationData.getPrecisionRemboursement());
            presentation.setId(presentationData.getId());
            presentation.setPrixEuros(presentationData.getPrixEuros());
            presentation.setPrixEurosHorsHonoraire(presentationData.getPrixEurosHorsHonoraire());
            presentation.setSpecialiteIdCodeCis(presentationData.getSpecialite().getIdCodeCis());
            presentation.setStatutAdministratif(presentationData.getStatutAdministratif());
            presentation.setTauxRemboursement(presentationData.getTauxRemboursement());
        }
        return presentation;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public List<Asmr> getAsmrs() {
        return asmrs;
    }

    public List<Smr> getSmrs() {
        return smrs;
    }

    public List<ConditionPrescription> getConditionPrescriptions() {
        return conditionPrescriptions;
    }

    public Generique getGenerique() {
        return generique;
    }

    public List<InfoImportante> getInfoImportantes() {
        return infoImportantes;
    }

    public List<LienCt> getLienCts() {
        return lienCts;
    }

    public List<TitulaireSpecialite> getTitulaireSpecialites() {
        return titulaireSpecialites;
    }

    public List<VoiesAdministration> getVoiesAdministrations() {
        return voiesAdministrations;
    }

    public List<Composition> getCompositions() {
        return compositions;
    }
}
