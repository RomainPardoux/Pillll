package com.pillll.pillll.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.InfoImportanteDao;
import com.pillll.pillll.model.entities.InfoImportante;
import java.util.List;

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

    /**
     * Persist InfoImportante data in Sqlite model in AsyncTask.
     *
     * @param infoImportante
     */
    public void persistInfoImportante(InfoImportante infoImportante){

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

    /**
     * Get a list of InfoImportante from Sqlite model by infoImportante id.
     * @param id
     * @return list of infoImportante
     */
    public LiveData<InfoImportante> getInfoImportantesById(long id){
        return this.infoImportanteDao.selectInfoImportanteById(id);
    }

    /**
     * Get a list of InfoImportante from Sqlite model by code cis.
     * @param specialiteIdCodeCis
     * @return list of infoImportante
     */
    public LiveData<List<InfoImportante>> getInfoImportantesByCodeCis(long specialiteIdCodeCis){
        return this.infoImportanteDao.selectInfoImportanteByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert InfoImportante into Sqlite model.
     * @param infoImportante
     * @return
     */
    public long insertInfoImportante(InfoImportante infoImportante){
        return this.infoImportanteDao.insertInfoImportante(infoImportante);
    }

    /**
     * Update InfoImportante from Sqlite model
     * @param infoImportante
     * @return
     */
    public int updateInfoImportante (InfoImportante infoImportante){
        return this.infoImportanteDao.updateInfoImportante(infoImportante);
    }

    /**
     * Delete InfoImportante from Sqlite model
     * @param infoImportante
     * @return
     */
    public int deleteInfoImportante (InfoImportante infoImportante){
        return this.infoImportanteDao.deleteInfoImportante(infoImportante);
    }
}
