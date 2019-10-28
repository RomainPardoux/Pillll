package com.pillll.pillll.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pillll.pillll.database.entity.Composition;
import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.database.entity.Specialite;
import com.pillll.pillll.repositories.CompositionDataRepository;
import com.pillll.pillll.repositories.PresentationDataRepository;
import com.pillll.pillll.repositories.SpecialiteDataRepository;

import java.util.List;

/**
 * Created by Pardoux Romain on 26/02/2019
 * ViewModel: Fourni à l'activity les données qui doivent etre affichées
 */

public class SpecialiteDetailViewModel extends AndroidViewModel {

    private PresentationDataRepository presentationDataSource;
    private SpecialiteDataRepository specialiteDataSource;
    private CompositionDataRepository compositionDataSource;


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
    }

    // REFRESH PUBLIC
    public void refreshPresentation(String codeCip){
        this.presentationDataSource.refreshPresentation(codeCip);
    }

    public void refreshSpecialite(long idCodeCis){
        this.specialiteDataSource.refreshSpecialite(idCodeCis);
    }

    public void refreshCompositions(long idCodeCis){
        this.compositionDataSource.refreshCompositions(idCodeCis);
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
}
