package com.pillll.pillll;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.LienCtDao;
import com.pillll.pillll.model.entities.LienCt;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

@RunWith(AndroidJUnit4.class)
public class LienCtDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private LienCtDao lienCtDao;
    // DATA SET FOR TEST
    private LienCt lienCt_test = new LienCt("CT-17146", "https://www.has-sante.fr/portail/jcms/c_2882052");

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.pillllDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PillllDatabase.class)
                .allowMainThreadQueries()
                .build();
        this.lienCtDao = pillllDatabase.lienCtDao();
        this.lienCtDao.insertLienCt(lienCt_test);
    }

    @After
    public void closeDb() throws Exception{
        pillllDatabase.close();
    }

    @Test
    public void GetLienCtByIdCodeDossierHas() throws InterruptedException{
        LienCt lienCt = LiveDataTestUtil.getValue(this.lienCtDao.selectLienCtByIdCodeDossierHas("CT-17146"));
        Assert.assertEquals("https://www.has-sante.fr/portail/jcms/c_2882052", lienCt.getLienAvisCt());
    }

    @Test
    public void DeleteLienCt() throws InterruptedException{
        LienCt lienCt = LiveDataTestUtil.getValue(this.lienCtDao.selectLienCtByIdCodeDossierHas("CT-17146"));
        int deleteOk = this.lienCtDao.deleteLienCt(lienCt);
        Assert.assertEquals(1, deleteOk);
   }

    @Test
    public void UpdateLienCt() throws InterruptedException{
        LienCt lienCt = LiveDataTestUtil.getValue(this.lienCtDao.selectLienCtByIdCodeDossierHas("CT-17146"));
        lienCt.setLienAvisCt("https://www.has-sante.fr/portail/jcms/c_999999");
        this.lienCtDao.updateLienCt(lienCt);
        LienCt lienCtUpdated = LiveDataTestUtil.getValue(this.lienCtDao.selectLienCtByIdCodeDossierHas("CT-17146"));
        Assert.assertEquals("https://www.has-sante.fr/portail/jcms/c_999999", lienCtUpdated.getLienAvisCt());
    }
}
