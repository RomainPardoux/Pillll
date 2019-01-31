package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;
import com.pill.pill.database.dao.InfoImportanteDao;
import com.pill.pill.models.InfoImportante;
import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

public class InfoImportanteDataRepository {

    private final InfoImportanteDao infoImportanteDao;

    public InfoImportanteDataRepository(InfoImportanteDao infoImportanteDao) {
        this.infoImportanteDao = infoImportanteDao;
    }

    public LiveData<InfoImportante> selectInfoImportanteParId(long idCodeCis){
        return this.infoImportanteDao.selectInfoImportanteParId(idCodeCis);
    }

    public LiveData<List<InfoImportante>> selectInfoImportanteParCodeCis(long specialiteIdCodeCis){
        return this.infoImportanteDao.selectInfoImportanteParCodeCis(specialiteIdCodeCis);
    }

    public long insertInfoImportante(InfoImportante infoImportante){
        return this.infoImportanteDao.insertInfoImportante(infoImportante);
    }

    public int updateInfoImportante (InfoImportante infoImportante){
        return this.infoImportanteDao.updateInfoImportante(infoImportante);
    }

    public int deleteInfoImportante (InfoImportante infoImportante){
        return this.infoImportanteDao.deleteInfoImportante(infoImportante);
    }
}
