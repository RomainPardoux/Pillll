package com.pillll.pillll.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pillll.pillll.database.entity.LienCt;

import java.util.List;

/**
 * Created by Pardoux Romain on 08/01/2019
 */

@Dao
public interface LienCtDao {

    @Query("SELECT * FROM LienCt WHERE code_dossier_has = :codeDossierHas")
    LiveData<LienCt> selectLienCtByIdCodeDossierHas(String codeDossierHas);

    @Insert
    long insertLienCt(LienCt lienCt);

    @Insert
    void insertAll(List<LienCt> lienCts);

    @Update
    int updateLienCt(LienCt lienCt);

    @Delete
    int deleteLienCt(LienCt lienCt);
}
