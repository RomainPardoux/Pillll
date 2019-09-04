package com.pillll.pillll.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import com.pillll.pillll.database.converter.DateConverter;
import com.pillll.pillll.database.dao.AsmrDao;
import com.pillll.pillll.database.dao.CompositionDao;
import com.pillll.pillll.database.dao.ConditionPrescriptionDao;
import com.pillll.pillll.database.dao.GeneriqueDao;
import com.pillll.pillll.database.dao.InfoImportanteDao;
import com.pillll.pillll.database.dao.LienCtDao;
import com.pillll.pillll.database.dao.PresentationDao;
import com.pillll.pillll.database.dao.SmrDao;
import com.pillll.pillll.database.dao.SpecialiteDao;
import com.pillll.pillll.database.dao.TitulaireSpecialiteDao;
import com.pillll.pillll.database.dao.VoiesAdministrationDao;
import com.pillll.pillll.database.entity.Asmr;
import com.pillll.pillll.database.entity.Composition;
import com.pillll.pillll.database.entity.ConditionPrescription;
import com.pillll.pillll.database.entity.Evaluation;
import com.pillll.pillll.database.entity.Generique;
import com.pillll.pillll.database.entity.InfoImportante;
import com.pillll.pillll.database.entity.LienCt;
import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.database.entity.Smr;
import com.pillll.pillll.database.entity.Specialite;
import com.pillll.pillll.database.entity.TitulaireSpecialite;
import com.pillll.pillll.database.entity.VoiesAdministration;

/**
 * Created by Pardoux Romain on 09/01/2019
 */

@Database(entities = {Specialite.class, Composition.class, Presentation.class, Asmr.class, ConditionPrescription.class,
        Evaluation.class, Generique.class, InfoImportante.class, LienCt.class, Smr.class, TitulaireSpecialite.class, VoiesAdministration.class},
        version = 1, exportSchema = false)
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
