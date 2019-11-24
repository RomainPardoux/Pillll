package com.pillll.pillll.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.ConditionPrescriptionDao;
import com.pillll.pillll.model.entities.ConditionPrescription;
import java.util.List;

/**
 * Repository class that abstract access to ConditionPrescription data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class ConditionPrescriptionDataRepository {

    private final ConditionPrescriptionDao conditionPrescriptionDao;

    public ConditionPrescriptionDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.conditionPrescriptionDao = db.conditionPrescriptionDao();
    }

    /**
     * Persist ConditionPrescription data in Sqlite model in AsyncTask.
     *
     * @param conditionPrescription
     */
    public void persistConditionPrescription(ConditionPrescription conditionPrescription){

        new AsyncTask<ConditionPrescription, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(ConditionPrescription... conditionPrescriptions) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertConditionPrescription(conditionPrescriptions[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updateConditionPrescription(conditionPrescriptions[0]);
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

        }.execute(conditionPrescription);
    }

    /**
     * Get a list of ConditionPrescription from Sqlite model by conditionPrescription id.
     * @param id
     * @return list of conditionPrescription
     */
    public LiveData<ConditionPrescription> getConditionPrescriptionsById(long id){
        return this.conditionPrescriptionDao.selectConditionPrescriptionById(id);
    }

    /**
     * Get a list of ConditionPrescription from Sqlite model by code cis.
     * @param specialiteIdCodeCis
     * @return list of conditionPrescription
     */
    public LiveData<List<ConditionPrescription>> getConditionPrescriptionsByCodeCis(long specialiteIdCodeCis){
        return this.conditionPrescriptionDao.selectConditionPrescriptionByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert ConditionPrescription into Sqlite model.
     * @param conditionPrescription
     * @return
     */
    public long insertConditionPrescription(ConditionPrescription conditionPrescription){
        return this.conditionPrescriptionDao.insertConditionPrescription(conditionPrescription);
    }

    /**
     * Update ConditionPrescription from Sqlite model
     * @param conditionPrescription
     * @return
     */
    public int updateConditionPrescription (ConditionPrescription conditionPrescription){
        return this.conditionPrescriptionDao.updateConditionPrescription(conditionPrescription);
    }

    /**
     * Delete ConditionPrescription from Sqlite model
     * @param conditionPrescription
     * @return
     */
    public int deleteConditionPrescription (ConditionPrescription conditionPrescription){
        return this.conditionPrescriptionDao.deleteConditionPrescription(conditionPrescription);
    }
}
