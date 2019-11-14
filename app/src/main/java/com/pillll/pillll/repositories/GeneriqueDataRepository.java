package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import com.pillll.pillll.database.NetworkService;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.GeneriqueDao;
import com.pillll.pillll.database.entity.Generique;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class that abstract access to Generique data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class GeneriqueDataRepository {

    private final GeneriqueDao generiqueDao;

    public GeneriqueDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.generiqueDao = db.generiqueDao();
    }

    /**
     * Persist Generique data in Sqlite database in AsyncTask.
     *
     * @param generique
     */
    public void persistGenerique(Generique generique){

        new AsyncTask<Generique, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Generique... generiques) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertGenerique(generiques[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updateGenerique(generiques[0]);
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

        }.execute(generique);
    }

    /**
     * Get Generique data from Sqlite database by code cis.
     *
     * @param specialiteIdCodeCis
     * @return liveData generique
     */
    public LiveData<Generique> getGeneriqueByCodeCis(long specialiteIdCodeCis) {
        return this.generiqueDao.selectGeneriqueByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert Generique into Sqlite database.
     *
     * @param generique
     * @return
     */
    public long insertGenerique(Generique generique) {
        return this.generiqueDao.insertGenerique(generique);
    }

    /**
     * Update Generique from Sqlite database
     *
     * @param generique
     * @return
     */
    public int updateGenerique(Generique generique) {
        return this.generiqueDao.updateGenerique(generique);
    }

    /**
     * Delete Generique from Sqlite database
     *
     * @param generique
     * @return
     */
    public int deleteGenerique(Generique generique) {
        return this.generiqueDao.deleteGenerique(generique);
    }
}
