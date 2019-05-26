package com.pill.pill.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pill.pill.database.entity.ConditionPrescription;
import java.util.List;

/**
 * Created by Pardoux Romain on 08/01/2019
 */

@Dao
public interface ConditionPrescriptionDao {

    @Query("SELECT * FROM ConditionPrescription WHERE id = :id")
    LiveData<ConditionPrescription> selectConditionPrescriptionParId(long id);

    @Query("SELECT * FROM ConditionPrescription WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<ConditionPrescription>> selectConditionPrescriptionParCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertConditionPrescription(ConditionPrescription conditionPrescription);

    @Insert
    void insertAll(List<ConditionPrescription> conditionPrescriptions);

    @Update
    int updateConditionPrescription(ConditionPrescription conditionPrescription);

    @Delete
    int deleteConditionPrescription(ConditionPrescription conditionPrescription);
}
