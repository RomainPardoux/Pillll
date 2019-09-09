package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.ConditionPrescriptionDao;
import com.pillll.pillll.database.entity.ConditionPrescription;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        // Build Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PillllWebService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PillllWebService conditionPrescriptionWebService = retrofit.create(PillllWebService.class);
        Call<List<ConditionPrescription>> call = conditionPrescriptionWebService.listConditionPrescription(idCodeCis);
        call.enqueue(new Callback<List<ConditionPrescription>>() {
            @Override
            public void onResponse(Call<List<ConditionPrescription>> call, Response<List<ConditionPrescription>> response) {
                if (!response.body().isEmpty()){ conditionPrescriptionsFromApi = response.body(); }
            }

            @Override
            public void onFailure(Call<List<ConditionPrescription>> call, Throwable t) {
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
