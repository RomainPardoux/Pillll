package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;
import com.pill.pill.database.dao.EvaluationDao;
import com.pill.pill.models.Evaluation;
import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

public class EvaluationDataRepository {

    private final EvaluationDao evaluationDao;

    public EvaluationDataRepository(EvaluationDao evaluationDao) {
        this.evaluationDao = evaluationDao;
    }

    public LiveData<Evaluation> selectEvaluationParId(long id){
        return this.evaluationDao.selectEvaluationParId(id);
    }

    public LiveData<List<Evaluation>> selectEvaluationParCodeCis(long specialiteIdCodeCis){
        return this.evaluationDao.selectEvaluationParCodeCis(specialiteIdCodeCis);
    }

    public long insertEvaluation(Evaluation evaluation){
        return this.evaluationDao.insertEvaluation(evaluation);
    }

    public int updateEvaluation (Evaluation evaluation){
        return this.evaluationDao.updateEvaluation(evaluation);
    }

    public int deleteEvaluation (Evaluation evaluation){
        return this.evaluationDao.deleteEvaluation(evaluation);
    }
}
