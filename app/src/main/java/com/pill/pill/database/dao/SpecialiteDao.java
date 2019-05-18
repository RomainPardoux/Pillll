package com.pill.pill.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pill.pill.database.entity.Specialite;

/**
 * Created by Pardoux Romain on 05/01/2019
 */

@Dao
public interface SpecialiteDao {

    @Query("SELECT * FROM Specialite WHERE id_code_cis = :idCodeCis")
    LiveData<Specialite> selectSpecialiteParId(long idCodeCis);

    @Query("SELECT * FROM Specialite INNER JOIN Presentation ON Specialite.id_code_cis = Presentation.specialite_id_code_cis WHERE Presentation.code_cip7 = :codeCip7")
    LiveData<Specialite> selectSpecialiteParCodeCip7(String codeCip7);

    @Query("SELECT * FROM Specialite INNER JOIN Presentation ON Specialite.id_code_cis = Presentation.specialite_id_code_cis WHERE Presentation.code_cip13 = :codeCip13")
    LiveData<Specialite> selectSpecialiteParCodeCip13(String codeCip13);

    @Insert
    long insertSpecialite (Specialite specialite);

    @Update
    int updateSpecialite (Specialite specialite);

    @Delete
    int deleteSpecialite (Specialite specialite);
}
