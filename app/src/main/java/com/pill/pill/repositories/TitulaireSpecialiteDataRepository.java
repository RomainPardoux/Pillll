package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;
import com.pill.pill.database.dao.TitulaireSpecialiteDao;
import com.pill.pill.database.entity.TitulaireSpecialite;
import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

public class TitulaireSpecialiteDataRepository {

    private final TitulaireSpecialiteDao titulaireSpecialiteDao;

    public TitulaireSpecialiteDataRepository(TitulaireSpecialiteDao titulaireSpecialiteDao) {
        this.titulaireSpecialiteDao = titulaireSpecialiteDao;
    }

    public LiveData<TitulaireSpecialite> selectTitulaireSpecialiteParId(long id){
        return this.titulaireSpecialiteDao.selectTitulaireSpecialiteParId(id);
    }

    public LiveData<List<TitulaireSpecialite>> selectTitulaireSpecialiteParCodeCis(long specialiteIdCodeCis){
        return this.titulaireSpecialiteDao.selectTitulaireSpecialiteParCodeCis(specialiteIdCodeCis);
    }

    public long insertTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite){
        return this.titulaireSpecialiteDao.insertTitulaireSpecialite(titulaireSpecialite);
    }

    public int updateTitulaireSpecialite (TitulaireSpecialite titulaireSpecialite){
        return this.titulaireSpecialiteDao.updateTitulaireSpecialite(titulaireSpecialite);
    }

    public int deleteTitulaireSpecialite (TitulaireSpecialite titulaireSpecialite){
        return this.titulaireSpecialiteDao.deleteTitulaireSpecialite(titulaireSpecialite);
    }
}
