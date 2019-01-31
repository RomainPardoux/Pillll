package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;
import com.pill.pill.database.dao.SpecialiteDao;
import com.pill.pill.models.Specialite;

public class SpecialiteDataRepository {

    private final SpecialiteDao specialiteDao;

    public SpecialiteDataRepository(SpecialiteDao specialiteDao) {
        this.specialiteDao = specialiteDao;
    }

    public LiveData<Specialite> selectSpecialiteParId(long idCodeCis){
        return this.specialiteDao.selectSpecialiteParId(idCodeCis);
    }

    public long insertSpecialite (Specialite specialite){
        return this.specialiteDao.insertSpecialite(specialite);
    }

    public int updateSpecialite (Specialite specialite){
        return this.specialiteDao.updateSpecialite(specialite);
    }

    public int deleteSpecialite (Specialite specialite){
        return this.specialiteDao.deleteSpecialite(specialite);
    }
}
