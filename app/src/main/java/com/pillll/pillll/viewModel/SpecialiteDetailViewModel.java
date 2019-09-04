package com.pillll.pillll.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.pillll.pillll.database.entity.Composition;
import com.pillll.pillll.database.entity.Generique;
import com.pillll.pillll.database.entity.InfoImportante;
import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.database.entity.Smr;
import com.pillll.pillll.database.entity.Specialite;
import com.pillll.pillll.database.entity.TitulaireSpecialite;
import com.pillll.pillll.database.entity.VoiesAdministration;
import com.pillll.pillll.repositories.CompositionDataRepository;
import com.pillll.pillll.repositories.GeneriqueDataRepository;
import com.pillll.pillll.repositories.InfoImportanteDataRepository;
import com.pillll.pillll.repositories.PresentationDataRepository;
import com.pillll.pillll.repositories.SmrDataRepository;
import com.pillll.pillll.repositories.SpecialiteDataRepository;
import com.pillll.pillll.repositories.TitulaireSpecialiteDataRepository;
import com.pillll.pillll.repositories.VoiesAdministrationDataRepository;
import java.util.List;

/**
 * Created by Pardoux Romain on 26/02/2019
 * ViewModel: Fournit à l'activity les données qui doivent etre affichées
 */

public class SpecialiteDetailViewModel extends AndroidViewModel {

    // DECLARATION DES VARIABLES
    // REPOSITORIES
    private SpecialiteDataRepository specialiteDataSource;
    private CompositionDataRepository compositionDataSource;
    private PresentationDataRepository presentationDataSource;
    private GeneriqueDataRepository generiqueDataSource;
    private InfoImportanteDataRepository infoImportanteDataSource;
    private SmrDataRepository smrDataSource;
    private TitulaireSpecialiteDataRepository titulaireSpecialiteDataSource;
    private VoiesAdministrationDataRepository voiesAdministrationDataSource;

    // DATA
    @Nullable
    private LiveData<Specialite> currentSpecialite;
    @Nullable
    private LiveData<List<Composition>> currentCompositions;
    @Nullable
    private LiveData<Presentation> currentPresentation;
    @Nullable
    private LiveData<Generique> generique;
    @Nullable
    private LiveData<List<Smr>> currentSmrs;
    @Nullable
    private LiveData<List<InfoImportante>> currentInfosImportantes;
    @Nullable
    private LiveData<List<TitulaireSpecialite>> currentTitulairesSpecialite;
    @Nullable
    private LiveData<List<VoiesAdministration>> currentVoiesAdministrations;

    // CONSTRUCTEURS
    public SpecialiteDetailViewModel(Application application) {
        super(application);
        initRepositories(application);
    }

    private void initRepositories(Application application){
        this.specialiteDataSource = new SpecialiteDataRepository(application);
        this.compositionDataSource = new CompositionDataRepository(application);
        this.presentationDataSource = new PresentationDataRepository(application);
        this.generiqueDataSource = new GeneriqueDataRepository(application);
        this.infoImportanteDataSource = new InfoImportanteDataRepository(application);
        this.smrDataSource = new SmrDataRepository(application);
        this.titulaireSpecialiteDataSource = new TitulaireSpecialiteDataRepository(application);
        this.voiesAdministrationDataSource = new VoiesAdministrationDataRepository(application);
    }

    // METHODE D'INITIALISATION DU VIEW MODEL
    public void initViewModelFromSqlite(String codeCip){
        if(this.currentPresentation != null){
            return;
        }
        initWithCodeCipFromSqlite(codeCip);
    }

    public void initViewModelFromWebService(String codeCip){
        if(this.currentPresentation != null){
            return;
        }
    }

    public void initViewModelFromSqlitePriority(String codeCip){
        if(this.currentPresentation != null){
            return;
        }
    }

    private void initWithCodeCipFromSqlite(String codeCip){
        long idCodeCis;
        if(codeCip.length() == 7){
            this.currentPresentation = presentationDataSource.getPresentationFromSqliteByCodeCip7(codeCip);
        }else if (codeCip.length() == 13){
            this.currentPresentation = presentationDataSource.getPresentationFromSqliteByCodeCip13(codeCip);
        }else {
            return;
        }
        idCodeCis = this.currentPresentation.getValue().getSpecialiteIdCodeCis();
        this.currentSpecialite = specialiteDataSource.getSpecialiteFromSqliteByCodeCis(idCodeCis);
        this.currentCompositions = compositionDataSource.getCompositionsFromSqliteByCodeCis(idCodeCis);
        this.generique = generiqueDataSource.getGeneriqueFromSqliteByCodeCis(idCodeCis);
        this.currentSmrs = smrDataSource.getSmrsFromSqliteByCodeCis(idCodeCis);
        this.currentInfosImportantes = infoImportanteDataSource.getInfoImportantesFromSqliteByCodeCis(idCodeCis);
        this.currentTitulairesSpecialite = titulaireSpecialiteDataSource.getTitulaireSpecialitesFromSqliteByCodeCis(idCodeCis);
        this.currentVoiesAdministrations = voiesAdministrationDataSource.getVoiesAdministrationsFromSqliteByCodeCis(idCodeCis);
    }

    @Nullable
    public LiveData<Specialite> getCurrentSpecialite() {
        return currentSpecialite;
    }

    @Nullable
    public LiveData<List<Composition>> getCurrentCompositions() {
        return currentCompositions;
    }

    @Nullable
    public LiveData<Presentation> getCurrentPresentations() {
        return currentPresentation;
    }

    @Nullable
    public LiveData<List<Smr>> getCurrentSmrs() {
        return currentSmrs;
    }

    @Nullable
    public LiveData<List<InfoImportante>> getCurrentInfosImportantes() {
        return currentInfosImportantes;
    }

    @Nullable
    public LiveData<List<TitulaireSpecialite>> getCurrentTitulairesSpecialite() {
        return currentTitulairesSpecialite;
    }

    @Nullable
    public LiveData<List<VoiesAdministration>> getCurrentVoiesAdministrations() {
        return currentVoiesAdministrations;
    }

    /* A voir: Recuperation des generiques associés à la specialité
    -> Utiliser un autre view model ?
    -> Ajouter un champ favori à Specialite pour afficher une partie seulement des Specilites percistées dans ROOM ?
     */
}
