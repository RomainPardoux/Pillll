package com.pillll.pillll.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.PresentationDao;
import com.pillll.pillll.model.entities.Presentation;
import java.util.List;

/**
 * Repository class that abstract access to Presentation data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class PresentationDataRepository {

    private final PresentationDao presentationDao;

    public PresentationDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.presentationDao = db.presentationDao();
    }

    /**
     * Persist Presentation data from Sqlite model in AsyncTask.
     *
     * @param presentation
     */
    public void persistPresentation(Presentation presentation){

        new AsyncTask<Presentation, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Presentation... presentations) {
                boolean insertedOrUpdated = false;
                long inserted = 0;
                int updated;
                try {
                    inserted = insertPresentation(presentations[0]);
                    if (inserted != 0){
                        insertedOrUpdated = true;
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    if (inserted == 0){
                        try {
                            updated = updatePresentation(presentations[0]);
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

        }.execute(presentation);
    }

    /**
     * Get Presentation data from Sqlite model by presentation id.
     *
     * @param id
     * @return list of presentation
     */
    public LiveData<Presentation> getPresentationsById(long id) {
        return this.presentationDao.selectPresentationById(id);
    }

    /**
     * Get a list of Presentation from Sqlite model by code cis.
     *
     * @param specialiteIdCodeCis
     * @return list of presentation
     */
    public LiveData<List<Presentation>> getPresentationsByCodeCis(long specialiteIdCodeCis) {
        return this.presentationDao.selectPresentationByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Get Presentation data from Sqlite model by code cip 7.
     *
     * @param codeCip7
     * @return Presentation
     */
    public LiveData<Presentation> getPresentationByCodeCip7(String codeCip7) {
        return this.presentationDao.selectPresentationByCodeCip7(codeCip7);
    }

    /**
     * Get Presentation data from Sqlite model by code cip 13.
     *
     * @param codeCip13
     * @return Presentation
     */
    public LiveData<Presentation> getPresentationByCodeCip13(String codeCip13) {
        return this.presentationDao.selectPresentationByCodeCip13(codeCip13);
    }

    /**
     * Insert Presentation into Sqlite model.
     *
     * @param presentation
     * @return
     */
    public long insertPresentation(Presentation presentation) {
        return this.presentationDao.insertPresentation(presentation);
    }

    /**
     * Update Presentation from Sqlite model
     *
     * @param presentation
     * @return
     */
    public int updatePresentation(Presentation presentation) {
        return this.presentationDao.updatePresentation(presentation);
    }

    /**
     * Delete Presentation from Sqlite model
     *
     * @param presentation
     * @return
     */
    public int deletePresentation(Presentation presentation) {
        return this.presentationDao.deletePresentation(presentation);
    }

}
