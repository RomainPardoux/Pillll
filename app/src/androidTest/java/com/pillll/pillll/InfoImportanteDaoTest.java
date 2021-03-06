package com.pillll.pillll;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.pillll.pillll.model.converter.DateConverter;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.SpecialiteDao;
import com.pillll.pillll.model.dao.InfoImportanteDao;
import com.pillll.pillll.model.entities.Specialite;
import com.pillll.pillll.model.entities.InfoImportante;

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
public class InfoImportanteDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private SpecialiteDao specialiteDao;
    private InfoImportanteDao infoImportanteDao;
    // DATA SET FOR TEST
    private Long date_timestamp = Integer.toUnsignedLong(1515791376);
    private Date date_amm = DateConverter.fromTimestamp(date_timestamp);
    private Specialite specialite_test = new Specialite(61244396, "A 313 200 000 UI POUR CENT, pommade", "pommade", "Autorisation active", "Procédure nationale", date_amm, null, null, false, "Commercialisée" );
    private InfoImportante infoImportante_test = new InfoImportante(date_amm, date_amm, "Pas d info importante", 61244396);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.pillllDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PillllDatabase.class)
                .allowMainThreadQueries()
                .build();
        this.specialiteDao = pillllDatabase.specialiteDao();
        this.infoImportanteDao = pillllDatabase.infoImportanteDao();
        this.specialiteDao.insertSpecialite(specialite_test);
        this.infoImportanteDao.insertInfoImportante(infoImportante_test);
    }

    @After
    public void closeDb() throws Exception{
        pillllDatabase.close();
    }

    @Test
    public void GetInfoImportanteById() throws InterruptedException{
        InfoImportante infoImportante = LiveDataTestUtil.getValue(this.infoImportanteDao.selectInfoImportanteById(1));
        Assert.assertEquals("Pas d info importante", infoImportante.getDescription());
    }

    @Test
    public void GetInfoImportantesByIdCodeCis() throws InterruptedException{
        List<InfoImportante> infoImportantes = LiveDataTestUtil.getValue(this.infoImportanteDao.selectInfoImportanteByCodeCis(61244396));
        Assert.assertEquals("Pas d info importante", infoImportantes.get(0).getDescription());
    }

    @Test
    public void DeleteInfoImportante() throws InterruptedException{
        InfoImportante infoImportante = LiveDataTestUtil.getValue(this.infoImportanteDao.selectInfoImportanteById(1));
        int deleteOk = this.infoImportanteDao.deleteInfoImportante(infoImportante);
        Assert.assertEquals(1, deleteOk);
   }

    @Test
    public void UpdateInfoImportante() throws InterruptedException{
        InfoImportante infoImportante = LiveDataTestUtil.getValue(this.infoImportanteDao.selectInfoImportanteById(1));
        infoImportante.setDescription("Attention au surdosage");
        this.infoImportanteDao.updateInfoImportante(infoImportante);
        InfoImportante infoImportanteUpdated = LiveDataTestUtil.getValue(this.infoImportanteDao.selectInfoImportanteById(1));
        Assert.assertEquals("Attention au surdosage", infoImportanteUpdated.getDescription());
    }
}
