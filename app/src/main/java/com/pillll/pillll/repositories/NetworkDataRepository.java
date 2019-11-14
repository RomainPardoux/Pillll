package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.pillll.pillll.database.CopyDataToEntity;
import com.pillll.pillll.database.NetworkService;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.entity.Asmr;
import com.pillll.pillll.database.entity.Composition;
import com.pillll.pillll.database.entity.ConditionPrescription;
import com.pillll.pillll.database.entity.Generique;
import com.pillll.pillll.database.entity.InfoImportante;
import com.pillll.pillll.database.entity.LienCt;
import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.database.entity.Smr;
import com.pillll.pillll.database.entity.TitulaireSpecialite;
import com.pillll.pillll.database.entity.VoiesAdministration;
import com.pillll.pillll.database.pojo.PresentationData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        PillllDatabase db = PillllDatabase.getInstance(application);
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

        // Get instance of pillllApi
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
                call = null;
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

            public void persistDataToRoomDb(CopyDataToEntity copyDataToEntity){
                //Persist Data
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

    private Presentation copyPresentationDataToEntity(Response<PresentationData> response) {
        //copy presentation data
        copyPresentationDataToEntity(response);
        Presentation presentation = new Presentation();
        PresentationData presentationData = response.body();
        presentation.setLibelle(presentationData.getLibelle());
        presentation.setAgrementCollectivites(presentationData.isAgrementCollectivites());
        presentation.setCodeCip7(presentationData.getCodeCip7());
        presentation.setCodeCip13(presentationData.getCodeCip13());
        //Gestion de la date !!
        String dateCommercialisationString = presentationData.getDateCommercialisation();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date dateCommercialisation = simpleDateFormat.parse(dateCommercialisationString);
            presentation.setDateCommercialisation(dateCommercialisation);
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
        return presentation;
    }



}
