package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;

import com.pill.pill.database.dao.ConditionPrescriptionDao;
import com.pill.pill.models.ConditionPrescription;

import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

public class ComditionPrescriptionDataRepository {

    private final ConditionPrescriptionDao conditionPrescriptionDao;

    public ComditionPrescriptionDataRepository(ConditionPrescriptionDao conditionPrescriptionDao) {
        this.conditionPrescriptionDao = conditionPrescriptionDao;
    }

    public LiveData<ConditionPrescription> selectConditionPrescriptionParId(long idCodeCis){
        return this.conditionPrescriptionDao.selectConditionPrescriptionParId(idCodeCis);
    }

    public LiveData<List<ConditionPrescription>> selectConditionPrescriptionParCodeCis(long specialiteIdCodeCis){
        return this.conditionPrescriptionDao.selectConditionPrescriptionParCodeCis(specialiteIdCodeCis);
    }

    public long insertConditionPrescription(ConditionPrescription conditionPrescription){
        return this.conditionPrescriptionDao.insertConditionPrescription(conditionPrescription);
    }

    public int updateConditionPrescription (ConditionPrescription conditionPrescription){
        return this.conditionPrescriptionDao.updateConditionPrescription(conditionPrescription);
    }

    public int deleteConditionPrescription (ConditionPrescription conditionPrescription){
        return this.conditionPrescriptionDao.deleteConditionPrescription(conditionPrescription);
    }
}
