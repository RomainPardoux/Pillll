package com.pill.pill;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.pill.pill.database.entity.Asmr;
import com.pill.pill.database.entity.Composition;
import com.pill.pill.database.entity.ConditionPrescription;
import com.pill.pill.database.entity.Generique;
import com.pill.pill.database.entity.InfoImportante;
import com.pill.pill.database.entity.Presentation;
import com.pill.pill.database.entity.Smr;
import com.pill.pill.database.entity.Specialite;
import com.pill.pill.database.entity.TitulaireSpecialite;
import com.pill.pill.database.entity.VoiesAdministration;
import com.pill.pill.repositories.AsmrDataRepository;
import com.pill.pill.repositories.ComditionPrescriptionDataRepository;
import com.pill.pill.repositories.CompositionDataRepository;
import com.pill.pill.repositories.EvaluationDataRepository;
import com.pill.pill.repositories.GeneriqueDataRepository;
import com.pill.pill.repositories.InfoImportanteDataRepository;
import com.pill.pill.repositories.LienCtDataRepository;
import com.pill.pill.repositories.PresentationDataRepository;
import com.pill.pill.repositories.SmrDataRepository;
import com.pill.pill.repositories.SpecialiteDataRepository;
import com.pill.pill.repositories.TitulaireSpecialiteDataRepository;
import com.pill.pill.repositories.VoiesAdministrationDataRepository;

import java.util.List;

/**
 * Created by Pardoux Romain on 26/02/2019
 * ViewModel: Fournit à l'activity les données qui doivent etre affichées
 */

public class SpecialiteViewModel extends ViewModel {

    // REPOSITORIES
    private final SpecialiteDataRepository specialiteDataSource;
    private final CompositionDataRepository compositionDataSource;
    private final PresentationDataRepository presentationDataSource;
    private final AsmrDataRepository asmrDataSource;
    private final ComditionPrescriptionDataRepository conditionPrescriptionDataSource;
    private final EvaluationDataRepository evaluationDataSource;
    private final GeneriqueDataRepository generiqueDataSource;
    private final InfoImportanteDataRepository infoImportanteDataSource;
    private final LienCtDataRepository lienCtDataSource;
    private final SmrDataRepository smrDataSource;
    private final TitulaireSpecialiteDataRepository titulaireSpecialiteDataSource;
    private final VoiesAdministrationDataRepository voiesAdministrationDataSource;

    // DATA
    @Nullable
    private LiveData<Specialite> currentSpecialite;

    public SpecialiteViewModel(SpecialiteDataRepository specialiteDataSource, CompositionDataRepository compositionDataSource, PresentationDataRepository presentationDataSource, AsmrDataRepository asmrDataSource, ComditionPrescriptionDataRepository conditionPrescriptionDataSource, EvaluationDataRepository evaluationDataSource, GeneriqueDataRepository generiqueDataSource, InfoImportanteDataRepository infoImportanteDataSource, LienCtDataRepository lienCtDataSource, SmrDataRepository smrDataSource, TitulaireSpecialiteDataRepository titulaireSpecialiteDataSource, VoiesAdministrationDataRepository voiesAdministrationDataSource) {
        this.specialiteDataSource = specialiteDataSource;
        this.compositionDataSource = compositionDataSource;
        this.presentationDataSource = presentationDataSource;
        this.asmrDataSource = asmrDataSource;
        this.conditionPrescriptionDataSource = conditionPrescriptionDataSource;
        this.evaluationDataSource = evaluationDataSource;
        this.generiqueDataSource = generiqueDataSource;
        this.infoImportanteDataSource = infoImportanteDataSource;
        this.lienCtDataSource = lienCtDataSource;
        this.smrDataSource = smrDataSource;
        this.titulaireSpecialiteDataSource = titulaireSpecialiteDataSource;
        this.voiesAdministrationDataSource = voiesAdministrationDataSource;
    }

    public void initWithCodeCis(long idCodeCis){
        if(this.currentSpecialite != null){
            return;
        }
        currentSpecialite = specialiteDataSource.selectSpecialiteParId(idCodeCis);
    }

    public void initWithCodeCip7(String codeCip7){
        if(this.currentSpecialite != null){
            return;
        }
        currentSpecialite = specialiteDataSource.selectSpecialiteParCodeCip7(codeCip7);
    }


    public void initWithCodeCip13(String codeCip13){
        if(this.currentSpecialite != null){
            return;
        }
        currentSpecialite = specialiteDataSource.selectSpecialiteParCodeCip7(codeCip13);
    }

    // ------------------------------
    // SPECIALITE
    // ------------------------------

    public LiveData<Specialite> getSpecialite(){ return this.currentSpecialite; }


    // ------------------------------
    // COMPOSITION
    // ------------------------------

    public LiveData<List<Composition>> getCompositions() {
        return compositionDataSource.selectCompositionParCodeCis(currentSpecialite.getValue().getIdCodeCis());
    }

    // ------------------------------
    // PRESENTATION
    // ------------------------------

    public LiveData<List<Presentation>> getPresentations() {
        return presentationDataSource.selectPresentationParCodeCis(currentSpecialite.getValue().getIdCodeCis());
    }

    // ------------------------------
    // GENERIQUE
    // ------------------------------

    public LiveData<Generique> getGenerique() {
        return generiqueDataSource.selectGeneriqueParCodeCis(currentSpecialite.getValue().getIdCodeCis());
    }

    // ------------------------------
    // ASMR
    // ------------------------------

    public LiveData<List<Asmr>> getAsmrs() {
        return asmrDataSource.selectAsmrParCodeCis(currentSpecialite.getValue().getIdCodeCis());
    }

    // ------------------------------
    // SMR
    // ------------------------------

    public LiveData<List<Smr>> getSmrs() {
        return smrDataSource.selectSmrParCodeCis(currentSpecialite.getValue().getIdCodeCis());
    }

    // ------------------------------
    // CONDITION DE PRESCRIPTION
    // ------------------------------

    public LiveData<List<ConditionPrescription>> getConditionsPrescriptions() {
        return conditionPrescriptionDataSource.selectConditionPrescriptionParCodeCis(currentSpecialite.getValue().getIdCodeCis());
    }

    // ------------------------------
    // INFOS IMPORTANTES
    // ------------------------------

    public LiveData<List<InfoImportante>> getInfosImportantes() {
        return infoImportanteDataSource.selectInfoImportanteParCodeCis(currentSpecialite.getValue().getIdCodeCis());
    }

    // ------------------------------
    // TITULAIRE SPECIALITE
    // ------------------------------

    public LiveData<List<TitulaireSpecialite>> getTitulairesSpecialite() {
        return titulaireSpecialiteDataSource.selectTitulaireSpecialiteParCodeCis(currentSpecialite.getValue().getIdCodeCis());
    }

    // ------------------------------
    // VOIES D'ADMINISTRATION
    // ------------------------------

    public LiveData<List<VoiesAdministration>> getVoiesAdministrations() {
        return voiesAdministrationDataSource.selectVoiesAdministrationParCodeCis(currentSpecialite.getValue().getIdCodeCis());
    }

}
