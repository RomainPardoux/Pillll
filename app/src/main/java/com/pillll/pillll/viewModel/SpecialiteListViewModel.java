package com.pillll.pillll.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pillll.pillll.model.entities.Composition;
import com.pillll.pillll.model.entities.Generique;
import com.pillll.pillll.model.entities.InfoImportante;
import com.pillll.pillll.model.entities.Presentation;
import com.pillll.pillll.model.entities.Smr;
import com.pillll.pillll.model.entities.Specialite;
import com.pillll.pillll.model.entities.TitulaireSpecialite;
import com.pillll.pillll.model.entities.VoiesAdministration;
import com.pillll.pillll.repositories.CompositionDataRepository;
import com.pillll.pillll.repositories.GeneriqueDataRepository;
import com.pillll.pillll.repositories.InfoImportanteDataRepository;
import com.pillll.pillll.repositories.PresentationDataRepository;
import com.pillll.pillll.repositories.SmrDataRepository;
import com.pillll.pillll.repositories.SpecialiteDataRepository;
import com.pillll.pillll.repositories.TitulaireSpecialiteDataRepository;
import com.pillll.pillll.repositories.VoiesAdministrationDataRepository;

import java.util.List;

public class SpecialiteListViewModel extends AndroidViewModel {

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
    private LiveData<List<Presentation>> currentPresentations;
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


    public SpecialiteListViewModel(@NonNull Application application) {
        super(application);
    }
}
