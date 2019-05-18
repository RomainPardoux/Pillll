package com.pill.pill.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pill.pill.database.entity.Generique;

/**
 * Created by Pardoux Romain on 08/01/2019
 */

@Dao
public interface GeneriqueDao {

    @Query("SELECT * FROM Generique WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<Generique> selectGeneriqueParCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertGenerique(Generique Generique);

    @Update
    int updateGenerique(Generique Generique);

    @Delete
    int deleteGenerique(Generique Generique);
}
