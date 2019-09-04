package com.pillll.pillll;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.dao.LienCtDao;
import com.pillll.pillll.database.dao.AsmrDao;
import com.pillll.pillll.database.dao.SpecialiteDao;
import com.pillll.pillll.database.entity.LienCt;
import com.pillll.pillll.database.entity.Asmr;
import com.pillll.pillll.database.entity.Specialite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by By Pardoux Romain on 18/01/2019
 */

@RunWith(AndroidJUnit4.class)
public class AsmrDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private LienCtDao lienCtDao;
    private AsmrDao asmrDao;
    private SpecialiteDao specialiteDao;
    // DATA SET FOR TEST
    private LienCt lienCt_test = new LienCt("CT-17146", "https://www.has-sante.fr/portail/jcms/c_2882052");
    private Asmr asmr_test = new Asmr("Renouvellement d'inscription (CT)", null, "Important", "Le service médical rendu par ROVALCYTE reste important dans les indications de l’AMM.","CT-17146", 60626471);
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
        this.asmrDao = pillllDatabase.asmrDao();
        this.specialiteDao = pillllDatabase.specialiteDao();
        this.specialiteDao.insertSpecialite(specialite_test);
        this.lienCtDao.insertLienCt(lienCt_test);
        this.asmrDao.insertAsmr(asmr_test);
    }

    @After
    public void closeDb() throws Exception{
        pillllDatabase.close();
    }

    @Test
    public void GetAsmrById() throws InterruptedException{
        Asmr asmr = LiveDataTestUtil.getValue(this.asmrDao.selectAsmrById(1));
        Assert.assertEquals("Important", asmr.getValeur());
    }

    @Test
    public void GetAsmrByIdCodeCis() throws InterruptedException{
        List<Asmr> asmr = LiveDataTestUtil.getValue(this.asmrDao.selectAsmrByCodeCis(60626471));
        Assert.assertEquals("Important", asmr.get(0).getValeur());
    }

    @Test
    public void DeleteAsmr() throws InterruptedException{
        Asmr asmr = LiveDataTestUtil.getValue(this.asmrDao.selectAsmrById(1));
        int deleteOk = this.asmrDao.deleteAsmr(asmr);
        Assert.assertEquals(1, deleteOk);
    }

    @Test
    public void UpdateAsmr() throws InterruptedException{
        Asmr asmr = LiveDataTestUtil.getValue(this.asmrDao.selectAsmrById(1));
        asmr.setValeur("Faible");
        this.asmrDao.updateAsmr(asmr);
        Asmr asmrUpdated = LiveDataTestUtil.getValue(this.asmrDao.selectAsmrById(1));
        Assert.assertEquals("Faible", asmrUpdated.getValeur());
    }
}
