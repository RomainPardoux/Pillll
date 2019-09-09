package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.LienCtDao;
import com.pillll.pillll.database.entity.LienCt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Repository class that abstract access to LienCt data sources.
 * @author Romain Pardoux
 * @version 1.0
 */
public class LienCtDataRepository {

    private final LienCtDao lienCtDao;
    private LienCt lienCtFromApi;
    private LiveData<LienCt> lienCtFromSqlite;

    public LienCtDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.lienCtDao = db.lienCtDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Fetch LienCt data from pillll WebService by code cis
     *
     * @param codeDossierHas
     */
    private void fetchLienCtFromApiByCodeHas(String codeDossierHas) {
        // Build Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PillllWebService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PillllWebService lienCtWebService = retrofit.create(PillllWebService.class);
        Call<LienCt> call = lienCtWebService.getLienCt(codeDossierHas);
        call.enqueue(new Callback<LienCt>() {
            @Override
            public void onResponse(Call<LienCt> call, Response<LienCt> response) {
                lienCtFromApi = response.body();
            }

            @Override
            public void onFailure(Call<LienCt> call, Throwable t) {
                // action à effectuer en cas d'echec
            }
        });
    }

    /**
     * Get LienCt data from pillll WebService by code Has
     *
     * @param codeDossierHas
     * @return LienCt
     */
    public LienCt getLienCtFromApiByCodeHas(String codeDossierHas) {
        fetchLienCtFromApiByCodeHas(codeDossierHas);
        return lienCtFromApi;
    }

    /**
     * Get LienCt data from pillll WebService by code has and persist it into Sqlite database
     *
     * @param codeDossierHas
     * @return LiveData LienCt
     */
    public LiveData<LienCt> getPersistableLienCtFromApiByCodeHas(String codeDossierHas) {
        fetchLienCtFromApiByCodeHas(codeDossierHas);
        getLienCtFromSqliteByCodeHas(lienCtFromApi.getCodeDossierHas());
        if (lienCtFromSqlite.getValue().equals(null)) {
            insertLienCt(lienCtFromApi);
        } else {
            updateLienCt(lienCtFromApi);
        }
        return getLienCtFromSqliteByCodeHas(codeDossierHas);
    }

    // ACTION SUR SQLITE DB

    /**
     * Get LienCt data from Sqlite database by code cis.
     *
     * @param idCodeHas
     * @return liveData lienCt
     */
    public LiveData<LienCt> getLienCtFromSqliteByCodeHas(String idCodeHas){
        this.lienCtFromSqlite = this.lienCtDao.selectLienCtByIdCodeDossierHas(idCodeHas);
        return this.lienCtFromSqlite;
    }

    /**
     * Insert LienCt into Sqlite database.
     *
     * @param lienCt
     * @return
     */
    public long insertLienCt(LienCt lienCt) {
        return this.lienCtDao.insertLienCt(lienCt);
    }

    /**
     * Update LienCt from Sqlite database
     *
     * @param lienCt
     * @return
     */
    public int updateLienCt(LienCt lienCt) {
        return this.lienCtDao.updateLienCt(lienCt);
    }

    /**
     * Delete LienCt from Sqlite database
     *
     * @param lienCt
     * @return
     */
    public int deleteLienCt(LienCt lienCt) {
        return this.lienCtDao.deleteLienCt(lienCt);
    }
}
