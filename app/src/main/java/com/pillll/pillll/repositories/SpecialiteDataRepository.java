package com.pillll.pillll.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.SpecialiteDao;
import com.pillll.pillll.model.entities.Specialite;

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

    /**
     * Persist Specialite data from Sqlite model in AsyncTask.
     *
     * @param specialite
     */
    public void persistSpecialite(Specialite specialite){

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

    /**
     * Get Specialite data from Sqlite model by code cis.
     *
     * @param specialiteIdCodeCis
     * @return liveData specialite
     */
    public LiveData<Specialite> getSpecialiteByCodeCis(long specialiteIdCodeCis) {
        return this.specialiteDao.selectSpecialiteById(specialiteIdCodeCis);
    }

    /**
     * Insert Specialite into Sqlite model.
     *
     * @param specialite
     * @return
     */
    public long insertSpecialite(Specialite specialite) {
        return this.specialiteDao.insertSpecialite(specialite);
    }

    /**
     * Update Specialite from Sqlite model
     *
     * @param specialite
     * @return
     */
    public int updateSpecialite(Specialite specialite) {
        return this.specialiteDao.updateSpecialite(specialite);
    }

    /**
     * Delete Specialite from Sqlite model
     *
     * @param specialite
     * @return
     */
    public int deleteSpecialite(Specialite specialite) {
        return this.specialiteDao.deleteSpecialite(specialite);
    }
}
