package com.pillll.pillll.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pillll.pillll.database.entity.Specialite;

import java.util.List;

/**
 * Created by Pardoux Romain on 05/01/2019
 */

@Dao
public interface SpecialiteDao {

    @Query("SELECT * FROM Specialite WHERE id_code_cis = :idCodeCis")
    LiveData<Specialite> selectSpecialiteById(long idCodeCis);

    @Query("SELECT * FROM Specialite WHERE denomination = :denomination")
    LiveData<List<Specialite>> selectSpecialitesByDenomination(String denomination);

    @Query("SELECT * FROM Specialite INNER JOIN Presentation ON Specialite.id_code_cis = Presentation.specialite_id_code_cis WHERE Presentation.code_cip7 = :codeCip7")
    LiveData<Specialite> selectSpecialiteByCodeCip7(String codeCip7);

    @Query("SELECT * FROM Specialite INNER JOIN Presentation ON Specialite.id_code_cis = Presentation.specialite_id_code_cis WHERE Presentation.code_cip13 = :codeCip13")
    LiveData<Specialite> selectSpecialiteByCodeCip13(String codeCip13);

    @Insert
    long insertSpecialite (Specialite specialite);

    @Insert
    void insertAll(List<Specialite> specialites);

    @Update
    int updateSpecialite (Specialite specialite);

    @Delete
    int deleteSpecialite (Specialite specialite);
}
