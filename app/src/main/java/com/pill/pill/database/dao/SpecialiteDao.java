package com.pill.pill.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pill.pill.models.Specialite;

/**
 * Created by Pardoux Romain on 05/01/2019
 */

@Dao
public interface SpecialiteDao {

    @Query("SELECT * FROM Specialite WHERE id_code_cis = :idCodeCis")
    LiveData<Specialite> selectSpecialiteParId(long idCodeCis);

    @Insert
    long insertSpecialite (Specialite specialite);

    @Update
    int updateSpecialite (Specialite specialite);

    @Delete
    int deleteSpecialite (Specialite specialite);
}
