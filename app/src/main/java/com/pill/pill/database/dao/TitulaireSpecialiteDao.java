package com.pill.pill.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pill.pill.database.entity.TitulaireSpecialite;
import java.util.List;

/**
 * Created by Pardoux Romain on 08/01/2019
 */

@Dao
public interface TitulaireSpecialiteDao {

    @Query("SELECT * FROM TitulaireSpecialite WHERE id = :id")
    LiveData<TitulaireSpecialite> selectTitulaireSpecialiteParId(long id);

    @Query("SELECT * FROM TitulaireSpecialite WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<TitulaireSpecialite>> selectTitulaireSpecialiteParCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite);

    @Insert
    void insertAll(List<TitulaireSpecialite> titulaireSpecialites);

    @Update
    int updateTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite);

    @Delete
    int deleteTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite);
}
