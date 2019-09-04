package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.PresentationDao;
import com.pillll.pillll.database.entity.Presentation;
import java.util.List;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Repository class that abstract access to Presentation data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class PresentationDataRepository {

    private final PresentationDao presentationDao;
    private Presentation presentationFromApi;
    private List<Presentation> presentationsFromApi;
    private LiveData<Presentation> presentationFromSqlite;
    private LiveData<List<Presentation>> presentationsFromSqlite;

    public PresentationDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.presentationDao = db.presentationDao();
    }

    // ACTION SUR WEB SERVICE

    /**
     * Fetch Presentation list from pillll WebService by code cis
     *
     * @param idCodeCis
     */
    private void fetchPresentationsFromApiByCodeCis(Long idCodeCis) {
        PillllWebService presentationWebService = new RestAdapter.Builder()
                .setEndpoint(PillllWebService.ENDPOINT)
                .build()
                .create(PillllWebService.class);

        presentationWebService.listPresentationAsync(idCodeCis, new Callback<List<Presentation>>() {
            @Override
            public void success(List<Presentation> presentations, Response response) {
                if (!presentations.isEmpty()) {
                    presentationsFromApi = presentations;
                }
            }

            @Override
            public void failure(RetrofitError error) {
                // action à effectuer en cas d'echec
            }
        });
    }

    /**
     * Fetch Presentation data from pillll WebService by code cip
     *
     * @param codeCip
     */
    private void fetchPresentationFromApiByCodeCip(String codeCip) {
        PillllWebService presentationWebService = new RestAdapter.Builder()
                .setEndpoint(PillllWebService.ENDPOINT)
                .build()
                .create(PillllWebService.class);

        if (codeCip.length() == 7) {
            presentationWebService.getPresentationWithCodeCip7Async(codeCip, new Callback<Presentation>() {
                @Override
                public void success(Presentation presentation, Response response) {
                    presentationFromApi = presentation;
                }

                @Override
                public void failure(RetrofitError error) {
                    // action à effectuer en cas d'echec
                }
            });
        } else if (codeCip.length() == 13) {
            presentationWebService.getPresentationWithCodeCip13Async(codeCip, new Callback<Presentation>() {
                @Override
                public void success(Presentation presentation, Response response) {
                    presentationFromApi = presentation;
                }

                @Override
                public void failure(RetrofitError error) {
                    // action à effectuer en cas d'echec
                }
            });
        } else {
            // action à effectuer si code cip <> de 7 ou 13
        }
    }

    /**
     * * Get a list of Presentation from pillll WebService by code cis
     *
     * @param idCodeCis
     * @return List Presentation
     */
    public List<Presentation> getPresentationsFromApiByCodeCis(Long idCodeCis) {
        if (presentationsFromApi.isEmpty()) {
            fetchPresentationsFromApiByCodeCis(idCodeCis);
        }
        return presentationsFromApi;
    }

    /**
     * * Get Presentation data from pillll WebService by code cip
     *
     * @param codeCip
     * @return Presentation
     */
    public Presentation getPresentationFromApiByCodeCip(String codeCip) {
        fetchPresentationFromApiByCodeCip(codeCip);
        return presentationFromApi;
    }

    /**
     * Get a list of Presentation from pillll WebService by code cis and persist it into Sqlite database
     *
     * @param specialiteIdCodeCis
     * @return List Presentation
     */
    public LiveData<List<Presentation>> getPersistablePresentationsFromApiByCodeCis(Long specialiteIdCodeCis) {
        if (presentationsFromApi.isEmpty()) {
            fetchPresentationsFromApiByCodeCis(specialiteIdCodeCis);
        }
        if (!presentationsFromApi.isEmpty()) {
            for (int i = 0; i < this.presentationsFromApi.size(); i++) {
                getPresentationsFromSqliteById(presentationsFromApi.get(i).getId());
                if (presentationFromSqlite.getValue().equals(null)) {
                    insertPresentation(presentationsFromApi.get(i));
                } else {
                    updatePresentation(presentationsFromApi.get(i));
                }
            }
        }
        return getPresentationsFromSqliteByCodeCis(specialiteIdCodeCis);
    }


    /**
     * Get Presentation data from pillll WebService by code cip and persist it into Sqlite database
     *
     * @param codeCip
     * @return LiveData Presentation
     */
    public LiveData<Presentation> getPersistablePresentationsFromApiByCodeCip(String codeCip) {
        fetchPresentationFromApiByCodeCip(codeCip);
        getPresentationsFromSqliteById(presentationFromApi.getId());
        if (presentationFromSqlite.getValue().equals(null)) {
            insertPresentation(presentationFromApi);
        } else {
            updatePresentation(presentationFromApi);
        }
        if (codeCip.length() == 13) {
            presentationFromSqlite = getPresentationFromSqliteByCodeCip13(codeCip);
        } else {
            presentationFromSqlite = getPresentationFromSqliteByCodeCip7(codeCip);
        }
        return presentationFromSqlite;
    }

    // ACTION SUR SQLITE DB

    /**
     * Get Presentation data from Sqlite database by presentation id.
     *
     * @param id
     * @return list of presentation
     */
    public LiveData<Presentation> getPresentationsFromSqliteById(long id) {
        this.presentationFromSqlite = this.presentationDao.selectPresentationById(id);
        return this.presentationFromSqlite;
    }

    /**
     * Get a list of Presentation from Sqlite database by code cis.
     *
     * @param specialiteIdCodeCis
     * @return list of presentation
     */
    public LiveData<List<Presentation>> getPresentationsFromSqliteByCodeCis(long specialiteIdCodeCis) {
        this.presentationsFromSqlite = this.presentationDao.selectPresentationByCodeCis(specialiteIdCodeCis);
        return this.presentationsFromSqlite;
    }

    /**
     * Get Presentation data from Sqlite database by code cip 7.
     *
     * @param codeCip7
     * @return Presentation
     */
    public LiveData<Presentation> getPresentationFromSqliteByCodeCip7(String codeCip7) {
        this.presentationFromSqlite = this.presentationDao.selectPresentationByCodeCip7(codeCip7);
        return this.presentationFromSqlite;
    }

    /**
     * Get Presentation data from Sqlite database by code cip 13.
     *
     * @param codeCip13
     * @return Presentation
     */
    public LiveData<Presentation> getPresentationFromSqliteByCodeCip13(String codeCip13) {
        this.presentationFromSqlite = this.presentationDao.selectPresentationByCodeCip13(codeCip13);
        return this.presentationFromSqlite;
    }

    /**
     * Insert Presentation into Sqlite database.
     *
     * @param presentation
     * @return
     */
    public long insertPresentation(Presentation presentation) {
        return this.presentationDao.insertPresentation(presentation);
    }

    /**
     * Update Presentation from Sqlite database
     *
     * @param presentation
     * @return
     */
    public int updatePresentation(Presentation presentation) {
        return this.presentationDao.updatePresentation(presentation);
    }

    /**
     * Delete Presentation from Sqlite database
     *
     * @param presentation
     * @return
     */
    public int deletePresentation(Presentation presentation) {
        return this.presentationDao.deletePresentation(presentation);
    }
}
