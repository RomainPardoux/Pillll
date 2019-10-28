package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import com.pillll.pillll.database.NetworkService;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.TitulaireSpecialiteDao;
import com.pillll.pillll.database.entity.TitulaireSpecialite;
import com.pillll.pillll.database.entity.TitulairesSpecialites;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class that abstract access to TitulaireSpecialite data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class TitulaireSpecialiteDataRepository {

    private final TitulaireSpecialiteDao titulaireSpecialiteDao;

    public TitulaireSpecialiteDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.titulaireSpecialiteDao = db.titulaireSpecialiteDao();
    }

    // ACTION SUR WEB SERVICE
    /**
     * Refresh TitulaireSpecialite list from pillll WebService by code cis
     * @param idCodeCis
     */
    public void refreshTitulaireSpecialites(Long idCodeCis) {

        // Get instance of pillllApi
        PillllWebService pillllApi = NetworkService.getInstance().getPillllApi();
        Call<TitulairesSpecialites> call = pillllApi.listTitulaireSpecialite(idCodeCis);

        call.enqueue(new Callback<TitulairesSpecialites>() {
            @Override
            public void onResponse(Call<TitulairesSpecialites> call, Response<TitulairesSpecialites> response) {
                if (response.isSuccessful()){
                    for (TitulaireSpecialite titulaireSpecialite : response.body().getData()) {
                        persistTitulaireSpecialite(titulaireSpecialite);
                    }
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
            public void onFailure(Call<TitulairesSpecialites> call, Throwable t) {
                // action à effectuer en cas d'echec
                Log.d("error","failure");
            }
        });

    }

    /**
     * Persist TitulaireSpecialite data from Sqlite database in AsyncTask.
     *
     * @param titulaireSpecialite
     */
    private void persistTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite){

        new AsyncTask<TitulaireSpecialite, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(TitulaireSpecialite... titulaireSpecialites) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertTitulaireSpecialite(titulaireSpecialites[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updateTitulaireSpecialite(titulaireSpecialites[0]);
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

        }.execute(titulaireSpecialite);
    }

    // ACTION SUR SQLITE DB
    /**
     * Get a list of TitulaireSpecialite from Sqlite database by titulaireSpecialite id.
     *
     * @param id
     * @return list of titulaireSpecialite
     */
    public LiveData<TitulaireSpecialite> getTitulaireSpecialitesById(long id){
        return this.titulaireSpecialiteDao.selectTitulaireSpecialiteById(id);
    }

    /**
     * Get a list of TitulaireSpecialite from Sqlite database by code cis.
     * @param specialiteIdCodeCis
     * @return list of titulaireSpecialite
     */
    public LiveData<List<TitulaireSpecialite>> getTitulaireSpecialitesByCodeCis(long specialiteIdCodeCis){
        return this.titulaireSpecialiteDao.selectTitulaireSpecialiteByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert TitulaireSpecialite into Sqlite database.
     * @param titulaireSpecialite
     * @return
     */
    public long insertTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite){
        return this.titulaireSpecialiteDao.insertTitulaireSpecialite(titulaireSpecialite);
    }

    /**
     * Update TitulaireSpecialite from Sqlite database
     * @param titulaireSpecialite
     * @return
     */
    public int updateTitulaireSpecialite (TitulaireSpecialite titulaireSpecialite){
        return this.titulaireSpecialiteDao.updateTitulaireSpecialite(titulaireSpecialite);
    }

    /**
     * Delete TitulaireSpecialite from Sqlite database
     * @param titulaireSpecialite
     * @return
     */
    public int deleteTitulaireSpecialite (TitulaireSpecialite titulaireSpecialite){
        return this.titulaireSpecialiteDao.deleteTitulaireSpecialite(titulaireSpecialite);
    }
}
