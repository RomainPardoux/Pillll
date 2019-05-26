package com.pill.pill.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pill.pill.database.entity.Presentation;
import java.util.List;

/**
 * Created by Pardoux Romain on 08/01/2019
 */

@Dao
public interface PresentationDao {

    @Query("SELECT * FROM Presentation WHERE id = :id")
    LiveData<Presentation> selectPresentationParId(long id);

    @Query("SELECT * FROM Presentation WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<Presentation>> selectPresentationParCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertPresentation(Presentation presentation);

    @Insert
    void insertAll(List<Presentation> presentations);

    @Update
    int updatePresentation(Presentation presentation);

    @Delete
    int deletePresentation(Presentation presentation);
}
