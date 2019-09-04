package com.pillll.pillll.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pillll.pillll.database.entity.Generique;

import java.util.List;

/**
 * Created by Pardoux Romain on 08/01/2019
 */

@Dao
public interface GeneriqueDao {

    @Query("SELECT * FROM Generique WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<Generique> selectGeneriqueByCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertGenerique(Generique Generique);

    @Insert
    void insertAll(List<Generique> generiques);

    @Update
    int updateGenerique(Generique Generique);

    @Delete
    int deleteGenerique(Generique Generique);
}
