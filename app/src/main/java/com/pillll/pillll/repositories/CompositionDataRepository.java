package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import com.pillll.pillll.database.NetworkService;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.CompositionDao;
import com.pillll.pillll.database.entity.Composition;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class that abstract access to Composition data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class CompositionDataRepository {

    private final CompositionDao compositionDao;

    public CompositionDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.compositionDao = db.compositionDao();
    }

    // ACTION SUR WEB SERVICE
    /**
     * Refresh Composition list from pillll WebService by code cis
     * @param idCodeCis
     */
    public void refreshCompositions(Long idCodeCis) {

        // Get instance of pillllApi
        PillllWebService pillllApi = NetworkService.getInstance().getPillllApi();
        Call<Compositions> call = pillllApi.listComposition(idCodeCis);

        call.enqueue(new Callback<Compositions>() {
            @Override
            public void onResponse(Call<Compositions> call, Response<Compositions> response) {
                if (response.isSuccessful()){
                    for (Composition composition : response.body().getData()) {
                        persistComposition(composition);
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
            public void onFailure(Call<Compositions> call, Throwable t) {
                // action à effectuer en cas d'echec
                Log.d("error","failure");
            }
        });

    }

    /**
     * Persist Composition data from Sqlite database in AsyncTask.
     *
     * @param composition
     */
    private void persistComposition(Composition composition){

        new AsyncTask<Composition, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Composition... compositions) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertComposition(compositions[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updateComposition(compositions[0]);
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

        }.execute(composition);
    }

    // ACTION SUR SQLITE DB
    /**
     * Get a list of Composition from Sqlite database by composition id.
     * @param id
     * @return list of composition
     */
    public LiveData<Composition> getCompositionById(long id){
        return this.compositionDao.selectCompositionById(id);
    }

    /**
     * Get a list of Composition from Sqlite database by code cis.
     * @param specialiteIdCodeCis
     * @return list of composition
     */
    public LiveData<List<Composition>> getCompositionsByCodeCis(long specialiteIdCodeCis){
        return this.compositionDao.selectCompositionByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert Composition into Sqlite database.
     * @param composition
     * @return
     */
    public long insertComposition(Composition composition){
        return this.compositionDao.insertComposition(composition);
    }

    /**
     * Update Composition from Sqlite database
     * @param composition
     * @return
     */
    public int updateComposition (Composition composition){
        return this.compositionDao.updateComposition(composition);
    }

    /**
     * Delete Composition from Sqlite database
     * @param composition
     * @return
     */
    public int deleteComposition (Composition composition){
        return this.compositionDao.deleteComposition(composition);
    }
}
