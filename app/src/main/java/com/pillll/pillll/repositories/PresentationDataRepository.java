package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.PresentationDao;
import com.pillll.pillll.database.entity.Presentation;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Repository class that abstract access to Presentation data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class PresentationDataRepository {

    private final PresentationDao presentationDao;

    public PresentationDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.presentationDao = db.presentationDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Fetch Presentation data from pillll WebService by code cip
     *
     * @param codeCip
     */
    public void refreshPresentation(String codeCip) {
        // Build Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PillllWebService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PillllWebService presentationWebService = retrofit.create(PillllWebService.class);

        if (codeCip.length() == 7) {
            Call<Presentation> call = presentationWebService.getPresentationWithCodeCip7(codeCip);
            call.enqueue(new Callback<Presentation>() {
                @Override
                public void onResponse(Call<Presentation> call, Response<Presentation> response) {
                    insertPresentation(response.body());
/*
                    if (response.isSuccessful()){
                        presentationFromSqlite = getPresentationFromSqliteByCodeCip7(codeCip);
                        if (presentationFromSqlite==null){
                            insertPresentation(response.body());
                        }else if (presentationFromSqlite.getValue().equals(response.body()))
                            updatePresentation(response.body());
                    }else {

                    }
*/
                }

                @Override
                public void onFailure(Call<Presentation> call, Throwable t) {
                    // action à effectuer en cas d'echec
                }
            });
        } else if (codeCip.length() == 13) {
            Call<Presentation> call = presentationWebService.getPresentationWithCodeCip13(codeCip);
            call.enqueue(new Callback<Presentation>() {
                @Override
                public void onResponse(Call<Presentation> call, Response<Presentation> response) {
                    if (response.isSuccessful()){
                        LiveData<Presentation> presentationFromSqlite = getPresentationByCodeCip13(codeCip);
                        if (presentationFromSqlite==null){
                            insertPresentation(response.body());
                        }
                        /*else if (presentationFromSqlite.getValue().equals(response.body()))
                            updatePresentation(response.body());
                    }else {
*/
                    }
                }

                @Override
                public void onFailure(Call<Presentation> call, Throwable t) {
                    // action à effectuer en cas d'echec
                }
            });
        } else {
            // action à effectuer si code cip <> de 7 ou 13
        }
    }

    // ACTION SUR SQLITE DB

    /**
     * Get Presentation data from Sqlite database by presentation id.
     *
     * @param id
     * @return list of presentation
     */
    public LiveData<Presentation> getPresentationsById(long id) {
        return this.presentationDao.selectPresentationById(id);
    }

    /**
     * Get a list of Presentation from Sqlite database by code cis.
     *
     * @param specialiteIdCodeCis
     * @return list of presentation
     */
    public LiveData<List<Presentation>> getPresentationsByCodeCis(long specialiteIdCodeCis) {
        return this.presentationDao.selectPresentationByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Get Presentation data from Sqlite database by code cip 7.
     *
     * @param codeCip7
     * @return Presentation
     */
    public LiveData<Presentation> getPresentationByCodeCip7(String codeCip7) {
        return this.presentationDao.selectPresentationByCodeCip7(codeCip7);
    }

    /**
     * Get Presentation data from Sqlite database by code cip 13.
     *
     * @param codeCip13
     * @return Presentation
     */
    public LiveData<Presentation> getPresentationByCodeCip13(String codeCip13) {
        return this.presentationDao.selectPresentationByCodeCip13(codeCip13);
    }

    /**
     * Insert Presentation into Sqlite database.
     *
     * @param presentation
     * @return
     */
    public long insertPresentation(Presentation presentation) {
        return this.presentationDao.insertPresentation(presentation);
    }

    /**
     * Update Presentation from Sqlite database
     *
     * @param presentation
     * @return
     */
    public int updatePresentation(Presentation presentation) {
        return this.presentationDao.updatePresentation(presentation);
    }

    /**
     * Delete Presentation from Sqlite database
     *
     * @param presentation
     * @return
     */
    public int deletePresentation(Presentation presentation) {
        return this.presentationDao.deletePresentation(presentation);
    }
}
