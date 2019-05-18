package com.pill.pill.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pill.pill.database.entity.Evaluation;
import java.util.List;

/**
 * Created by Pardoux Romain on 08/01/2019
 */

@Dao
public interface EvaluationDao {

    @Query("SELECT * FROM Evaluation WHERE id = :id")
    LiveData<Evaluation> selectEvaluationParId(long id);

    @Query("SELECT * FROM Evaluation WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<Evaluation>> selectEvaluationParCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertEvaluation(Evaluation evaluation);

    @Update
    int updateEvaluation(Evaluation evaluation);

    @Delete
    int deleteEvaluation(Evaluation evaluation);
}
