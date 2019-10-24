package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pillll.pillll.database.NetworkService;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.VoiesAdministrationDao;
import com.pillll.pillll.database.entity.VoiesAdministration;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Repository class that abstract access to VoiesAdministration data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class VoiesAdministrationDataRepository {

    private final VoiesAdministrationDao voiesAdministrationDao;
    private List<VoiesAdministration> voiesAdministrationsFromApi;
    private LiveData<VoiesAdministration> voiesAdministrationFromSqlite;
    private LiveData<List<VoiesAdministration>> voiesAdministrationsFromSqlite;

    public VoiesAdministrationDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.voiesAdministrationDao = db.voiesAdministrationDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Fetch VoiesAdministration list from pillll WebService by code cis
     *
     * @param idCodeCis
     */
    private void fetchVoiesAdministrationsFromApiByCodeCis(Long idCodeCis) {
        // Build Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PillllWebService voiesAdministrationWebService = retrofit.create(PillllWebService.class);
        Call<List<VoiesAdministration>> call = voiesAdministrationWebService.listVoiesAdministration(idCodeCis);
        call.enqueue(new Callback<List<VoiesAdministration>>() {
            @Override
            public void onResponse(Call<List<VoiesAdministration>> call, Response<List<VoiesAdministration>> response) {
                if (!response.body().isEmpty()) {
                    voiesAdministrationsFromApi = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<VoiesAdministration>> call, Throwable t) {
                // action à effectuer en cas d'echec
            }
        });
    }

    /**
     * * Get a list of VoiesAdministration from pillll WebService by code cis
     *
     * @param idCodeCis
     * @return
     */
    public List<VoiesAdministration> getVoiesAdministrationsFromApiByCodeCis(Long idCodeCis) {
        if (voiesAdministrationsFromApi.isEmpty()) {
            fetchVoiesAdministrationsFromApiByCodeCis(idCodeCis);
        }
        return voiesAdministrationsFromApi;
    }

    /**
     * Get a list of VoiesAdministration from pillll WebService by code cis and persist it into Sqlite database
     *
     * @param specialiteIdCodeCis
     * @return List VoiesAdministration
     */
    public LiveData<List<VoiesAdministration>> getPersistableVoiesAdministrationsFromApiByCodeCis(Long specialiteIdCodeCis) {
        if (voiesAdministrationsFromApi.isEmpty()) {
            fetchVoiesAdministrationsFromApiByCodeCis(specialiteIdCodeCis);
        }
        if (!voiesAdministrationsFromApi.isEmpty()) {
            for (int i = 0; i < this.voiesAdministrationsFromApi.size(); i++) {
                getVoiesAdministrationsFromSqliteById(voiesAdministrationsFromApi.get(i).getId());
                if (voiesAdministrationFromSqlite.getValue().equals(null)) {
                    insertVoiesAdministration(voiesAdministrationsFromApi.get(i));
                } else {
                    updateVoiesAdministration(voiesAdministrationsFromApi.get(i));
                }
            }
        }
        return getVoiesAdministrationsFromSqliteByCodeCis(specialiteIdCodeCis);
    }

    // ACTION SUR SQLITE DB

    /**
     * Get a list of VoiesAdministration from Sqlite database by voiesAdministration id.
     *
     * @param id
     * @return list of voiesAdministration
     */
    public LiveData<VoiesAdministration> getVoiesAdministrationsFromSqliteById(long id) {
        this.voiesAdministrationFromSqlite = this.voiesAdministrationDao.selectVoiesAdministrationById(id);
        return this.voiesAdministrationFromSqlite;
    }

    /**
     * Get a list of VoiesAdministration from Sqlite database by code cis.
     *
     * @param specialiteIdCodeCis
     * @return list of voiesAdministration
     */
    public LiveData<List<VoiesAdministration>> getVoiesAdministrationsFromSqliteByCodeCis(long specialiteIdCodeCis) {
        this.voiesAdministrationsFromSqlite = this.voiesAdministrationDao.selectVoiesAdministrationByCodeCis(specialiteIdCodeCis);
        return this.voiesAdministrationsFromSqlite;
    }

    /**
     * Insert VoiesAdministration into Sqlite database.
     *
     * @param voiesAdministration
     * @return
     */
    public long insertVoiesAdministration(VoiesAdministration voiesAdministration) {
        return this.voiesAdministrationDao.insertVoiesAdministration(voiesAdministration);
    }

    /**
     * Update VoiesAdministration from Sqlite database
     *
     * @param voiesAdministration
     * @return
     */
    public int updateVoiesAdministration(VoiesAdministration voiesAdministration) {
        return this.voiesAdministrationDao.updateVoiesAdministration(voiesAdministration);
    }

    /**
     * Delete VoiesAdministration from Sqlite database
     *
     * @param voiesAdministration
     * @return
     */
    public int deleteVoiesAdministration(VoiesAdministration voiesAdministration) {
        return this.voiesAdministrationDao.deleteVoiesAdministration(voiesAdministration);
    }
}
