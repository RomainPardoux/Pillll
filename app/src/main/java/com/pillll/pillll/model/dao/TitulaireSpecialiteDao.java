package com.pillll.pillll.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
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
