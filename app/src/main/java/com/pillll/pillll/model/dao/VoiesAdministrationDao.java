package com.pillll.pillll.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.pillll.pillll.model.entities.VoiesAdministration;
import java.util.List;

/**
 * DAO class used to access to voies administration data using Room persistence library.
 * This class includes methods that offer abstract access to app database.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Dao
public interface VoiesAdministrationDao {

    @Query("SELECT * FROM VoiesAdministration WHERE id = :id")
    LiveData<VoiesAdministration> selectVoiesAdministrationById(long id);

    @Query("SELECT * FROM VoiesAdministration WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<VoiesAdministration>> selectVoiesAdministrationByCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertVoiesAdministration(VoiesAdministration VoiesAdministration);

    @Insert
    void insertAll(List<VoiesAdministration> voiesAdministrations);

    @Update
    int updateVoiesAdministration(VoiesAdministration VoiesAdministration);

    @Delete
    int deleteVoiesAdministration(VoiesAdministration VoiesAdministration);
}
