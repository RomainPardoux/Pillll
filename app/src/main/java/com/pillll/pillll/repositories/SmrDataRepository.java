package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.SmrDao;
import com.pillll.pillll.model.entities.Smr;
import java.util.List;

/**
 * Repository class that abstract access to Smr data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class SmrDataRepository {

    private final SmrDao smrDao;

    public SmrDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.smrDao = db.smrDao();
    }

    /**
     * Persist Smr data from Sqlite model in AsyncTask.
     *
     * @param smr
     */
    public void persistSmr(Smr smr){

        new AsyncTask<Smr, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Smr... smrs) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertSmr(smrs[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updateSmr(smrs[0]);
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

        }.execute(smr);
    }

    /**
     * Get a list of Smr from Sqlite model by smr id.
     * @param id
     * @return list of smr
     */
    public LiveData<Smr> getSmrsById(long id){
        return this.smrDao.selectSmrById(id);
    }

    /**
     * Get a list of Smr from Sqlite model by code cis.
     * @param specialiteIdCodeCis
     * @return list of smr
     */
    public LiveData<List<Smr>> getSmrsByCodeCis(long specialiteIdCodeCis){
        return this.smrDao.selectSmrByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert Smr into Sqlite model.
     * @param smr
     * @return
     */
    public long insertSmr(Smr smr){
        return this.smrDao.insertSmr(smr);
    }

    /**
     * Update Smr from Sqlite model
     * @param smr
     * @return
     */
    public int updateSmr (Smr smr){
        return this.smrDao.updateSmr(smr);
    }

    /**
     * Delete Smr from Sqlite model
     * @param smr
     * @return
     */
    public int deleteSmr (Smr smr){
        return this.smrDao.deleteSmr(smr);
    }
}
