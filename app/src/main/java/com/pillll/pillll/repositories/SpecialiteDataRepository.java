package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import com.pillll.pillll.database.NetworkService;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.SpecialiteDao;
import com.pillll.pillll.database.entity.Specialite;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class that abstract access to Specialite data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class SpecialiteDataRepository {

    private final SpecialiteDao specialiteDao;

    public SpecialiteDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.specialiteDao = db.specialiteDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Refresh Specialite data from pillll WebService by code cis
     *
     * @param idCodeCis
     */
    public void refreshSpecialite(Long idCodeCis) {

        // Get instance of pillllApi
        PillllWebService pillllApi = NetworkService.getInstance().getPillllApi();
        Call<Specialite> call = pillllApi.getSpecialiteWithCodeCis(idCodeCis);

        call.enqueue(new Callback<Specialite>() {
            @Override
            public void onResponse(Call<Specialite> call, Response<Specialite> response) {
                if (response.isSuccessful()){
                    persistSpecialite(response.body());
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
            public void onFailure(Call<Specialite> call, Throwable t) {
                // action à effectuer en cas d'echec
                Log.d("error","failure");
            }
        });

    }

    /**
     * Persist Specialite data from Sqlite database in AsyncTask.
     *
     * @param specialite
     */
    private void persistSpecialite(Specialite specialite){

        new AsyncTask<Specialite, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Specialite... specialites) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertSpecialite(specialites[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updateSpecialite(specialites[0]);
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

        }.execute(specialite);
    }

    // ACTION SUR SQLITE DB

    /**
     * Get Specialite data from Sqlite database by code cis.
     *
     * @param specialiteIdCodeCis
     * @return liveData specialite
     */
    public LiveData<Specialite> getSpecialiteByCodeCis(long specialiteIdCodeCis) {
        return this.specialiteDao.selectSpecialiteById(specialiteIdCodeCis);
    }

    /**
     * Insert Specialite into Sqlite database.
     *
     * @param specialite
     * @return
     */
    public long insertSpecialite(Specialite specialite) {
        return this.specialiteDao.insertSpecialite(specialite);
    }

    /**
     * Update Specialite from Sqlite database
     *
     * @param specialite
     * @return
     */
    public int updateSpecialite(Specialite specialite) {
        return this.specialiteDao.updateSpecialite(specialite);
    }

    /**
     * Delete Specialite from Sqlite database
     *
     * @param specialite
     * @return
     */
    public int deleteSpecialite(Specialite specialite) {
        return this.specialiteDao.deleteSpecialite(specialite);
    }
}
