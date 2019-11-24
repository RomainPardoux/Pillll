package com.pillll.pillll.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.pillll.pillll.model.entities.ConditionPrescription;
import java.util.List;

/**
 * DAO class used to access to condition prescription data using Room persistence library.
 * This class includes methods that offer abstract access to app database.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Dao
public interface ConditionPrescriptionDao {

    @Query("SELECT * FROM ConditionPrescription WHERE id = :id")
    LiveData<ConditionPrescription> selectConditionPrescriptionById(long id);

    @Query("SELECT * FROM ConditionPrescription WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<ConditionPrescription>> selectConditionPrescriptionByCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertConditionPrescription(ConditionPrescription conditionPrescription);

    @Insert
    void insertAll(List<ConditionPrescription> conditionPrescriptions);

    @Update
    int updateConditionPrescription(ConditionPrescription conditionPrescription);

    @Delete
    int deleteConditionPrescription(ConditionPrescription conditionPrescription);
}
