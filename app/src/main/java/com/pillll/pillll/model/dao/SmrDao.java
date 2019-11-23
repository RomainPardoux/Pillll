package com.pillll.pillll.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pillll.pillll.model.entities.Smr;
import java.util.List;

/**
 * DAO class used to access to smr data using Room persistence library.
 * This class includes methods that offer abstract access to app database.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Dao
public interface SmrDao {

    @Query("SELECT * FROM Smr WHERE id = :id")
    LiveData<Smr> selectSmrById(long id);

    @Query("SELECT * FROM Smr WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<Smr>> selectSmrByCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertSmr(Smr smr);

    @Insert
    void insertAll(List<Smr> smrs);

    @Update
    int updateSmr(Smr smr);

    @Delete
    int deleteSmr(Smr smr);
}
