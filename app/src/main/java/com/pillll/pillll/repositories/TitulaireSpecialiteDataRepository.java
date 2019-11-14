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

    /**
     * Persist TitulaireSpecialite data in Sqlite database in AsyncTask.
     *
     * @param titulaireSpecialite
     */
    public void persistTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite){

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
