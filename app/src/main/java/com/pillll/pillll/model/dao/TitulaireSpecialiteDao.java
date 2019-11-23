package com.pillll.pillll.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pillll.pillll.model.entities.TitulaireSpecialite;
import java.util.List;

/**
 * DAO class used to access to titulaire specialite data using Room persistence library.
 * This class includes methods that offer abstract access to app database.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Dao
public interface TitulaireSpecialiteDao {

    @Query("SELECT * FROM TitulaireSpecialite WHERE id = :id")
    LiveData<TitulaireSpecialite> selectTitulaireSpecialiteById(long id);

    @Query("SELECT * FROM TitulaireSpecialite WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<TitulaireSpecialite>> selectTitulaireSpecialiteByCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite);

    @Insert
    void insertAll(List<TitulaireSpecialite> titulaireSpecialites);

    @Update
    int updateTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite);

    @Delete
    int deleteTitulaireSpecialite(TitulaireSpecialite titulaireSpecialite);
}
