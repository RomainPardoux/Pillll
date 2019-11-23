package com.pillll.pillll.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
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
