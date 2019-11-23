package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.AsmrDao;
import com.pillll.pillll.model.entities.Asmr;
import java.util.List;

/**
 * Repository class that abstract access to Asmr data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class AsmrDataRepository {

    private final AsmrDao asmrDao;

    public AsmrDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.asmrDao = db.asmrDao();
    }

    /**
     * Persist Asmr data in Sqlite model in AsyncTask.
     *
     * @param asmr
     */
    public void persistAsmr(Asmr asmr){

        new AsyncTask<Asmr, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Asmr... asmrs) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertAsmr(asmrs[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updateAsmr(asmrs[0]);
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

        }.execute(asmr);
    }

    /**
     * Get a list of Asmr from Sqlite model by asmr id.
     * @param id
     * @return list of asmr
     */
    public LiveData<Asmr> getAsmrsById(long id){
        return this.asmrDao.selectAsmrById(id);
    }

    /**
     * Get a list of Asmr from Sqlite model by code cis.
     * @param specialiteIdCodeCis
     * @return list of asmr
     */
    public LiveData<List<Asmr>> getAsmrsByCodeCis(long specialiteIdCodeCis){
        return this.asmrDao.selectAsmrByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert Asmr into Sqlite model.
     * @param asmr
     * @return
     */
    public long insertAsmr(Asmr asmr){
        return this.asmrDao.insertAsmr(asmr);
    }

    /**
     * Update Asmr from Sqlite model
     * @param asmr
     * @return
     */
    public int updateAsmr (Asmr asmr){
        return this.asmrDao.updateAsmr(asmr);
    }

    /**
     * Delete Asmr from Sqlite model
     * @param asmr
     * @return
     */
    public int deleteAsmr (Asmr asmr){
        return this.asmrDao.deleteAsmr(asmr);
    }
}
