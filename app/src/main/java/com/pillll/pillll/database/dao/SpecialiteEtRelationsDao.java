package com.pillll.pillll.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import com.pillll.pillll.database.entity.SpecialiteEtRelations;

import java.util.List;

/**
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
