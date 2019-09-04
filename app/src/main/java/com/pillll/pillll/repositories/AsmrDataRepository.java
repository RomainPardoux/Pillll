package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.AsmrDao;
import com.pillll.pillll.database.entity.Asmr;
import java.util.List;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Repository class that abstract access to Asmr data sources.
 * @author Romain Pardoux
 * @version 1.0
 */
public class AsmrDataRepository {

    private final AsmrDao asmrDao;
    private List<Asmr> asmrsFromApi;
    private LiveData<Asmr> asmrFromSqlite;
    private LiveData<List<Asmr>> asmrsFromSqlite;

    public AsmrDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.asmrDao = db.asmrDao();
    }

    // ACTION SUR WEB SERVICE
    /**
     * Fetch Asmr list from pillll WebService by code cis
     * @param idCodeCis
     */
    private void fetchAsmrsFromApiByCodeCis(Long idCodeCis){
        PillllWebService asmrWebService = new RestAdapter.Builder()
                                                         .setEndpoint(PillllWebService.ENDPOINT)
                                                         .build()
                                                         .create(PillllWebService.class);

        asmrWebService.listAsmrAsync(idCodeCis, new Callback<List<Asmr>>() {
            @Override
            public void success(List<Asmr> asmrs, Response response) {
                if (!asmrs.isEmpty()){ asmrsFromApi = asmrs; }
            }
            @Override
            public void failure(RetrofitError error) {
                // action à effectuer en cas d'echec
            }
        });
    }

    /**
     * * Get a list of Asmr from pillll WebService by code cis
     * @param idCodeCis
     * @return
     */
    public List<Asmr> getAsmrsFromApiByCodeCis(Long idCodeCis) {
        if (asmrsFromApi.isEmpty()){
            fetchAsmrsFromApiByCodeCis(idCodeCis);
        }
        return asmrsFromApi;
    }

    /**
     * Get a list of Asmr from pillll WebService by code cis and persist it into Sqlite database
     * @param specialiteIdCodeCis
     * @return List Asmr
     */
    public LiveData<List<Asmr>> getPersistableAsmrsFromApiByCodeCis(Long specialiteIdCodeCis){
        if (asmrsFromApi.isEmpty()){
            fetchAsmrsFromApiByCodeCis(specialiteIdCodeCis);
        }
        if (!asmrsFromApi.isEmpty()){
            for (int i = 0; i < this.asmrsFromApi.size(); i++) {
                getAsmrsFromSqliteById(asmrsFromApi.get(i).getId());
                if (asmrFromSqlite.getValue().equals(null)){
                    insertAsmr(asmrsFromApi.get(i));
                }else {
                    updateAsmr(asmrsFromApi.get(i));
                }
            }
        }
        return getAsmrsFromSqliteByCodeCis(specialiteIdCodeCis);
    }

    // ACTION SUR SQLITE DB
    /**
     * Get a list of Asmr from Sqlite database by asmr id.
     * @param id
     * @return list of asmr
     */
    public LiveData<Asmr> getAsmrsFromSqliteById(long id){
        this.asmrFromSqlite = this.asmrDao.selectAsmrById(id);
        return this.asmrFromSqlite;
    }

    /**
     * Get a list of Asmr from Sqlite database by code cis.
     * @param specialiteIdCodeCis
     * @return list of asmr
     */
    public LiveData<List<Asmr>> getAsmrsFromSqliteByCodeCis(long specialiteIdCodeCis){
        this.asmrsFromSqlite = this.asmrDao.selectAsmrByCodeCis(specialiteIdCodeCis);
        return this.asmrsFromSqlite;
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