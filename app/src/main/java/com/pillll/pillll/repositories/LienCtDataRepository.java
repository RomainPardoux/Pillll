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

    /**
     * Persist LienCt data from Sqlite database in AsyncTask.
     *
     * @param lienCt
     */
    public void persistLienCt(LienCt lienCt){

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
