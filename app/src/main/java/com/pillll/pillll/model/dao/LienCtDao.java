package com.pillll.pillll.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.pillll.pillll.model.entities.LienCt;

import java.util.List;

/**
 * DAO class used to access to lien ct data using Room persistence library.
 * This class includes methods that offer abstract access to app database.
 *
 * @author Romain Pardoux
 * @version 1.0
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
