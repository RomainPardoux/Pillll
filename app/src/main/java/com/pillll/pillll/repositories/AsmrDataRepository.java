package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import com.pillll.pillll.database.NetworkService;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.AsmrDao;
import com.pillll.pillll.database.entity.Asmr;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class that abstract access to Asmr data sources.
 * @author Romain Pardoux
 * @version 1.0
 */
public class AsmrDataRepository {

    private final AsmrDao asmrDao;

    public AsmrDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.asmrDao = db.asmrDao();
    }

    // ACTION SUR WEB SERVICE
    /**
     * Persist Asmr data in Sqlite database in AsyncTask.
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
     * Get a list of Asmr from Sqlite database by asmr id.
     * @param id
     * @return list of asmr
     */
    public LiveData<Asmr> getAsmrsById(long id){
        return this.asmrDao.selectAsmrById(id);
    }

    /**
     * Get a list of Asmr from Sqlite database by code cis.
     * @param specialiteIdCodeCis
     * @return list of asmr
     */
    public LiveData<List<Asmr>> getAsmrsByCodeCis(long specialiteIdCodeCis){
        return this.asmrDao.selectAsmrByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert Asmr into Sqlite database.
     * @param asmr
     * @return
     */
    public long insertAsmr(Asmr asmr){
        return this.asmrDao.insertAsmr(asmr);
    }

    /**
     * Update Asmr from Sqlite database
     * @param asmr
     * @return
     */
    public int updateAsmr (Asmr asmr){
        return this.asmrDao.updateAsmr(asmr);
    }

    /**
     * Delete Asmr from Sqlite database
     * @param asmr
     * @return
     */
    public int deleteAsmr (Asmr asmr){
        return this.asmrDao.deleteAsmr(asmr);
    }
}
