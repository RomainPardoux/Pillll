package com.pillll.pillll.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pillll.pillll.database.entity.Smr;
import java.util.List;

/**
 * Created by Pardoux Romain on 08/01/2019
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
