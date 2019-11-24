package com.pillll.pillll.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;
import com.pillll.pillll.model.converter.DateConverter;
import com.pillll.pillll.model.dao.AsmrDao;
import com.pillll.pillll.model.dao.CompositionDao;
import com.pillll.pillll.model.dao.ConditionPrescriptionDao;
import com.pillll.pillll.model.dao.GeneriqueDao;
import com.pillll.pillll.model.dao.InfoImportanteDao;
import com.pillll.pillll.model.dao.LienCtDao;
import com.pillll.pillll.model.dao.PresentationDao;
import com.pillll.pillll.model.dao.SmrDao;
import com.pillll.pillll.model.dao.SpecialiteDao;
import com.pillll.pillll.model.dao.SpecialiteEtRelationsDao;
import com.pillll.pillll.model.dao.TitulaireSpecialiteDao;
import com.pillll.pillll.model.dao.VoiesAdministrationDao;
import com.pillll.pillll.model.entities.Asmr;
import com.pillll.pillll.model.entities.Composition;
import com.pillll.pillll.model.entities.ConditionPrescription;
import com.pillll.pillll.model.entities.Generique;
import com.pillll.pillll.model.entities.InfoImportante;
import com.pillll.pillll.model.entities.LienCt;
import com.pillll.pillll.model.entities.Presentation;
import com.pillll.pillll.model.entities.Smr;
import com.pillll.pillll.model.entities.Specialite;
import com.pillll.pillll.model.entities.TitulaireSpecialite;
import com.pillll.pillll.model.entities.VoiesAdministration;

/**
 * Contains the database holder and serves as the main access point fot underlying connection to app persisted, relational data.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Database(entities = {Specialite.class, Composition.class, Presentation.class, Asmr.class, ConditionPrescription.class,
        Generique.class, InfoImportante.class, LienCt.class, Smr.class, TitulaireSpecialite.class, VoiesAdministration.class},
        version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class PillllDatabase extends RoomDatabase {

    // Singleton
    private static volatile PillllDatabase INSTANCE;

    // DAO
    public abstract SpecialiteDao specialiteDao();
    public abstract CompositionDao compositionDao();
    public abstract PresentationDao presentationDao();
    public abstract AsmrDao asmrDao();
    public abstract ConditionPrescriptionDao conditionPrescriptionDao();
    public abstract GeneriqueDao generiqueDao();
    public abstract InfoImportanteDao infoImportanteDao();
    public abstract LienCtDao lienCtDao();
    public abstract SmrDao smrDao();
    public abstract TitulaireSpecialiteDao titulaireSpecialiteDao();
    public abstract VoiesAdministrationDao voiesAdministrationDao();
    public abstract SpecialiteEtRelationsDao specialiteEtRelationsDao();

    // INSTANCE
    public static PillllDatabase getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (PillllDatabase.class) {
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PillllDatabase.class, "PillllDatabase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
