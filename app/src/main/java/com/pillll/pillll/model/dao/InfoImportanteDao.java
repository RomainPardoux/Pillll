package com.pillll.pillll.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pillll.pillll.model.entities.InfoImportante;
import java.util.List;

/**
 * DAO class used to access to info importante data using Room persistence library.
 * This class includes methods that offer abstract access to app database.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Dao
public interface InfoImportanteDao {

    @Query("SELECT * FROM InfoImportante WHERE id = :id")
    LiveData<InfoImportante> selectInfoImportanteById(long id);

    @Query("SELECT * FROM InfoImportante WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<InfoImportante>> selectInfoImportanteByCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertInfoImportante(InfoImportante infoImportante);

    @Insert
    void insertAll(List<InfoImportante> infoImportantes);

    @Update
    int updateInfoImportante(InfoImportante infoImportante);

    @Delete
    int deleteInfoImportante(InfoImportante infoImportante);
}
