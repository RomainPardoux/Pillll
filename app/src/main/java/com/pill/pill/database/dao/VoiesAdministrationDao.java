package com.pill.pill.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pill.pill.database.entity.VoiesAdministration;
import java.util.List;

/**
 * Created by Pardoux Romain on 08/01/2019
 */

@Dao
public interface VoiesAdministrationDao {

    @Query("SELECT * FROM VoiesAdministration WHERE id = :id")
    LiveData<VoiesAdministration> selectVoiesAdministrationParId(long id);

    @Query("SELECT * FROM VoiesAdministration WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<VoiesAdministration>> selectVoiesAdministrationParCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertVoiesAdministration(VoiesAdministration VoiesAdministration);

    @Update
    int updateVoiesAdministration(VoiesAdministration VoiesAdministration);

    @Delete
    int deleteVoiesAdministration(VoiesAdministration VoiesAdministration);
}
