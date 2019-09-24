package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.SpecialiteDao;
import com.pillll.pillll.database.entity.Specialite;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Repository class that abstract access to Specialite data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class SpecialiteDataRepository {

    private final SpecialiteDao specialiteDao;
    private Specialite specialiteFromApi;
    private LiveData<Specialite> specialiteFromSqlite;

    public SpecialiteDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.specialiteDao = db.specialiteDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Fetch Specialite data from pillll WebService by code cis
     *
     * @param idCodeCis
     */
    private void fetchSpecialiteFromApiByCodeCis(Long idCodeCis) {

        // Build Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PillllWebService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PillllWebService specialiteWebService = retrofit.create(PillllWebService.class);

        Call<Specialite> call = specialiteWebService.getSpecialiteWithCodeCis(idCodeCis);
        call.enqueue(new Callback<Specialite>() {
            @Override
            public void onResponse(Call<Specialite> call, Response<Specialite> response) {
                specialiteFromApi = response.body();
                Log.d("msg", response.body().getDenomination());
            }

            @Override
            public void onFailure(Call<Specialite> call, Throwable t) {
                Log.d("msg", "fail");
            }
        });
    }

    /**
     * Get Specialite data from pillll WebService by code cis
     *
     * @param idCodeCis
     * @return Specialite
     */
    public Specialite getSpecialiteFromApiByCodeCis(Long idCodeCis) {
        fetchSpecialiteFromApiByCodeCis(idCodeCis);
        return specialiteFromApi;
    }

    /**
     * Get Specialite data from pillll WebService by code cis and persist it into Sqlite database
     *
     * @param specialiteIdCodeCis
     * @return LiveData Specialite
     */
    public LiveData<Specialite> getPersistableSpecialiteFromApiByCodeCis(Long specialiteIdCodeCis) {
        fetchSpecialiteFromApiByCodeCis(specialiteIdCodeCis);
        getSpecialiteFromSqliteByCodeCis(specialiteFromApi.getIdCodeCis());
        if (specialiteFromSqlite.getValue().equals(null)) {
            insertSpecialite(specialiteFromApi);
        } else {
            updateSpecialite(specialiteFromApi);
        }
        return getSpecialiteFromSqliteByCodeCis(specialiteIdCodeCis);
    }

    // ACTION SUR SQLITE DB

    /**
     * Get Specialite data from Sqlite database by code cis.
     *
     * @param specialiteIdCodeCis
     * @return liveData specialite
     */
    public LiveData<Specialite> getSpecialiteFromSqliteByCodeCis(long specialiteIdCodeCis) {
        this.specialiteFromSqlite = this.specialiteDao.selectSpecialiteById(specialiteIdCodeCis);
        return this.specialiteFromSqlite;
    }

    /**
     * Insert Specialite into Sqlite database.
     *
     * @param specialite
     * @return
     */
    public long insertSpecialite(Specialite specialite) {
        return this.specialiteDao.insertSpecialite(specialite);
    }

    /**
     * Update Specialite from Sqlite database
     *
     * @param specialite
     * @return
     */
    public int updateSpecialite(Specialite specialite) {
        return this.specialiteDao.updateSpecialite(specialite);
    }

    /**
     * Delete Specialite from Sqlite database
     *
     * @param specialite
     * @return
     */
    public int deleteSpecialite(Specialite specialite) {
        return this.specialiteDao.deleteSpecialite(specialite);
    }
}
