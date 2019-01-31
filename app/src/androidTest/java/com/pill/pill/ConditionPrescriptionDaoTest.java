package com.pill.pill;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.pill.pill.database.DateConverter;
import com.pill.pill.database.PillllDatabase;
import com.pill.pill.database.dao.ConditionPrescriptionDao;
import com.pill.pill.database.dao.SpecialiteDao;
import com.pill.pill.models.ConditionPrescription;
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
 * Created by Pardoux Romain on 09/01/2019
 */

@RunWith(AndroidJUnit4.class)
public class ConditionPrescriptionDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private SpecialiteDao specialiteDao;
    private ConditionPrescriptionDao conditionPrescriptionDao;
    // DATA SET FOR TEST
    private Long date_timestamp = Integer.toUnsignedLong(1515791376);
    private Date date_amm = DateConverter.fromTimestamp(date_timestamp);
    private Specialite specialite_test = new Specialite(61244396, "A 313 200 000 UI POUR CENT, pommade", "pommade", "Autorisation active", "Procédure nationale", date_amm, null, null, false, "Commercialisée" );
    private ConditionPrescription conditionPrescription_test = new ConditionPrescription("réservé à l'usage professionnel DENTAIRE", 61244396);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.pillllDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PillllDatabase.class)
                .allowMainThreadQueries()
                .build();
        this.specialiteDao = pillllDatabase.specialiteDao();
        this.conditionPrescriptionDao = pillllDatabase.conditionPrescriptionDao();
        this.specialiteDao.insertSpecialite(specialite_test);
        this.conditionPrescriptionDao.insertConditionPrescription(conditionPrescription_test);
    }

    @After
    public void closeDb() throws Exception{
        pillllDatabase.close();
    }

    @Test
    public void GetConditionPrescriptionById() throws InterruptedException{
        ConditionPrescription conditionPrescription = LiveDataTestUtil.getValue(this.conditionPrescriptionDao.selectConditionPrescriptionParId(1));
        Assert.assertEquals("réservé à l'usage professionnel DENTAIRE", conditionPrescription.getConditionPrescription());
    }

    @Test
    public void GetConditionPrescriptionsByIdCodeCis() throws InterruptedException{
        List<ConditionPrescription> conditionPrescriptions = LiveDataTestUtil.getValue(this.conditionPrescriptionDao.selectConditionPrescriptionParCodeCis(61244396));
        Assert.assertEquals("réservé à l'usage professionnel DENTAIRE", conditionPrescriptions.get(0).getConditionPrescription());
    }

    @Test
    public void DeleteConditionPrescription() throws InterruptedException{
        ConditionPrescription conditionPrescription = LiveDataTestUtil.getValue(this.conditionPrescriptionDao.selectConditionPrescriptionParId(1));
        int deleteOk = this.conditionPrescriptionDao.deleteConditionPrescription(conditionPrescription);
        Assert.assertEquals(1, deleteOk);
   }

    @Test
    public void UpdateConditionPrescription() throws InterruptedException{
        ConditionPrescription conditionPrescription = LiveDataTestUtil.getValue(this.conditionPrescriptionDao.selectConditionPrescriptionParId(1));
        conditionPrescription.setConditionPrescription("réservé à l'usage HOSPITALIER");
        this.conditionPrescriptionDao.updateConditionPrescription(conditionPrescription);
        ConditionPrescription conditionPrescriptionUpdated = LiveDataTestUtil.getValue(this.conditionPrescriptionDao.selectConditionPrescriptionParId(1));
        Assert.assertEquals("réservé à l'usage HOSPITALIER", conditionPrescriptionUpdated.getConditionPrescription());
    }
}
