package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import com.pillll.pillll.database.NetworkService;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.LienCtDao;
import com.pillll.pillll.database.entity.LienCt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class that abstract access to LienCt data sources.
 * @author Romain Pardoux
 * @version 1.0
 */
public class LienCtDataRepository {

    private final LienCtDao lienCtDao;

    public LienCtDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.lienCtDao = db.lienCtDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Refresh LienCt data from pillll WebService by code dossier has
     *
     * @param codeDossierHas
     */
    public void refreshLienCt(String codeDossierHas) {

        // Get instance of pillllApi
        PillllWebService pillllApi = NetworkService.getInstance().getPillllApi();
        Call<LienCt> call = pillllApi.getLienCt(codeDossierHas);

        call.enqueue(new Callback<LienCt>() {
            @Override
            public void onResponse(Call<LienCt> call, Response<LienCt> response) {
                if (response.isSuccessful()){
                    persistLienCt(response.body());
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
            public void onFailure(Call<LienCt> call, Throwable t) {
                // action à effectuer en cas d'echec
                Log.d("error","failure");
            }
        });
    }

    /**
     * Persist LienCt data from Sqlite database in AsyncTask.
     *
     * @param lienCt
     */
    private void persistLienCt(LienCt lienCt){

        new AsyncTask<LienCt, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(LienCt... lienCts) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertLienCt(lienCts[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updateLienCt(lienCts[0]);
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

        }.execute(lienCt);
    }

    // ACTION SUR SQLITE DB

    /**
     * Get LienCt data from Sqlite database by code dossier has.
     *
     * @param codeDossierHas
     * @return liveData lienCt
     */
    public LiveData<LienCt> getLienCtByCodeHas(String codeDossierHas) {
        return this.lienCtDao.selectLienCtByIdCodeDossierHas(codeDossierHas);
    }

    /**
     * Insert LienCt into Sqlite database.
     *
     * @param lienCt
     * @return
     */
    public long insertLienCt(LienCt lienCt) {
        return this.lienCtDao.insertLienCt(lienCt);
    }

    /**
     * Update LienCt from Sqlite database
     *
     * @param lienCt
     * @return
     */
    public int updateLienCt(LienCt lienCt) {
        return this.lienCtDao.updateLienCt(lienCt);
    }

    /**
     * Delete LienCt from Sqlite database
     *
     * @param lienCt
     * @return
     */
    public int deleteLienCt(LienCt lienCt) {
        return this.lienCtDao.deleteLienCt(lienCt);
    }
}
