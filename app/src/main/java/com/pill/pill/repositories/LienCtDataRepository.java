package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;

import com.pill.pill.database.dao.LienCtDao;
import com.pill.pill.models.LienCt;

import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

public class LienCtDataRepository {

    private final LienCtDao lienCtDao;

    public LienCtDataRepository(LienCtDao lienCtDao) {
        this.lienCtDao = lienCtDao;
    }

    public LiveData<LienCt> selectLienCtParIdCodeDossierHas(String idCodeHas){
        return this.lienCtDao.selectLienCtParIdCodeDossierHas(idCodeHas);
    }

    public long insertLienCt(LienCt lienCt){
        return this.lienCtDao.insertLienCt(lienCt);
    }

    public int updateLienCt (LienCt lienCt){
        return this.lienCtDao.updateLienCt(lienCt);
    }

    public int deleteLienCt (LienCt lienCt){
        return this.lienCtDao.deleteLienCt(lienCt);
    }
}
