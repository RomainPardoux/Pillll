package com.pillll.pillll.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.pillll.pillll.model.entities.SpecialiteEtRelations;

import java.util.List;

/**
 * DAO class used to access to specialite et relation data using Room persistence library.
 * This class includes methods that offer abstract access to app database.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Dao
public interface SpecialiteEtRelationsDao {

    @Query("SELECT * FROM Specialite WHERE id_code_cis = :idCodeCis")
    LiveData<SpecialiteEtRelations> selectSpecialiteEtRelationsByCodeCis(long idCodeCis);

    @Query("SELECT * FROM Specialite")
    LiveData<List<SpecialiteEtRelations>> selectAllSpecialitesEtRelations();

}
