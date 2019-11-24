package com.pillll.pillll.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.pillll.pillll.model.entities.Presentation;
import java.util.List;

/**
 * DAO class used to access to presentation data using Room persistence library.
 * This class includes methods that offer abstract access to app database.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Dao
public interface PresentationDao {

    @Query("SELECT * FROM Presentation WHERE id = :id")
    LiveData<Presentation> selectPresentationById(long id);

    @Query("SELECT * FROM Presentation WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<Presentation>> selectPresentationByCodeCis(long specialiteIdCodeCis);

    @Query("SELECT * FROM Presentation WHERE code_cip7 = :codeCip7")
    LiveData<Presentation> selectPresentationByCodeCip7(String codeCip7);

    @Query("SELECT * FROM Presentation WHERE code_cip13 = :codeCip13")
    LiveData<Presentation> selectPresentationByCodeCip13(String codeCip13);

    @Insert
    long insertPresentation(Presentation presentation);

    @Insert
    void insertAll(List<Presentation> presentations);

    @Update
    int updatePresentation(Presentation presentation);

    @Delete
    int deletePresentation(Presentation presentation);
}
