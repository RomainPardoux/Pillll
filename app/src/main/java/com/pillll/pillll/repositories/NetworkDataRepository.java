package com.pillll.pillll.repositories;

import android.app.Application;
import android.util.Log;

import com.pillll.pillll.remoteDataSource.CopyDataToEntity;
import com.pillll.pillll.remoteDataSource.NetworkService;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.remoteDataSource.PillllWebService;
import com.pillll.pillll.model.entities.Asmr;
import com.pillll.pillll.model.entities.Composition;
import com.pillll.pillll.model.entities.ConditionPrescription;
import com.pillll.pillll.model.entities.InfoImportante;
import com.pillll.pillll.model.entities.LienCt;
import com.pillll.pillll.model.entities.Presentation;
import com.pillll.pillll.model.entities.Smr;
import com.pillll.pillll.model.entities.TitulaireSpecialite;
import com.pillll.pillll.model.entities.VoiesAdministration;
import com.pillll.pillll.remoteDataSource.retrofitModel.PresentationData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class that abstract access to Network data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class NetworkDataRepository {

    private PresentationDataRepository presentationDataRepository;
    private SpecialiteDataRepository specialiteDataRepository;
    private AsmrDataRepository asmrDataRepository;
    private SmrDataRepository smrDataRepository;
    private TitulaireSpecialiteDataRepository titulaireSpecialiteDataRepository;
    private VoiesAdministrationDataRepository voiesAdministrationDataRepository;
    private CompositionDataRepository compositionDataRepository;
    private GeneriqueDataRepository generiqueDataRepository;
    private LienCtDataRepository lienCtDataRepository;
    private InfoImportanteDataRepository infoImportanteDataRepository;
    private ConditionPrescriptionDataRepository conditionPrescriptionDataRepository;

    public NetworkDataRepository(Application application) {
        presentationDataRepository = new PresentationDataRepository(application);
        specialiteDataRepository = new SpecialiteDataRepository(application);
        asmrDataRepository = new AsmrDataRepository(application);
        smrDataRepository = new SmrDataRepository(application);
        titulaireSpecialiteDataRepository = new TitulaireSpecialiteDataRepository(application);
        voiesAdministrationDataRepository = new VoiesAdministrationDataRepository(application);
        compositionDataRepository = new CompositionDataRepository(application);
        generiqueDataRepository = new GeneriqueDataRepository(application);
        lienCtDataRepository = new LienCtDataRepository(application);
        infoImportanteDataRepository = new InfoImportanteDataRepository(application);
        conditionPrescriptionDataRepository = new ConditionPrescriptionDataRepository(application);
    }

    // ACTION SUR WEB SERVICE

    /**
     * Refresh data from pillll WebService by code cip.
     *
     * @param codeCip
     */
    public void refreshData(String codeCip) {

        PillllWebService pillllApi = NetworkService.getInstance().getPillllApi();
        Call<PresentationData> call;

        switch (codeCip.length()){
            case 7:
                call = pillllApi.getPresentationWithCodeCip7(codeCip);
                break;
            case 13:
                call = pillllApi.getPresentationWithCodeCip13(codeCip);
                break;
            default:
                return;
        }

        call.enqueue(new Callback<PresentationData>() {
            @Override
            public void onResponse(Call<PresentationData> call, Response<PresentationData> response) {

                if (response.isSuccessful()){
                    CopyDataToEntity copyDataToEntity = new CopyDataToEntity(response);
                    persistDataToRoomDb(copyDataToEntity);
                }else {
                    //error case
                    switch (response.code()){
                        case 404:
                            Log.d("error", "not found");
                            break;
                        case 500:
                            Log.d("error", "not logged in or server broken");
                            break;
                        default:
                            Log.d("error","unknown error");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<PresentationData> call, Throwable t) {
                // action à effectuer en cas d'echec
                Log.d("error","failure");
            }

            private void persistDataToRoomDb(CopyDataToEntity copyDataToEntity){
                presentationDataRepository.persistPresentation(copyDataToEntity.getPresentation());
                specialiteDataRepository.persistSpecialite(copyDataToEntity.getSpecialite());
                generiqueDataRepository.persistGenerique(copyDataToEntity.getGenerique());
                for (Asmr asmr:copyDataToEntity.getAsmrs()) {
                    asmrDataRepository.persistAsmr(asmr);
                }
                for (Smr smr:copyDataToEntity.getSmrs()) {
                    smrDataRepository.persistSmr(smr);
                }
                for (Composition composition:copyDataToEntity.getCompositions()) {
                    compositionDataRepository.persistComposition(composition);
                }
                for (TitulaireSpecialite titulaireSpecialite:copyDataToEntity.getTitulaireSpecialites()) {
                    titulaireSpecialiteDataRepository.persistTitulaireSpecialite(titulaireSpecialite);
                }
                for (VoiesAdministration voiesAdministration:copyDataToEntity.getVoiesAdministrations()) {
                    voiesAdministrationDataRepository.persistVoiesAdministration(voiesAdministration);
                }
                for (ConditionPrescription conditionPrescription:copyDataToEntity.getConditionPrescriptions()) {
                    conditionPrescriptionDataRepository.persistConditionPrescription(conditionPrescription);
                }
                for (InfoImportante infoImportante:copyDataToEntity.getInfoImportantes()) {
                    infoImportanteDataRepository.persistInfoImportante(infoImportante);
                }
                for (LienCt lienCt:copyDataToEntity.getLienCts()) {
                    lienCtDataRepository.persistLienCt(lienCt);
                }

            }
        });
    }
}
