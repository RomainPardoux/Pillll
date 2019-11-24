package com.pillll.pillll.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.CompositionDao;
import com.pillll.pillll.model.entities.Composition;
import java.util.List;

/**
 * Repository class that abstract access to Composition data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class CompositionDataRepository {

    private final CompositionDao compositionDao;

    public CompositionDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.compositionDao = db.compositionDao();
    }

    /**
     * Persist Composition data in Sqlite model in AsyncTask.
     *
     * @param composition
     */
    public void persistComposition(Composition composition){

        new AsyncTask<Composition, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Composition... compositions) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertComposition(compositions[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updateComposition(compositions[0]);
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

        }.execute(composition);
    }

    /**
     * Get a list of Composition from Sqlite model by composition id.
     * @param id
     * @return list of composition
     */
    public LiveData<Composition> getCompositionById(long id){
        return this.compositionDao.selectCompositionById(id);
    }

    /**
     * Get a list of Composition from Sqlite model by code cis.
     * @param specialiteIdCodeCis
     * @return list of composition
     */
    public LiveData<List<Composition>> getCompositionsByCodeCis(long specialiteIdCodeCis){
        return this.compositionDao.selectCompositionByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Insert Composition into Sqlite model.
     * @param composition
     * @return
     */
    public long insertComposition(Composition composition){
        return this.compositionDao.insertComposition(composition);
    }

    /**
     * Update Composition from Sqlite model
     * @param composition
     * @return
     */
    public int updateComposition (Composition composition){
        return this.compositionDao.updateComposition(composition);
    }

    /**
     * Delete Composition from Sqlite model
     * @param composition
     * @return
     */
    public int deleteComposition (Composition composition){
        return this.compositionDao.deleteComposition(composition);
    }
}
