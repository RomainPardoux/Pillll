package com.pillll.pillll;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.LienCtDao;
import com.pillll.pillll.model.dao.SmrDao;
import com.pillll.pillll.model.dao.SpecialiteDao;
import com.pillll.pillll.model.entities.LienCt;
import com.pillll.pillll.model.entities.Smr;
import com.pillll.pillll.model.entities.Specialite;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

@RunWith(AndroidJUnit4.class)
public class SmrDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private LienCtDao lienCtDao;
    private SmrDao smrDao;
    private SpecialiteDao specialiteDao;
    // DATA SET FOR TEST
    private LienCt lienCt_test = new LienCt("CT-17146", "https://www.has-sante.fr/portail/jcms/c_2882052");
    private Smr smr_test = new Smr("Renouvellement d'inscription (CT)", null, "Important", "Le service médical rendu par ROVALCYTE reste important dans les indications de l’AMM.","CT-17146", 60626471);
    private Specialite specialite_test = new Specialite(60626471, "A 313 200 000 UI POUR CENT, pommade", "pommade", "Autorisation active", "Procédure nationale", null, null, null, false, "Commercialisée" );

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.pillllDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PillllDatabase.class)
                .allowMainThreadQueries()
                .build();
        this.lienCtDao = pillllDatabase.lienCtDao();
        this.smrDao = pillllDatabase.smrDao();
        this.specialiteDao = pillllDatabase.specialiteDao();
        this.specialiteDao.insertSpecialite(specialite_test);
        this.lienCtDao.insertLienCt(lienCt_test);
        this.smrDao.insertSmr(smr_test);
    }

    @After
    public void closeDb() throws Exception{
        pillllDatabase.close();
    }

    @Test
    public void GetSmrById() throws InterruptedException{
        Smr smr = LiveDataTestUtil.getValue(this.smrDao.selectSmrById(1));
        Assert.assertEquals("Important", smr.getValeur());
    }

    @Test
    public void GetSmrByIdCodeCis() throws InterruptedException{
        List<Smr> smr = LiveDataTestUtil.getValue(this.smrDao.selectSmrByCodeCis(60626471));
        Assert.assertEquals("Important", smr.get(0).getValeur());
    }

    @Test
    public void DeleteSmr() throws InterruptedException{
        Smr smr = LiveDataTestUtil.getValue(this.smrDao.selectSmrById(1));
        int deleteOk = this.smrDao.deleteSmr(smr);
        Assert.assertEquals(1, deleteOk);
    }

    @Test
    public void UpdateSmr() throws InterruptedException{
        Smr smr = LiveDataTestUtil.getValue(this.smrDao.selectSmrById(1));
        smr.setValeur("Faible");
        this.smrDao.updateSmr(smr);
        Smr smrUpdated = LiveDataTestUtil.getValue(this.smrDao.selectSmrById(1));
        Assert.assertEquals("Faible", smrUpdated.getValeur());
    }
}
