package com.pillll.pillll.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pillll.pillll.database.entity.Composition;
import java.util.List;

/**
 * Created by Pardoux Romain on 08/01/2019
 */

@Dao
public interface CompositionDao {

    @Query("SELECT * FROM Composition WHERE id = :id")
    LiveData<Composition> selectCompositionById(long id);

    @Query("SELECT * FROM Composition WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<Composition>> selectCompositionByCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertComposition(Composition composition);

    @Insert
    void insertAll(List<Composition> compositions);

    @Update
    int updateComposition(Composition composition);

    @Delete
    int deleteComposition(Composition composition);
}
