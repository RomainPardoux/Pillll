package com.pill.pill.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pill.pill.database.entity.LienCt;

/**
 * Created by Pardoux Romain on 08/01/2019
 */

@Dao
public interface LienCtDao {

    @Query("SELECT * FROM LienCt WHERE code_dossier_has = :codeDossierHas")
    LiveData<LienCt> selectLienCtParIdCodeDossierHas(String codeDossierHas);

    @Insert
    long insertLienCt(LienCt lienCt);

    @Update
    int updateLienCt(LienCt lienCt);

    @Delete
    int deleteLienCt(LienCt lienCt);
}
