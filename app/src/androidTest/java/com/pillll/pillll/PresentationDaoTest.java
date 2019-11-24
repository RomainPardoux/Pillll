package com.pillll.pillll;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.pillll.pillll.model.converter.DateConverter;
import com.pillll.pillll.model.PillllDatabase;
import com.pillll.pillll.model.dao.PresentationDao;
import com.pillll.pillll.model.dao.SpecialiteDao;
import com.pillll.pillll.model.entities.Presentation;
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
public class PresentationDaoTest {

    // FOR DATA
    private PillllDatabase pillllDatabase;
    private SpecialiteDao specialiteDao;
    private PresentationDao presentationDao;
    // DATA SET FOR TEST
    private Long date_timestamp = Integer.toUnsignedLong(1515791376);
    private Date date_amm = DateConverter.fromTimestamp(date_timestamp);
    private Specialite specialite_test = new Specialite(61266250, "A 313 200 000 UI POUR CENT, pommade", "pommade", "Autorisation active", "Procédure nationale", date_amm, null, null, false, "Commercialisée" );
    private Presentation presentation_test = new Presentation( 1, "4949729", "3400949497294", "plaquette(s) PVC PVDC aluminium de 30 comprimé(s)", "Présentation active", "Déclaration de commercialisation", date_amm, false, 30, 2.63, null, 61266250 );

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.pillllDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PillllDatabase.class)
                .allowMainThreadQueries()
                .build();
        this.specialiteDao = pillllDatabase.specialiteDao();
        this.presentationDao = pillllDatabase.presentationDao();
        this.specialiteDao.insertSpecialite(specialite_test);
        this.presentationDao.insertPresentation(presentation_test);
    }

    @After
    public void closeDb() throws Exception{
        pillllDatabase.close();
    }

    @Test
    public void GetPresentationById() throws InterruptedException{
        Presentation presentation = LiveDataTestUtil.getValue(this.presentationDao.selectPresentationById(1));
        Assert.assertEquals("3400930001479", presentation.getCodeCip13());
    }

    @Test
    public void GetPresentationsByIdCodeCis() throws InterruptedException{
        List<Presentation> presentations = LiveDataTestUtil.getValue(this.presentationDao.selectPresentationByCodeCis(61266250));
        Assert.assertEquals("3400930001479", presentations.get(0).getCodeCip13());
    }

    @Test
    public void DeletePresentation() throws InterruptedException{
        Presentation presentation = LiveDataTestUtil.getValue(this.presentationDao.selectPresentationById(1));
        int deleteOk = this.presentationDao.deletePresentation(presentation);
        Assert.assertEquals(1, deleteOk);
   }

    @Test
    public void UpdatePresentation() throws InterruptedException{
        Presentation presentation = LiveDataTestUtil.getValue(this.presentationDao.selectPresentationById(1));
        presentation.setCodeCip13("3400930001478");
        this.presentationDao.updatePresentation(presentation);
        Presentation presentationUpdated = LiveDataTestUtil.getValue(this.presentationDao.selectPresentationById(1));
        Assert.assertEquals("3400930001478", presentationUpdated.getCodeCip13());
    }
}
