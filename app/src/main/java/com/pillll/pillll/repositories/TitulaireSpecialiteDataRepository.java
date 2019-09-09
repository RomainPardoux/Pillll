package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.TitulaireSpecialiteDao;
import com.pillll.pillll.database.entity.TitulaireSpecialite;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Repository class that abstract access to TitulaireSpecialite data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class TitulaireSpecialiteDataRepository {

    private final TitulaireSpecialiteDao titulaireSpecialiteDao;
    private List<TitulaireSpecialite> titulaireSpecialitesFromApi;
    private LiveData<TitulaireSpecialite> titulaireSpecialiteFromSqlite;
    private LiveData<List<TitulaireSpecialite>> titulaireSpecialitesFromSqlite;

    public TitulaireSpecialiteDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.titulaireSpecialiteDao = db.titulaireSpecialiteDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Fetch TitulaireSpecialite list from pillll WebService by code cis
     *
     * @param idCodeCis
     */
    private void fetchTitulaireSpecialitesFromApiByCodeCis(Long idCodeCis) {
        // Build Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PillllWebService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        PillllWebService titulaireSpecialiteWebService = retrofit.create(PillllWebService.class);
        Call<List<TitulaireSpecialite>> call = titulaireSpecialiteWebService.listTitulaireSpecialite(idCodeCis);

        call.enqueue(new Callback<List<TitulaireSpecialite>>() {
            @Override
            public void onResponse(Call<List<TitulaireSpecialite>> call, Response<List<TitulaireSpecialite>> response) {
                if (!response.body().isEmpty()) {
                    titulaireSpecialitesFromApi = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<TitulaireSpecialite>> call, Throwable t) {
                // action à effectuer en cas d'echec
            }
        });
    }

    /**
     * * Get a list of TitulaireSpecialite from pillll WebService by code cis
     *
     * @param idCodeCis
     * @return
     */
    public List<TitulaireSpecialite> getTitulaireSpecialitesFromApiByCodeCis(Long idCodeCis) {
        if (titulaireSpecialitesFromApi.isEmpty()) {
            fetchTitulaireSpecialitesFromApiByCodeCis(idCodeCis);
        }
        return titulaireSpecialitesFromApi;
    }

    /**
     * Get a list of TitulaireSpecialite from pillll WebService by code cis and persist it into Sqlite database
     *
     * @param specialiteIdCodeCis
     * @return List TitulaireSpecialite
     */
    public LiveData<List<TitulaireSpecialite>> getPersistableTitulaireSpecialitesFromApiByCodeCis(Long specialiteIdCodeCis) {
        if (titulaireSpecialitesFromApi.isEmpty()) {
            fetchTitulaireSpecialitesFromApiByCodeCis(specialiteIdCodeCis);
        }
        if (!titulaireSpecialitesFromApi.isEmpty()) {
            for (int i = 0; i < this.titulaireSpecialitesFromApi.size(); i++) {
                getTitulaireSpecialitesFromSqliteById(titulaireSpecialitesFromApi.get(i).getId());
                if (titulaireSpecialiteFromSqlite.getValue().equals(null)) {
                    insertTitulaireSpecialite(titulaireSpecialitesFromApi.get(i));
                } else {
                    updateTitulaireSpecialite(titulaireSpecialitesFromApi.get(i));
                }
            }
        }
        return getTitulaireSpecialitesFromSqliteByCodeCis(specialiteIdCodeCis);
    }

    // ACTION SUR SQLITE DB

    /**
     * Get a list of TitulaireSpecialite from Sqlite database by titulaireSpecialite id.
     *
     * @param id
     * @return list of titulaireSpecialite
     */
    public LiveData<TitulaireSpecialite> getTitulaireSpecialitesFromSqliteById(long id) {
        this.titulaireSpecialiteFromSqlite = this.titulaireSpecialiteDao.selectTitulaireSpecialiteById(id);
        return this.titulaireSpecialiteFromSqlite;
    }

    /**
     * Get a list of TitulaireSpecialite from Sqlite database by code cis.
     *
     * @param specialiteIdCodeCis
     * @return list of titulaireSpecialite
     */
    public LiveData<List<TitulaireSpecialite>> getTitulaireSpecialitesFromSqliteByCodeCis(long specialiteIdCodeCis) {
        this.titulaireSpecialitesFromSqlite = this.titulaireSpecialiteDao.selectTitulaireSpecialiteByCodeCis(specialiteIdCodeCis);
        return this.titulaireSpecialitesFromSqlite;
    }

    /**
     * Insert TitulaireSpecialite into Sqlite database.
     *
     * @param titulaireSpecialite
     * @return
     */
    public long insertTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite) {
        return this.titulaireSpecialiteDao.insertTitulaireSpecialite(titulaireSpecialite);
    }

    /**
     * Update TitulaireSpecialite from Sqlite database
     *
     * @param titulaireSpecialite
     * @return
     */
    public int updateTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite) {
        return this.titulaireSpecialiteDao.updateTitulaireSpecialite(titulaireSpecialite);
    }

    /**
     * Delete TitulaireSpecialite from Sqlite database
     *
     * @param titulaireSpecialite
     * @return
     */
    public int deleteTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite) {
        return this.titulaireSpecialiteDao.deleteTitulaireSpecialite(titulaireSpecialite);
    }
}
