package com.pill.pill;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.pill.pill.database.converter.DateConverter;
import com.pill.pill.database.PillllDatabase;
import com.pill.pill.database.dao.GeneriqueDao;
import com.pill.pill.database.dao.SpecialiteDao;
import com.pill.pill.database.entity.Generique;
import com.pill.pill.database.entity.Specialite;
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
public class GeneriqueDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private SpecialiteDao specialiteDao;
    private GeneriqueDao generiqueDao;
    // DATA SET FOR TEST
    private Long date_timestamp = Integer.toUnsignedLong(1515791376);
    private Date date_amm = DateConverter.fromTimestamp(date_timestamp);
    private Specialite specialite_test = new Specialite(61266250, "A 313 200 000 UI POUR CENT, pommade", "pommade", "Autorisation active", "Procédure nationale", date_amm, null, null, false, "Commercialisée" );
    private Generique generique_test = new Generique(61266250,  "1", "CIMETIDINE 200 mg - TAGAMET 200 mg, comprimé pelliculé", "0", "1");

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.pillllDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PillllDatabase.class)
                .allowMainThreadQueries()
                .build();
        this.specialiteDao = pillllDatabase.specialiteDao();
        this.generiqueDao = pillllDatabase.generiqueDao();
        this.specialiteDao.insertSpecialite(specialite_test);
        this.generiqueDao.insertGenerique(generique_test);
    }

    @After
    public void closeDb() throws Exception{
        pillllDatabase.close();
    }

    @Test
    public void GetGenerique() throws InterruptedException{
        Generique generique = LiveDataTestUtil.getValue(this.generiqueDao.selectGeneriqueParCodeCis(61266250));
        Assert.assertEquals("CIMETIDINE 200 mg - TAGAMET 200 mg, comprimé pelliculé", generique.getLibelleGroupe());
    }

    @Test
    public void DeleteGenerique() throws InterruptedException{
        int deleteOk = this.generiqueDao.deleteGenerique(generique_test);
        Assert.assertEquals(1, deleteOk);
   }

    @Test
    public void UpdateGenerique() throws InterruptedException{
        Generique generique = LiveDataTestUtil.getValue(this.generiqueDao.selectGeneriqueParCodeCis(61266250));
        generique.setLibelleGroupe("CIMETIDINE 200 mg - TAGAMET 200 mg");
        this.generiqueDao.updateGenerique(generique);
        Generique generiqueUpdated = LiveDataTestUtil.getValue(this.generiqueDao.selectGeneriqueParCodeCis(61266250));
        Assert.assertEquals("CIMETIDINE 200 mg - TAGAMET 200 mg", generiqueUpdated.getLibelleGroupe());
    }
}
