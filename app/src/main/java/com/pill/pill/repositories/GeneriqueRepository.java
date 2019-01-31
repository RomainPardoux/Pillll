package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;
import com.pill.pill.database.dao.GeneriqueDao;
import com.pill.pill.models.Generique;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

public class GeneriqueRepository {

    private final GeneriqueDao generiqueDao;

    public GeneriqueRepository(GeneriqueDao generiqueDao) {
        this.generiqueDao = generiqueDao;
    }

    public LiveData<Generique> selectGeneriqueParCodeCis(long specialiteIdCodeCis){
        return this.generiqueDao.selectGeneriqueParCodeCis(specialiteIdCodeCis);
    }

    public long insertGenerique(Generique generique){
        return this.generiqueDao.insertGenerique(generique);
    }

    public int updateGenerique (Generique generique){
        return this.generiqueDao.updateGenerique(generique);
    }

    public int deleteGenerique (Generique generique){
        return this.generiqueDao.deleteGenerique(generique);
    }
}
