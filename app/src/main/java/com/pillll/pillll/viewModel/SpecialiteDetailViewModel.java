package com.pillll.pillll.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pillll.pillll.database.entity.Asmr;
import com.pillll.pillll.database.entity.Composition;
import com.pillll.pillll.database.entity.ConditionPrescription;
import com.pillll.pillll.database.entity.Generique;
import com.pillll.pillll.database.entity.InfoImportante;
import com.pillll.pillll.database.entity.LienCt;
import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.database.entity.Smr;
import com.pillll.pillll.database.entity.Specialite;
import com.pillll.pillll.database.entity.TitulaireSpecialite;
import com.pillll.pillll.database.entity.VoiesAdministration;
import com.pillll.pillll.repositories.AsmrDataRepository;
import com.pillll.pillll.repositories.CompositionDataRepository;
import com.pillll.pillll.repositories.ConditionPrescriptionDataRepository;
import com.pillll.pillll.repositories.GeneriqueDataRepository;
import com.pillll.pillll.repositories.InfoImportanteDataRepository;
import com.pillll.pillll.repositories.LienCtDataRepository;
import com.pillll.pillll.repositories.NetworkDataRepository;
import com.pillll.pillll.repositories.PresentationDataRepository;
import com.pillll.pillll.repositories.SmrDataRepository;
import com.pillll.pillll.repositories.SpecialiteDataRepository;
import com.pillll.pillll.repositories.TitulaireSpecialiteDataRepository;
import com.pillll.pillll.repositories.VoiesAdministrationDataRepository;

import java.util.List;

/**
 * Created by Pardoux Romain on 26/02/2019
 * ViewModel: Fourni à l'activity les données qui doivent etre affichées
 */

public class SpecialiteDetailViewModel extends AndroidViewModel {

    private PresentationDataRepository presentationDataSource;
    private SpecialiteDataRepository specialiteDataSource;
    private CompositionDataRepository compositionDataSource;
    private AsmrDataRepository asmrDataSource;
    private SmrDataRepository smrDataSource;
    private ConditionPrescriptionDataRepository conditionPrescriptionDataSource;
    private GeneriqueDataRepository generiqueDataSource;
    private InfoImportanteDataRepository infoImportanteDataSource;
    private LienCtDataRepository lienCtDataSource;
    private TitulaireSpecialiteDataRepository titulaireSpecialiteDataSource;
    private VoiesAdministrationDataRepository voiesAdministrationDataSource;
    private NetworkDataRepository networkDataSource;

    @Nullable
    private LiveData<Generique> currentGenerique;
    @Nullable
    private LiveData<List<Asmr>> currentAsmrs;
    @Nullable
    private LiveData<List<Smr>> currentSmrs;
    @Nullable
    private LiveData<List<ConditionPrescription>> currentConditionsPrescriptions;
    @Nullable
    private LiveData<List<InfoImportante>> currentInfosImportantes;
    @Nullable
    private LiveData<LienCt> currentLienCt;
    @Nullable
    private LiveData<List<TitulaireSpecialite>> currentTitulaireSpecialites;
    @Nullable
    private LiveData<List<VoiesAdministration>> currentVoiesAdministrations;
    @Nullable
    private LiveData<List<Composition>> currentCompositions;
    @Nullable
    private LiveData<Presentation> currentPresentation;
    @Nullable
    private LiveData<Specialite> currentSpecialite;

    // CONSTRUCTEURS
    public SpecialiteDetailViewModel(Application application) {
        super(application);
        this.presentationDataSource = new PresentationDataRepository(application);
        this.specialiteDataSource = new SpecialiteDataRepository(application);
        this.compositionDataSource = new CompositionDataRepository(application);
        this.smrDataSource = new SmrDataRepository(application);
        this.asmrDataSource = new AsmrDataRepository(application);
        this.titulaireSpecialiteDataSource = new TitulaireSpecialiteDataRepository(application);
        this.voiesAdministrationDataSource = new VoiesAdministrationDataRepository(application);
        this.lienCtDataSource = new LienCtDataRepository(application);
        this.conditionPrescriptionDataSource = new ConditionPrescriptionDataRepository(application);
        this.infoImportanteDataSource = new InfoImportanteDataRepository(application);
        this.generiqueDataSource = new GeneriqueDataRepository(application);
    }

    // REFRESH PUBLIC
    public void refreshData(String codeCip){
        this.networkDataSource.refreshData(codeCip);
    }

