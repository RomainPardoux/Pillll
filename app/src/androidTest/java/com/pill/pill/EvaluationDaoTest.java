package com.pill.pill;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.pill.pill.database.DateConverter;
import com.pill.pill.database.PillllDatabase;
import com.pill.pill.database.dao.EvaluationDao;
import com.pill.pill.database.dao.SpecialiteDao;
import com.pill.pill.models.Evaluation;
import com.pill.pill.models.Specialite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

@RunWith(AndroidJUnit4.class)
public class EvaluationDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private SpecialiteDao specialiteDao;
    private EvaluationDao evaluationDao;
    // DATA SET FOR TEST
    private Long date_timestamp = Integer.toUnsignedLong(1515791376);
    private Date date_amm = DateConverter.fromTimestamp(date_timestamp);
    private Specialite specialite_test = new Specialite(61244396, "A 313 200 000 UI POUR CENT, pommade", "pommade", "Autorisation active", "Procédure nationale", date_amm, null, null, false, "Commercialisée" );
    private Evaluation evaluation_test = new Evaluation(date_amm, "2", "3", "2", null, 61244396);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.pillllDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PillllDatabase.class)
                .allowMainThreadQueries()
                .build();
        this.specialiteDao = pillllDatabase.specialiteDao();
        this.evaluationDao = pillllDatabase.evaluationDao();
        this.specialiteDao.insertSpecialite(specialite_test);
        this.evaluationDao.insertEvaluation(evaluation_test);
    }

    @After
    public void closeDb() throws Exception{
        pillllDatabase.close();
    }

    @Test
    public void GetEvaluationById() throws InterruptedException{
        Evaluation evaluation = LiveDataTestUtil.getValue(this.evaluationDao.selectEvaluationParId(1));
        Assert.assertEquals("2", evaluation.getRisque());
    }

    @Test
    public void GetEvaluationsByIdCodeCis() throws InterruptedException{
        List<Evaluation> evaluations = LiveDataTestUtil.getValue(this.evaluationDao.selectEvaluationParCodeCis(61244396));
        Assert.assertEquals("2", evaluations.get(0).getRisque());
    }

    @Test
    public void DeleteEvaluation() throws InterruptedException{
        Evaluation evaluation = LiveDataTestUtil.getValue(this.evaluationDao.selectEvaluationParId(1));
        int deleteOk = this.evaluationDao.deleteEvaluation(evaluation);
        Assert.assertEquals(1, deleteOk);
   }

    @Test
    public void UpdateEvaluation() throws InterruptedException{
        Evaluation evaluation = LiveDataTestUtil.getValue(this.evaluationDao.selectEvaluationParId(1));
        evaluation.setRisque("3");
        this.evaluationDao.updateEvaluation(evaluation);
        Evaluation evaluationUpdated = LiveDataTestUtil.getValue(this.evaluationDao.selectEvaluationParId(1));
        Assert.assertEquals("3", evaluationUpdated.getRisque());
    }
}
