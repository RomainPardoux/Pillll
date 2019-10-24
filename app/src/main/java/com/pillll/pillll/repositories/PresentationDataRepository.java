package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import com.pillll.pillll.database.NetworkService;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.PresentationDao;
import com.pillll.pillll.database.entity.Presentation;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
     * Refresh Presentation data from pillll WebService by code cip
     *
     * @param codeCip
     */
    public void refreshPresentation(String codeCip) {

        // Get instance of pillllApi
        PillllWebService pillllApi = NetworkService.getInstance().getPillllApi();
        Call<Presentation> call;

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

        call.enqueue(new Callback<Presentation>() {
            @Override
            public void onResponse(Call<Presentation> call, Response<Presentation> response) {
                if (response.isSuccessful()){
                    persistPresentation(response.body());
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
            public void onFailure(Call<Presentation> call, Throwable t) {
                // action à effectuer en cas d'echec
                Log.d("error","failure");
            }
        });

    }

    /**
     * Persist Presentation data from Sqlite database in AsyncTask.
     *
     * @param presentation
     */
    private void persistPresentation(Presentation presentation){

        new AsyncTask<Presentation, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Presentation... presentations) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertPresentation(presentations[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updatePresentation(presentations[0]);
                            if (updated > 0){
                                insertedOrUpdated = true;
                            }

                        }catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            return insertedOrUpdated;
                        }
                    }
                }
                return true;
            }

        }.execute(presentation);
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
