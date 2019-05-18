package com.pill.pill.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pill.pill.database.entity.Composition;
import java.util.List;

/**
 * Created by Pardoux Romain on 08/01/2019
 */

@Dao
public interface CompositionDao {

    @Query("SELECT * FROM Composition WHERE id = :id")
    LiveData<Composition> selectCompositionParId(long id);

    @Query("SELECT * FROM Composition WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<Composition>> selectCompositionParCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertComposition(Composition composition);

    @Update
    int updateComposition(Composition composition);

    @Delete
    int deleteComposition(Composition composition);
}
