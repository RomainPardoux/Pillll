package com.pillll.pillll.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.database.entity.Specialite;
import com.pillll.pillll.repositories.PresentationDataRepository;
import com.pillll.pillll.repositories.SpecialiteDataRepository;

/**
 * Created by Pardoux Romain on 26/02/2019
 * ViewModel: Fourni à l'activity les données qui doivent etre affichées
 */

public class SpecialiteDetailViewModel extends AndroidViewModel {

    private PresentationDataRepository presentationDataSource;
    private SpecialiteDataRepository specialiteDataSource;

    @Nullable
    private LiveData<Presentation> currentPresentation;
    @Nullable
    private LiveData<Specialite> currentSpecialite;

    // CONSTRUCTEURS
    public SpecialiteDetailViewModel(Application application) {
        super(application);
        this.presentationDataSource = new PresentationDataRepository(application);
        this.specialiteDataSource = new SpecialiteDataRepository(application);
    }

    public void refreshPresentation(String codeCip){
        this.presentationDataSource.refreshPresentation(codeCip);
    }

    public LiveData<Presentation> getCurrentPresentations(String codeCip) {
        if (codeCip.length()==7){
            currentPresentation = this.presentationDataSource.getPresentationByCodeCip7(codeCip);
        }else {
            currentPresentation = this.presentationDataSource.getPresentationByCodeCip13(codeCip);
        }
        return currentPresentation;
    }


    @Nullable
    public LiveData<Presentation> getCurrentPresentations() {
        return currentPresentation;
    }
}
