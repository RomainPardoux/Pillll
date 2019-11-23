package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.VoiesAdministrationDao;
import com.pillll.pillll.model.entities.VoiesAdministration;
import java.util.List;

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

    /**
     * Persist VoiesAdministration data from Sqlite model in AsyncTask.
     *
     * @param voiesAdministration
     */
    public void persistVoiesAdministration(VoiesAdministration voiesAdministration){

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

    /**
     * Get a list of VoiesAdministration from Sqlite model by voiesAdministration id.
     * @param id
     * @return list of voiesAdministration
     */
    public LiveData<VoiesAdministration> getVoiesAdministrationsById(long id){
        return this.voiesAdministrationDao.selectVoiesAdministrationById(id);
    }

    /**
     * Get a list of VoiesAdministration from Sqlite model by code cis.
     * @param specialiteIdCodeCis
     * @return list of voiesAdministration
     */
    public LiveData<List<VoiesAdministration>> getVoiesAdministrationsByCodeCis(long specialiteIdCodeCis){
        return this.voiesAdministrationDao.selectVoiesAdministrationByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert VoiesAdministration into Sqlite model.
     * @param voiesAdministration
     * @return
     */
    public long insertVoiesAdministration(VoiesAdministration voiesAdministration){
        return this.voiesAdministrationDao.insertVoiesAdministration(voiesAdministration);
    }

    /**
     * Update VoiesAdministration from Sqlite model
     * @param voiesAdministration
     * @return
     */
    public int updateVoiesAdministration (VoiesAdministration voiesAdministration){
        return this.voiesAdministrationDao.updateVoiesAdministration(voiesAdministration);
    }

    /**
     * Delete VoiesAdministration from Sqlite model
     * @param voiesAdministration
     * @return
     */
    public int deleteVoiesAdministration (VoiesAdministration voiesAdministration){
        return this.voiesAdministrationDao.deleteVoiesAdministration(voiesAdministration);
    }
}
