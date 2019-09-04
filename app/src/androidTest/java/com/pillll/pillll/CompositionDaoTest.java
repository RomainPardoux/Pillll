package com.pillll.pillll;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.pillll.pillll.database.converter.DateConverter;
import com.pillll.pillll.database.PillllDatabase;
import com.pillll.pillll.database.dao.CompositionDao;
import com.pillll.pillll.database.dao.SpecialiteDao;
import com.pillll.pillll.database.entity.Composition;
import com.pillll.pillll.database.entity.Specialite;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.Date;
import java.util.List;

/**
 * Created by Bydoux Romain on 09/01/2019
 */

@RunWith(AndroidJUnit4.class)
public class CompositionDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private SpecialiteDao specialiteDao;
    private CompositionDao compositionDao;
    // DATA SET FOR TEST
    private Long date_timestamp = Integer.toUnsignedLong(1515791376);
    private Date date_amm = DateConverter.fromTimestamp(date_timestamp);
    private Specialite specialite_test = new Specialite(61266250, "A 313 200 000 UI POUR CENT, pommade", "pommade", "Autorisation active", "Procédure nationale", date_amm, null, null, false, "Commercialisée" );
    private Composition composition_test = new Composition( "pommade", "77887", "CONCENTRAT DE VITAMINE A SYNTHÉTIQUE, FORME HUILEUSE", "200 000 UI", "100 g de pommade", "SA", 1, 61266250);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.pillllDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PillllDatabase.class)
                .allowMainThreadQueries()
                .build();
        this.specialiteDao = pillllDatabase.specialiteDao();
        this.compositionDao = pillllDatabase.compositionDao();
        this.specialiteDao.insertSpecialite(specialite_test);
        this.compositionDao.insertComposition(composition_test);
    }

    @After
    public void closeDb() throws Exception{
        pillllDatabase.close();
    }

    @Test
    public void GetCompositionById() throws InterruptedException{
        Composition composition = LiveDataTestUtil.getValue(this.compositionDao.selectCompositionById(1));
        Assert.assertTrue("la récupération de la composition dans la bd a échoué", composition.getDenominationSubstance().equals("CONCENTRAT DE VITAMINE A SYNTHÉTIQUE, FORME HUILEUSE"));
    }

    @Test
    public void GetCompositionsByIdCodeCis() throws InterruptedException{
        List<Composition> compositions = LiveDataTestUtil.getValue(this.compositionDao.selectCompositionByCodeCis(61266250));
        Assert.assertTrue("la récupération de la composition dans la bd a échoué", compositions.get(0).getDenominationSubstance().equals("CONCENTRAT DE VITAMINE A SYNTHÉTIQUE, FORME HUILEUSE"));
    }

    @Test
    public void DeleteComposition() throws InterruptedException{
        Composition composition = LiveDataTestUtil.getValue(this.compositionDao.selectCompositionById(1));
        int deleteOk = this.compositionDao.deleteComposition(composition);
        Assert.assertTrue("La supression du composition a échoué", deleteOk == 1);
   }

    @Test
    public void UpdateComposition() throws InterruptedException{
        Composition composition = LiveDataTestUtil.getValue(this.compositionDao.selectCompositionById(1));
        composition.setDenominationSubstance("CONCENTRAT DE VITAMINE A SYNTHÉTIQUE");
        this.compositionDao.updateComposition(composition);
        Composition compositionUpdated = LiveDataTestUtil.getValue(this.compositionDao.selectCompositionById(1));
        Assert.assertTrue("La modification du générique a échoué", compositionUpdated.getDenominationSubstance().equals("CONCENTRAT DE VITAMINE A SYNTHÉTIQUE"));
    }
}
