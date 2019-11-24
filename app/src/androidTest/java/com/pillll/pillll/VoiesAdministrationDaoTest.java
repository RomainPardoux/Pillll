package com.pillll.pillll;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.pillll.pillll.model.converter.DateConverter;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.VoiesAdministrationDao;
import com.pillll.pillll.model.dao.SpecialiteDao;
import com.pillll.pillll.model.entities.VoiesAdministration;
import com.pillll.pillll.model.entities.Specialite;

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
public class VoiesAdministrationDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private SpecialiteDao specialiteDao;
    private VoiesAdministrationDao voiesAdministrationDao;
    // DATA SET FOR TEST
    private Long date_timestamp = Integer.toUnsignedLong(1515791376);
    private Date date_amm = DateConverter.fromTimestamp(date_timestamp);
    private Specialite specialite_test = new Specialite(61244396, "A 313 200 000 UI POUR CENT, pommade", "pommade", "Autorisation active", "Procédure nationale", date_amm, null, null, false, "Commercialisée" );
    private VoiesAdministration voiesAdministration_test = new VoiesAdministration("dentaire", 61244396);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.pillllDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PillllDatabase.class)
                .allowMainThreadQueries()
                .build();
        this.specialiteDao = pillllDatabase.specialiteDao();
        this.voiesAdministrationDao = pillllDatabase.voiesAdministrationDao();
        this.specialiteDao.insertSpecialite(specialite_test);
        this.voiesAdministrationDao.insertVoiesAdministration(voiesAdministration_test);
    }

    @After
    public void closeDb() throws Exception{
        pillllDatabase.close();
    }

    @Test
    public void GetVoiesAdministrationById() throws InterruptedException{
        VoiesAdministration voiesAdministration = LiveDataTestUtil.getValue(this.voiesAdministrationDao.selectVoiesAdministrationById(1));
        Assert.assertEquals("dentaire", voiesAdministration.getVoiesAdministration());
    }

    @Test
    public void GetVoiesAdministrationsByIdCodeCis() throws InterruptedException{
        List<VoiesAdministration> voiesAdministrations = LiveDataTestUtil.getValue(this.voiesAdministrationDao.selectVoiesAdministrationByCodeCis(61244396));
        Assert.assertEquals("dentaire", voiesAdministrations.get(0).getVoiesAdministration());
    }

    @Test
    public void DeleteVoiesAdministration() throws InterruptedException{
        VoiesAdministration voiesAdministration = LiveDataTestUtil.getValue(this.voiesAdministrationDao.selectVoiesAdministrationById(1));
        int deleteOk = this.voiesAdministrationDao.deleteVoiesAdministration(voiesAdministration);
        Assert.assertEquals(1, deleteOk);
   }

    @Test
    public void UpdateVoiesAdministration() throws InterruptedException{
        VoiesAdministration voiesAdministration = LiveDataTestUtil.getValue(this.voiesAdministrationDao.selectVoiesAdministrationById(1));
        voiesAdministration.setVoiesAdministration("orale");
        this.voiesAdministrationDao.updateVoiesAdministration(voiesAdministration);
        VoiesAdministration voiesAdministrationUpdated = LiveDataTestUtil.getValue(this.voiesAdministrationDao.selectVoiesAdministrationById(1));
        Assert.assertEquals("orale", voiesAdministrationUpdated.getVoiesAdministration());
    }
}
