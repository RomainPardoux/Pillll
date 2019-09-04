package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.GeneriqueDao;
import com.pillll.pillll.database.entity.Generique;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Repository class that abstract access to Generique data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class GeneriqueDataRepository {

    private final GeneriqueDao generiqueDao;
    private Generique generiqueFromApi;
    private LiveData<Generique> generiqueFromSqlite;

    public GeneriqueDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.generiqueDao = db.generiqueDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Fetch Generique data from pillll WebService by code cis
     *
     * @param idCodeCis
     */
    private void fetchGeneriqueFromApiByCodeCis(Long idCodeCis) {
        PillllWebService generiqueWebService = new RestAdapter.Builder()
                .setEndpoint(PillllWebService.ENDPOINT)
                .build()
                .create(PillllWebService.class);

        generiqueWebService.listGeneriqueAsync(idCodeCis, new Callback<Generique>() {
            @Override
            public void success(Generique generique, Response response) {
                generiqueFromApi = generique;
            }

            @Override
            public void failure(RetrofitError error) {
                // action à effectuer en cas d'echec
            }
        });
    }

    /**
     * Get Generique data from pillll WebService by code cis
     *
     * @param idCodeCis
     * @return Generique
     */
    public Generique getGeneriqueFromApiByCodeCis(Long idCodeCis) {
        fetchGeneriqueFromApiByCodeCis(idCodeCis);
        return generiqueFromApi;
    }

    /**
     * Get Generique data from pillll WebService by code cis and persist it into Sqlite database
     *
     * @param specialiteIdCodeCis
     * @return LiveData Generique
     */
    public LiveData<Generique> getPersistableGeneriqueFromApiByCodeCis(Long specialiteIdCodeCis) {
        fetchGeneriqueFromApiByCodeCis(specialiteIdCodeCis);
        getGeneriqueFromSqliteByCodeCis(generiqueFromApi.getSpecialiteIdCodeCis());
        if (generiqueFromSqlite.getValue().equals(null)) {
            insertGenerique(generiqueFromApi);
        } else {
            updateGenerique(generiqueFromApi);
        }
        return getGeneriqueFromSqliteByCodeCis(specialiteIdCodeCis);
    }

    // ACTION SUR SQLITE DB

    /**
     * Get Generique data from Sqlite database by code cis.
     *
     * @param specialiteIdCodeCis
     * @return liveData generique
     */
    public LiveData<Generique> getGeneriqueFromSqliteByCodeCis(long specialiteIdCodeCis) {
        this.generiqueFromSqlite = this.generiqueDao.selectGeneriqueByCodeCis(specialiteIdCodeCis);
        return this.generiqueFromSqlite;
    }

    /**
     * Insert Generique into Sqlite database.
     *
     * @param generique
     * @return
     */
    public long insertGenerique(Generique generique) {
        return this.generiqueDao.insertGenerique(generique);
    }

    /**
     * Update Generique from Sqlite database
     *
     * @param generique
     * @return
     */
    public int updateGenerique(Generique generique) {
        return this.generiqueDao.updateGenerique(generique);
    }

    /**
     * Delete Generique from Sqlite database
     *
     * @param generique
     * @return
     */
    public int deleteGenerique(Generique generique) {
        return this.generiqueDao.deleteGenerique(generique);
    }
}
