package com.pillll.pillll.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.dao.SpecialiteEtRelationsDao;
import com.pillll.pillll.database.entity.SpecialiteEtRelations;
import java.util.List;

/**
 * Repository class that abstract access to SpecialiteEtRelations data sources.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class SpecialiteEtRelationsDataRepository {

    private final SpecialiteEtRelationsDao specialiteEtRelationsDao;

    public SpecialiteEtRelationsDataRepository(Application application) {
        PillllDatabase db = PillllDatabase.getInstance(application);
        this.specialiteEtRelationsDao = db.specialiteEtRelationsDao();
    }

    // ACTION SUR SQLITE DB

    /**
     * Get SpecialiteEtRelations data from Sqlite database by code cis.
     *
     * @param specialiteIdCodeCis
     * @return liveData specialiteEtRelations
     */
    public LiveData<SpecialiteEtRelations> getSpecialiteEtRelationsFromSqliteByCodeCis(long specialiteIdCodeCis) {
        return this.specialiteEtRelationsDao.selectSpecialiteEtRelationsByCodeCis(specialiteIdCodeCis);
    }

    /**
     * Get a list of all SpecialiteEtRelations from Sqlite database.
     *
     * @return liveData List specialiteEtRelations
     */
    public LiveData<List<SpecialiteEtRelations>> getAllSpecialiteEtRelationsFromSqlite() {
        return this.specialiteEtRelationsDao.selectAllSpecialitesEtRelations();
    }
}
