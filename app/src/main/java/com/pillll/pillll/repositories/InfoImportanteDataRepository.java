package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.InfoImportanteDao;
import com.pillll.pillll.database.entity.InfoImportante;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Repository class that abstract access to InfoImportante data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class InfoImportanteDataRepository {

    private final InfoImportanteDao infoImportanteDao;
    private List<InfoImportante> infoImportantesFromApi;
    private LiveData<InfoImportante> infoImportanteFromSqlite;
    private LiveData<List<InfoImportante>> infoImportantesFromSqlite;

    public InfoImportanteDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.infoImportanteDao = db.infoImportanteDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Fetch InfoImportante list from pillll WebService by code cis
     *
     * @param idCodeCis
     */
    private void fetchInfoImportantesFromApiByCodeCis(Long idCodeCis) {
        // Build Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PillllWebService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PillllWebService infoImportantesWebService = retrofit.create(PillllWebService.class);
        Call<List<InfoImportante>> call = infoImportantesWebService.listInfoImportante(idCodeCis);
        call.enqueue(new Callback<List<InfoImportante>>() {
            @Override
            public void onResponse(Call<List<InfoImportante>> call, Response<List<InfoImportante>> response) {
                if (!response.body().isEmpty()) {
                    infoImportantesFromApi = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<InfoImportante>> call, Throwable t) {
                // action à effectuer en cas d'echec
            }
        });
    }

    /**
     * Get a list of InfoImportante from pillll WebService by code cis
     *
     * @param idCodeCis
     * @return
     */
    public List<InfoImportante> getInfoImportantesFromApiByCodeCis(Long idCodeCis) {
        if (infoImportantesFromApi.isEmpty()) {
            fetchInfoImportantesFromApiByCodeCis(idCodeCis);
        }
        return infoImportantesFromApi;
    }

    /**
     * Get a list of InfoImportante from pillll WebService by code cis and persist it into Sqlite database
     *
     * @param specialiteIdCodeCis
     * @return List InfoImportante
     */
    public LiveData<List<InfoImportante>> getPersistableInfoImportantesFromApiByCodeCis(Long specialiteIdCodeCis) {
        if (infoImportantesFromApi.isEmpty()) {
            fetchInfoImportantesFromApiByCodeCis(specialiteIdCodeCis);
        }
        if (!infoImportantesFromApi.isEmpty()) {
            for (int i = 0; i < this.infoImportantesFromApi.size(); i++) {
                getInfoImportantesFromSqliteById(infoImportantesFromApi.get(i).getId());
                if (infoImportanteFromSqlite.getValue().equals(null)) {
                    insertInfoImportante(infoImportantesFromApi.get(i));
                } else {
                    updateInfoImportante(infoImportantesFromApi.get(i));
                }
            }
        }
        return getInfoImportantesFromSqliteByCodeCis(specialiteIdCodeCis);
    }

    // ACTION SUR SQLITE DB

    /**
     * Get InfoImportante data from Sqlite database by infoImportante id.
     *
     * @param id
     * @return list of infoImportante
     */
    public LiveData<InfoImportante> getInfoImportantesFromSqliteById(long id) {
        this.infoImportanteFromSqlite = this.infoImportanteDao.selectInfoImportanteById(id);
        return this.infoImportanteFromSqlite;
    }

    /**
     * Get a list of InfoImportante from Sqlite database by code cis.
     *
     * @param specialiteIdCodeCis
     * @return list of infoImportante
     */
    public LiveData<List<InfoImportante>> getInfoImportantesFromSqliteByCodeCis(long specialiteIdCodeCis) {
        this.infoImportantesFromSqlite = this.infoImportanteDao.selectInfoImportanteByCodeCis(specialiteIdCodeCis);
        return this.infoImportantesFromSqlite;
    }

    /**
     * Insert InfoImportante into Sqlite database.
     *
     * @param infoImportante
     * @return
     */
    public long insertInfoImportante(InfoImportante infoImportante) {
        return this.infoImportanteDao.insertInfoImportante(infoImportante);
    }

    /**
     * Update InfoImportante from Sqlite database
     *
     * @param infoImportante
     * @return
     */
    public int updateInfoImportante(InfoImportante infoImportante) {
        return this.infoImportanteDao.updateInfoImportante(infoImportante);
    }

    /**
     * Delete InfoImportante from Sqlite database
     *
     * @param infoImportante
     * @return
     */
    public int deleteInfoImportante(InfoImportante infoImportante) {
        return this.infoImportanteDao.deleteInfoImportante(infoImportante);
    }
}
