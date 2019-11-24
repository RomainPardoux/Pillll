package com.pillll.pillll.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.pillll.pillll.model.entities.Generique;

import java.util.List;

/**
 * DAO class used to access to generique data using Room persistence library.
 * This class includes methods that offer abstract access to app database.
 *
 * @author Romain Pardoux
 * @version 1.0
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
