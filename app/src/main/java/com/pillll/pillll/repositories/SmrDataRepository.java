package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.SmrDao;
import com.pillll.pillll.database.entity.Smr;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Repository class that abstract access to Smr data sources.
 * @author Romain Pardoux
 * @version 1.0
 */
public class SmrDataRepository {

    private final SmrDao smrDao;
    private List<Smr> smrsFromApi;
    private LiveData<Smr> smrFromSqlite;
    private LiveData<List<Smr>> smrsFromSqlite;

    public SmrDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.smrDao = db.smrDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Fetch Smr list from pillll WebService by code cis
     * @param idCodeCis
     */
    private void fetchSmrsFromApiByCodeCis(Long idCodeCis){
        // Build Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PillllWebService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PillllWebService smrWebService = retrofit.create(PillllWebService.class);
        Call<List<Smr>> call = smrWebService.listSmr(idCodeCis);
        call.enqueue(new Callback<List<Smr>>() {
            @Override
            public void onResponse(Call<List<Smr>> call, Response<List<Smr>> response) {
                if (!response.body().isEmpty()){ smrsFromApi = response.body(); }
            }

            @Override
            public void onFailure(Call<List<Smr>> call, Throwable t) {
                // action à effectuer en cas d'echec
            }
        });
    }

    /**
     * * Get a list of Smr from pillll WebService by code cis
     * @param idCodeCis
     * @return
     */
    public List<Smr> getSmrsFromApiByCodeCis(Long idCodeCis) {
        if (smrsFromApi.isEmpty()){
            fetchSmrsFromApiByCodeCis(idCodeCis);
        }
        return smrsFromApi;
    }

    /**
     * Get a list of Smr from pillll WebService by code cis and persist it into Sqlite database
     * @param specialiteIdCodeCis
     * @return List Smr
     */
    public LiveData<List<Smr>> getPersistableSmrsFromApiByCodeCis(Long specialiteIdCodeCis){
        if (smrsFromApi.isEmpty()){
            fetchSmrsFromApiByCodeCis(specialiteIdCodeCis);
        }
        if (!smrsFromApi.isEmpty()){
            for (int i = 0; i < this.smrsFromApi.size(); i++) {
                getSmrsFromSqliteById(smrsFromApi.get(i).getId());
                if (smrFromSqlite.getValue().equals(null)){
                    insertSmr(smrsFromApi.get(i));
                }else {
                    updateSmr(smrsFromApi.get(i));
                }
            }
        }
        return getSmrsFromSqliteByCodeCis(specialiteIdCodeCis);
    }

    // ACTION SUR SQLITE DB
    /**
     * Get a list of Smr from Sqlite database by smr id.
     * @param id
     * @return list of smr
     */
    public LiveData<Smr> getSmrsFromSqliteById(long id){
        this.smrFromSqlite = this.smrDao.selectSmrById(id);
        return this.smrFromSqlite;
    }

    /**
     * Get a list of Smr from Sqlite database by code cis.
     * @param specialiteIdCodeCis
     * @return list of smr
     */
    public LiveData<List<Smr>> getSmrsFromSqliteByCodeCis(long specialiteIdCodeCis){
        this.smrsFromSqlite = this.smrDao.selectSmrByCodeCis(specialiteIdCodeCis);
        return this.smrsFromSqlite;
    }

    /**
     * Insert Smr into Sqlite database.
     * @param smr
     * @return
     */
    public long insertSmr(Smr smr){
        return this.smrDao.insertSmr(smr);
    }

    /**
     * Update Smr from Sqlite database
     * @param smr
     * @return
     */
    public int updateSmr (Smr smr){
        return this.smrDao.updateSmr(smr);
    }

    /**
     * Delete Smr from Sqlite database
     * @param smr
     * @return
     */
    public int deleteSmr (Smr smr){
        return this.smrDao.deleteSmr(smr);
    }
}