    // GET DATA FROM SQLITE DB PUBLIC
    public LiveData<Presentation> getPresentation(String codeCip) {

        switch (codeCip.length()){
            case 7:
                currentPresentation = this.presentationDataSource.getPresentationByCodeCip7(codeCip);
                break;
            case 13:
                currentPresentation = this.presentationDataSource.getPresentationByCodeCip13(codeCip);
                break;
            default:
                currentPresentation = null;
        }
        return currentPresentation;
    }

    public LiveData<Specialite> getSpecialite(long idCodeCis) {
        currentSpecialite = this.specialiteDataSource.getSpecialiteByCodeCis(idCodeCis);
        return currentSpecialite;
    }

    public LiveData<List<Composition>> getCompositions(long idCodeCis) {
        currentCompositions = this.compositionDataSource.getCompositionsByCodeCis(idCodeCis);
        return currentCompositions;
    }

    public LiveData<List<Smr>> getSmrs(long idCodeCis) {
        currentSmrs = this.smrDataSource.getSmrsByCodeCis(idCodeCis);
        return currentSmrs;
    }

    public LiveData<List<Asmr>> getAsmrs(long idCodeCis) {
        currentAsmrs = this.asmrDataSource.getAsmrsByCodeCis(idCodeCis);
        return currentAsmrs;
    }

    public LiveData<List<ConditionPrescription>> getConditionsPrescriptions(long idCodeCis) {
        currentConditionsPrescriptions = this.conditionPrescriptionDataSource.getConditionPrescriptionsByCodeCis(idCodeCis);
        return currentConditionsPrescriptions;
    }

    public LiveData<Generique> getGeneriques(long idCodeCis) {
        currentGenerique= this.generiqueDataSource.getGeneriqueByCodeCis(idCodeCis);
        return currentGenerique;
    }

    public LiveData<List<InfoImportante>> getInfosImportantes(long idCodeCis) {
        currentInfosImportantes = this.infoImportanteDataSource.getInfoImportantesByCodeCis(idCodeCis);
        return currentInfosImportantes;
    }

    public LiveData<LienCt> getLiensCts(String codeDossierHas) {
        currentLienCt = this.lienCtDataSource.getLienCtByCodeHas(codeDossierHas);
        return currentLienCt;
    }

    public LiveData<List<TitulaireSpecialite>> getTitulairesSpecialites(long idCodeCis) {
        currentTitulaireSpecialites = this.titulaireSpecialiteDataSource.getTitulaireSpecialitesByCodeCis(idCodeCis);
        return currentTitulaireSpecialites;
    }

    public LiveData<List<VoiesAdministration>> getVoiesAdministrations(long idCodeCis) {
        currentVoiesAdministrations = this.voiesAdministrationDataSource.getVoiesAdministrationsByCodeCis(idCodeCis);
        return currentVoiesAdministrations;
    }

    // GET CURRENT DATA PUBLIC

    @Nullable
    public LiveData<List<Composition>> getCurrentCompositions() {
        return currentCompositions;
    }

    @Nullable
    public LiveData<Presentation> getCurrentPresentation() {
        return currentPresentation;
    }

    @Nullable
    public LiveData<Specialite> getCurrentSpecialite() {
        return currentSpecialite;
    }

    @Nullable
    public LiveData<Generique> getCurrentGenerique() {
        return currentGenerique;
    }

    @Nullable
    public LiveData<List<Asmr>> getCurrentAsmrs() {
        return currentAsmrs;
    }

    @Nullable
    public LiveData<List<Smr>> getCurrentSmrs() {
        return currentSmrs;
    }

    @Nullable
    public LiveData<List<ConditionPrescription>> getCurrentConditionsPrescriptions() {
        return currentConditionsPrescriptions;
    }

    @Nullable
    public LiveData<List<InfoImportante>> getCurrentInfosImportantes() {
        return currentInfosImportantes;
    }

    @Nullable
    public LiveData<LienCt> getCurrentLienCt() {
        return currentLienCt;
    }

    @Nullable
    public LiveData<List<TitulaireSpecialite>> getCurrentTitulaireSpecialites() {
        return currentTitulaireSpecialites;
    }

    @Nullable
    public LiveData<List<VoiesAdministration>> getCurrentVoiesAdministrations() {
        return currentVoiesAdministrations;
    }
}
