package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import com.pillll.pillll.database.NetworkService;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.VoiesAdministrationDao;
import com.pillll.pillll.database.entity.VoiesAdministration;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class that abstract access to VoiesAdministration data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class VoiesAdministrationDataRepository {

    private final VoiesAdministrationDao voiesAdministrationDao;

    public VoiesAdministrationDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.voiesAdministrationDao = db.voiesAdministrationDao();
    }

    // ACTION SUR WEB SERVICE
    /**
     * Refresh VoiesAdministration list from pillll WebService by code cis
     * @param idCodeCis
     */
    public void refreshVoiesAdministrations(Long idCodeCis) {

        // Get instance of pillllApi
        PillllWebService pillllApi = NetworkService.getInstance().getPillllApi();
        Call<VoiesAdministrations> call = pillllApi.listVoiesAdministration(idCodeCis);

        call.enqueue(new Callback<VoiesAdministrations>() {
            @Override
            public void onResponse(Call<VoiesAdministrations> call, Response<VoiesAdministrations> response) {
                if (response.isSuccessful()){
                    for (VoiesAdministration voiesAdministration : response.body().getData()) {
                        persistVoiesAdministration(voiesAdministration);
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
            public void onFailure(Call<VoiesAdministrations> call, Throwable t) {
                // action à effectuer en cas d'echec
                Log.d("error","failure");
            }
        });
    }

    /**
     * Persist VoiesAdministration data from Sqlite database in AsyncTask.
     *
     * @param voiesAdministration
     */
    private void persistVoiesAdministration(VoiesAdministration voiesAdministration){

        new AsyncTask<VoiesAdministration, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(VoiesAdministration... voiesAdministrations) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertVoiesAdministration(voiesAdministrations[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updateVoiesAdministration(voiesAdministrations[0]);
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

        }.execute(voiesAdministration);
    }

    // ACTION SUR SQLITE DB
    /**
     * Get a list of VoiesAdministration from Sqlite database by voiesAdministration id.
     * @param id
     * @return list of voiesAdministration
     */
    public LiveData<VoiesAdministration> getVoiesAdministrationsById(long id){
        return this.voiesAdministrationDao.selectVoiesAdministrationById(id);
    }

    /**
     * Get a list of VoiesAdministration from Sqlite database by code cis.
     * @param specialiteIdCodeCis
     * @return list of voiesAdministration
     */
    public LiveData<List<VoiesAdministration>> getVoiesAdministrationsByCodeCis(long specialiteIdCodeCis){
        return this.voiesAdministrationDao.selectVoiesAdministrationByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert VoiesAdministration into Sqlite database.
     * @param voiesAdministration
     * @return
     */
    public long insertVoiesAdministration(VoiesAdministration voiesAdministration){
        return this.voiesAdministrationDao.insertVoiesAdministration(voiesAdministration);
    }

    /**
     * Update VoiesAdministration from Sqlite database
     * @param voiesAdministration
     * @return
     */
    public int updateVoiesAdministration (VoiesAdministration voiesAdministration){
        return this.voiesAdministrationDao.updateVoiesAdministration(voiesAdministration);
    }

    /**
     * Delete VoiesAdministration from Sqlite database
     * @param voiesAdministration
     * @return
     */
    public int deleteVoiesAdministration (VoiesAdministration voiesAdministration){
        return this.voiesAdministrationDao.deleteVoiesAdministration(voiesAdministration);
    }
}
