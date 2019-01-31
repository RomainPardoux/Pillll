package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;
import com.pill.pill.database.dao.SmrDao;
import com.pill.pill.models.Smr;
import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

public class SmrDataRepository {

    private final SmrDao smrDao;

    public SmrDataRepository(SmrDao smrDao) {
        this.smrDao = smrDao;
    }

    public LiveData<Smr> selectSmrParId(long id){
        return this.smrDao.selectSmrParId(id);
    }

    public LiveData<List<Smr>> selectSmrParCodeCis(long specialiteIdCodeCis){
        return this.smrDao.selectSmrParCodeCis(specialiteIdCodeCis);
    }

    public long insertSmr(Smr smr){
        return this.smrDao.insertSmr(smr);
    }

    public int updateSmr (Smr smr){
        return this.smrDao.updateSmr(smr);
    }

    public int deleteSmr (Smr smr){
        return this.smrDao.deleteSmr(smr);
    }
}
