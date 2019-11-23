package com.pillll.pillll.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pillll.pillll.model.entities.Composition;
import java.util.List;

/**
 * DAO class used to access to composition data using Room persistence library.
 * This class includes methods that offer abstract access to app database.
 *
 * @author Romain Pardoux
 * @version 1.0
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
