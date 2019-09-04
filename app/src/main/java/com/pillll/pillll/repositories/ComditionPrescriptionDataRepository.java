package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.ConditionPrescriptionDao;
import com.pillll.pillll.database.entity.ConditionPrescription;
import java.util.List;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Repository class that abstract access to ConditionPrescription data sources.
 * @author Romain Pardoux
 * @version 1.0
 */
public class ComditionPrescriptionDataRepository {

    private final ConditionPrescriptionDao conditionPrescriptionDao;
    private List<ConditionPrescription> conditionPrescriptionsFromApi;
    private LiveData<ConditionPrescription> conditionPrescriptionFromSqlite;
    private LiveData<List<ConditionPrescription>> conditionPrescriptionsFromSqlite;

    public ComditionPrescriptionDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.conditionPrescriptionDao = db.conditionPrescriptionDao();
    }

    // ACTION SUR WEB SERVICE
    /**
     * Fetch ConditionPrescription list from pillll WebService by code cis
     * @param idCodeCis
     */
    private void fetchConditionPrescriptionsFromApiByCodeCis(Long idCodeCis){
        PillllWebService conditionPrescriptionWebService = new RestAdapter.Builder()
                .setEndpoint(PillllWebService.ENDPOINT)
                .build()
                .create(PillllWebService.class);

        conditionPrescriptionWebService.listConditionPrescriptionAsync(idCodeCis, new Callback<List<ConditionPrescription>>() {
            @Override
            public void success(List<ConditionPrescription> conditionPrescriptions, Response response) {
                if (!conditionPrescriptions.isEmpty()){ conditionPrescriptionsFromApi = conditionPrescriptions; }
            }
            @Override
            public void failure(RetrofitError error) {
                // action à effectuer en cas d'echec
            }
        });
    }

    /**
     * * Get a list of ConditionPrescription from pillll WebService by code cis
     * @param idCodeCis
     * @return
     */
    public List<ConditionPrescription> getConditionPrescriptionsFromApiByCodeCis(Long idCodeCis) {
        if (conditionPrescriptionsFromApi.isEmpty()){
            fetchConditionPrescriptionsFromApiByCodeCis(idCodeCis);
        }
        return conditionPrescriptionsFromApi;
    }

    /**
     * Get a list of ConditionPrescription from pillll WebService by code cis and persist it into Sqlite database
     * @param specialiteIdCodeCis
     * @return List ConditionPrescription
     */
    public LiveData<List<ConditionPrescription>> getPersistableConditionPrescriptionsFromApiByCodeCis(Long specialiteIdCodeCis){
        if (conditionPrescriptionsFromApi.isEmpty()){
            fetchConditionPrescriptionsFromApiByCodeCis(specialiteIdCodeCis);
        }
        if (!conditionPrescriptionsFromApi.isEmpty()){
            for (int i = 0; i < this.conditionPrescriptionsFromApi.size(); i++) {
                getConditionPrescriptionsFromSqliteById(conditionPrescriptionsFromApi.get(i).getId());
                if (conditionPrescriptionFromSqlite.getValue().equals(null)){
                    insertConditionPrescription(conditionPrescriptionsFromApi.get(i));
                }else {
                    updateConditionPrescription(conditionPrescriptionsFromApi.get(i));
                }
            }
        }
        return getConditionPrescriptionsFromSqliteByCodeCis(specialiteIdCodeCis);
    }

    // ACTION SUR SQLITE DB
    /**
     * Get a list of ConditionPrescription from Sqlite database by conditionPrescription id.
     * @param id
     * @return list of conditionPrescription
     */
    public LiveData<ConditionPrescription> getConditionPrescriptionsFromSqliteById(long id){
        this.conditionPrescriptionFromSqlite = this.conditionPrescriptionDao.selectConditionPrescriptionById(id);
        return this.conditionPrescriptionFromSqlite;
    }

    /**
     * Get a list of ConditionPrescription from Sqlite database by code cis.
     * @param specialiteIdCodeCis
     * @return list of conditionPrescription
     */
    public LiveData<List<ConditionPrescription>> getConditionPrescriptionsFromSqliteByCodeCis(long specialiteIdCodeCis){
        this.conditionPrescriptionsFromSqlite = this.conditionPrescriptionDao.selectConditionPrescriptionByCodeCis(specialiteIdCodeCis);
        return this.conditionPrescriptionsFromSqlite;
    }

    /**
     * Insert ConditionPrescription into Sqlite database.
     * @param conditionPrescription
     * @return
     */
    public long insertConditionPrescription(ConditionPrescription conditionPrescription){
        return this.conditionPrescriptionDao.insertConditionPrescription(conditionPrescription);
    }

    /**
     * Update ConditionPrescription from Sqlite database
     * @param conditionPrescription
     * @return
     */
    public int updateConditionPrescription (ConditionPrescription conditionPrescription){
        return this.conditionPrescriptionDao.updateConditionPrescription(conditionPrescription);
    }

    /**
     * Delete ConditionPrescription from Sqlite database
     * @param conditionPrescription
     * @return
     */
    public int deleteConditionPrescription (ConditionPrescription conditionPrescription){
        return this.conditionPrescriptionDao.deleteConditionPrescription(conditionPrescription);
    }
}
