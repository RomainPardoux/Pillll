package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;
import com.pill.pill.database.dao.AsmrDao;
import com.pill.pill.models.Asmr;
import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

public class AsmrDataRepository {

    private final AsmrDao asmrDao;

    public AsmrDataRepository(AsmrDao asmrDao) {
        this.asmrDao = asmrDao;
    }

    public LiveData<Asmr> selectAsmrParId(long idCodeCis){
        return this.asmrDao.selectAsmrParId(idCodeCis);
    }

    public LiveData<List<Asmr>> selectAsmrParCodeCis(long specialiteIdCodeCis){
        return this.asmrDao.selectAsmrParCodeCis(specialiteIdCodeCis);
    }

    public long insertAsmr(Asmr asmr){
        return this.asmrDao.insertAsmr(asmr);
    }

    public int updateAsmr (Asmr asmr){
        return this.asmrDao.updateAsmr(asmr);
    }

    public int deleteAsmr (Asmr asmr){
        return this.asmrDao.deleteAsmr(asmr);
    }
}
