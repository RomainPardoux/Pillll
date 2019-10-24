package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import com.pillll.pillll.database.NetworkService;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.dao.InfoImportanteDao;
import com.pillll.pillll.database.entity.InfoImportante;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class that abstract access to InfoImportante data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class InfoImportanteDataRepository {

    private final InfoImportanteDao infoImportanteDao;

    public InfoImportanteDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.infoImportanteDao = db.infoImportanteDao();
    }

    // ACTION SUR WEB SERVICE
    /**
     * Refresh InfoImportante list from pillll WebService by code cis
     * @param idCodeCis
     */
    public void refreshInfoImportantes(Long idCodeCis) {

        // Get instance of pillllApi
        PillllWebService pillllApi = NetworkService.getInstance().getPillllApi();
        Call<List<InfoImportante>> call = pillllApi.listInfoImportante(idCodeCis);

        call.enqueue(new Callback<List<InfoImportante>>() {
            @Override
            public void onResponse(Call<List<InfoImportante>> call, Response<List<InfoImportante>> response) {
                if (response.isSuccessful()){
                    for (InfoImportante infoImportante : response.body()) {
                        persistInfoImportante(infoImportante);
                    }
                }else {
                    //error case
                    switch (response.code()){
                        case 404:
                            Log.d("error", "not found");
                            break;
                        case 500:
                            Log.d("error", "not logged in or server broken");
                            break;
                        default:
                            Log.d("error","unknown error");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<InfoImportante>> call, Throwable t) {
                // action à effectuer en cas d'echec
                Log.d("error","failure");
            }
        });
    }

    /**
     * Persist InfoImportante data from Sqlite database in AsyncTask.
     *
     * @param infoImportante
     */
    private void persistInfoImportante(InfoImportante infoImportante){

        new AsyncTask<InfoImportante, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(InfoImportante... infoImportantes) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertInfoImportante(infoImportantes[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updateInfoImportante(infoImportantes[0]);
                            if (updated > 0){
                                insertedOrUpdated = true;
                            }

                        }catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            return insertedOrUpdated;
                        }
                    }
                }
                return true;
            }

        }.execute(infoImportante);
    }

    // ACTION SUR SQLITE DB
    /**
     * Get a list of InfoImportante from Sqlite database by infoImportante id.
     * @param id
     * @return list of infoImportante
     */
    public LiveData<InfoImportante> getInfoImportantesById(long id){
        return this.infoImportanteDao.selectInfoImportanteById(id);
    }

    /**
     * Get a list of InfoImportante from Sqlite database by code cis.
     * @param specialiteIdCodeCis
     * @return list of infoImportante
     */
    public LiveData<List<InfoImportante>> getInfoImportantesByCodeCis(long specialiteIdCodeCis){
        return this.infoImportanteDao.selectInfoImportanteByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert InfoImportante into Sqlite database.
     * @param infoImportante
     * @return
     */
    public long insertInfoImportante(InfoImportante infoImportante){
        return this.infoImportanteDao.insertInfoImportante(infoImportante);
    }

    /**
     * Update InfoImportante from Sqlite database
     * @param infoImportante
     * @return
     */
    public int updateInfoImportante (InfoImportante infoImportante){
        return this.infoImportanteDao.updateInfoImportante(infoImportante);
    }

    /**
     * Delete InfoImportante from Sqlite database
     * @param infoImportante
     * @return
     */
    public int deleteInfoImportante (InfoImportante infoImportante){
        return this.infoImportanteDao.deleteInfoImportante(infoImportante);
    }
}
