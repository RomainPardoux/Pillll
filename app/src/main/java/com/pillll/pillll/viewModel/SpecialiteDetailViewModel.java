package com.pillll.pillll.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;
import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.repositories.PresentationDataRepository;

/**
 * Created by Pardoux Romain on 26/02/2019
 * ViewModel: Fourni à l'activity les données qui doivent etre affichées
 */

public class SpecialiteDetailViewModel extends AndroidViewModel {

    private PresentationDataRepository presentationDataSource;
    @Nullable
    private LiveData<Presentation> currentPresentation;

    // CONSTRUCTEURS
    public SpecialiteDetailViewModel(Application application) {
        super(application);
        this.presentationDataSource = new PresentationDataRepository(application);
    }

    // METHODE D'INITIALISATION DU VIEW MODEL
    public void initViewModel(String codeCip){
        if(this.currentPresentation != null){
            return;
        }
        presentationDataSource.refreshPresentation(codeCip);
        if(codeCip.length() == 7){
            this.currentPresentation = presentationDataSource.getPresentationByCodeCip7(codeCip);
        }else if (codeCip.length() == 13){
            this.currentPresentation = presentationDataSource.getPresentationByCodeCip13(codeCip);
        }else {
            return;
        }
    }

    @Nullable
    public LiveData<Presentation> getCurrentPresentations() {
        return currentPresentation;
    }
}
