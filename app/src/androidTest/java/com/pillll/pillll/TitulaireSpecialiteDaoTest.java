package com.pillll.pillll;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.pillll.pillll.model.converter.DateConverter;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.SpecialiteDao;
import com.pillll.pillll.model.dao.TitulaireSpecialiteDao;
import com.pillll.pillll.model.entities.Specialite;
import com.pillll.pillll.model.entities.TitulaireSpecialite;

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
public class TitulaireSpecialiteDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private SpecialiteDao specialiteDao;
    private TitulaireSpecialiteDao titulaireSpecialiteDao;
    // DATA SET FOR TEST
    private Long date_timestamp = Integer.toUnsignedLong(1515791376);
    private Date date_amm = DateConverter.fromTimestamp(date_timestamp);
    private Specialite specialite_test = new Specialite(61244396, "A 313 200 000 UI POUR CENT, pommade", "pommade", "Autorisation active", "Procédure nationale", date_amm, null, null, false, "Commercialisée" );
    private TitulaireSpecialite titulaireSpecialite_test = new TitulaireSpecialite("DENTSPLY", 61244396);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.pillllDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PillllDatabase.class)
                .allowMainThreadQueries()
                .build();
        this.specialiteDao = pillllDatabase.specialiteDao();
        this.titulaireSpecialiteDao = pillllDatabase.titulaireSpecialiteDao();
        this.specialiteDao.insertSpecialite(specialite_test);
        this.titulaireSpecialiteDao.insertTitulaireSpecialite(titulaireSpecialite_test);
    }

    @After
    public void closeDb() throws Exception{
        pillllDatabase.close();
    }

    @Test
    public void GetTitulaireSpecialiteById() throws InterruptedException{
        TitulaireSpecialite titulaireSpecialite = LiveDataTestUtil.getValue(this.titulaireSpecialiteDao.selectTitulaireSpecialiteById(1));
        Assert.assertEquals("DENTSPLY", titulaireSpecialite.getTitulaire());
    }

    @Test
    public void GetTitulaireSpecialitesByIdCodeCis() throws InterruptedException{
        List<TitulaireSpecialite> titulaireSpecialites = LiveDataTestUtil.getValue(this.titulaireSpecialiteDao.selectTitulaireSpecialiteByCodeCis(61244396));
        Assert.assertEquals("DENTSPLY", titulaireSpecialites.get(0).getTitulaire());
    }

    @Test
    public void DeleteTitulaireSpecialite() throws InterruptedException{
        TitulaireSpecialite titulaireSpecialite = LiveDataTestUtil.getValue(this.titulaireSpecialiteDao.selectTitulaireSpecialiteById(1));
        int deleteOk = this.titulaireSpecialiteDao.deleteTitulaireSpecialite(titulaireSpecialite);
        Assert.assertEquals(1, deleteOk);
   }

    @Test
    public void UpdateTitulaireSpecialite() throws InterruptedException{
        TitulaireSpecialite titulaireSpecialite = LiveDataTestUtil.getValue(this.titulaireSpecialiteDao.selectTitulaireSpecialiteById(1));
        titulaireSpecialite.setTitulaire("ALMIRALL");
        this.titulaireSpecialiteDao.updateTitulaireSpecialite(titulaireSpecialite);
        TitulaireSpecialite titulaireSpecialiteUpdated = LiveDataTestUtil.getValue(this.titulaireSpecialiteDao.selectTitulaireSpecialiteById(1));
        Assert.assertEquals("ALMIRALL", titulaireSpecialiteUpdated.getTitulaire());
    }
}
