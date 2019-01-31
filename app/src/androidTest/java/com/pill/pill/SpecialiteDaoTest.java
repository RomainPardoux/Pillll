package com.pill.pill;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.pill.pill.database.DateConverter;
import com.pill.pill.database.PillllDatabase;
import com.pill.pill.database.dao.SpecialiteDao;
import com.pill.pill.models.Specialite;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.Date;

/**
 * Created by Pardoux Romain on 09/01/2019
 */

@RunWith(AndroidJUnit4.class)
public class SpecialiteDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private SpecialiteDao specialiteDao;
    // DATA SET FOR TEST
    private static Long date_timestamp = Integer.toUnsignedLong(1515791376);
    private static Date date_amm = DateConverter.fromTimestamp(date_timestamp);
    private static Specialite specialite_test = new Specialite(61266250, "A 313 200 000 UI POUR CENT, pommade", "pommade", "Autorisation active", "Procédure nationale", date_amm, null, null, false, "Commercialisée" );

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.pillllDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PillllDatabase.class)
                .allowMainThreadQueries()
                .build();
        this.specialiteDao = pillllDatabase.specialiteDao();
        this.specialiteDao.insertSpecialite(specialite_test);
    }

    @After
    public void closeDb() throws Exception{
        this.pillllDatabase.close();
    }

    @Test
    public void GetSpecialite() throws InterruptedException{
        Specialite specialite = LiveDataTestUtil.getValue(this.specialiteDao.selectSpecialiteParId(61266250));         Assert.assertEquals("A 313 200 000 UI POUR CENT, pommade", specialite.getDenomination());
    }

    @Test
    public void DeleteSpecialite() throws InterruptedException{
        int deleteOk = this.specialiteDao.deleteSpecialite(specialite_test);
        Assert.assertEquals(1, deleteOk);
    }

    @Test
    public void UpdateSpecialite() throws InterruptedException{
        Specialite specialite = LiveDataTestUtil.getValue(this.specialiteDao.selectSpecialiteParId(61266250));
        specialite.setDenomination("A 313 200 000 UI POUR CENT");
        this.specialiteDao.updateSpecialite(specialite);
        Specialite specialiteUpdated = LiveDataTestUtil.getValue(this.specialiteDao.selectSpecialiteParId(61266250));
        Assert.assertEquals("A 313 200 000 UI POUR CENT", specialiteUpdated.getDenomination());
    }
}
