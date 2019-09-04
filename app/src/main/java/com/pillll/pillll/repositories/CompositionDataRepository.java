package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.CompositionDao;
import com.pillll.pillll.database.entity.Composition;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Repository class that abstract access to Composition data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class CompositionDataRepository {

    private final CompositionDao compositionDao;
    private List<Composition> compositionsFromApi;
    private LiveData<Composition> compositionFromSqlite;
    private LiveData<List<Composition>> compositionsFromSqlite;

    public CompositionDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.compositionDao = db.compositionDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Fetch Composition list from pillll WebService by code cis
     *
     * @param idCodeCis
     */
    private void fetchCompositionsFromApiByCodeCis(Long idCodeCis) {
        PillllWebService compositionWebService = new RestAdapter.Builder()
                .setEndpoint(PillllWebService.ENDPOINT)
                .build()
                .create(PillllWebService.class);

        compositionWebService.listCompositionAsync(idCodeCis, new Callback<List<Composition>>() {
            @Override
            public void success(List<Composition> compositions, Response response) {
                if (!compositions.isEmpty()) {
                    compositionsFromApi = compositions;
                }
            }

            @Override
            public void failure(RetrofitError error) {
                // action à effectuer en cas d'echec
            }
        });
    }

    /**
     * * Get a list of Composition from pillll WebService by code cis
     *
     * @param idCodeCis
     * @return
     */
    public List<Composition> getCompositionsFromApiByCodeCis(Long idCodeCis) {
        if (compositionsFromApi.isEmpty()) {
            fetchCompositionsFromApiByCodeCis(idCodeCis);
        }
        return compositionsFromApi;
    }

    /**
     * Get a list of Composition from pillll WebService by code cis and persist it into Sqlite database
     *
     * @param specialiteIdCodeCis
     * @return List Composition
     */
    public LiveData<List<Composition>> getPersistableCompositionsFromApiByCodeCis(Long specialiteIdCodeCis) {
        if (compositionsFromApi.isEmpty()) {
            fetchCompositionsFromApiByCodeCis(specialiteIdCodeCis);
        }
        if (!compositionsFromApi.isEmpty()) {
            for (int i = 0; i < this.compositionsFromApi.size(); i++) {
                getCompositionsFromSqliteById(compositionsFromApi.get(i).getId());
                if (compositionFromSqlite.getValue().equals(null)) {
                    insertComposition(compositionsFromApi.get(i));
                } else {
                    updateComposition(compositionsFromApi.get(i));
                }
            }
        }
        return getCompositionsFromSqliteByCodeCis(specialiteIdCodeCis);
    }

    // ACTION SUR SQLITE DB

    /**
     * Get a list of Composition from Sqlite database by composition id.
     *
     * @param id
     * @return list of composition
     */
    public LiveData<Composition> getCompositionsFromSqliteById(long id) {
        this.compositionFromSqlite = this.compositionDao.selectCompositionById(id);
        return this.compositionFromSqlite;
    }

    /**
     * Get a list of Composition from Sqlite database by code cis.
     *
     * @param specialiteIdCodeCis
     * @return list of composition
     */
    public LiveData<List<Composition>> getCompositionsFromSqliteByCodeCis(long specialiteIdCodeCis) {
        this.compositionsFromSqlite = this.compositionDao.selectCompositionByCodeCis(specialiteIdCodeCis);
        return this.compositionsFromSqlite;
    }

    /**
     * Insert Composition into Sqlite database.
     *
     * @param composition
     * @return
     */
    public long insertComposition(Composition composition) {
        return this.compositionDao.insertComposition(composition);
    }

    /**
     * Update Composition from Sqlite database
     *
     * @param composition
     * @return
     */
    public int updateComposition(Composition composition) {
        return this.compositionDao.updateComposition(composition);
    }

    /**
     * Delete Composition from Sqlite database
     *
     * @param composition
     * @return
     */
    public int deleteComposition(Composition composition) {
        return this.compositionDao.deleteComposition(composition);
    }
}
