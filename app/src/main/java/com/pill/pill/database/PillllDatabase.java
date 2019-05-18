package com.pill.pill.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.pill.pill.database.converter.DateConverter;
import com.pill.pill.database.dao.AsmrDao;
import com.pill.pill.database.dao.CompositionDao;
import com.pill.pill.database.dao.ConditionPrescriptionDao;
import com.pill.pill.database.dao.EvaluationDao;
import com.pill.pill.database.dao.GeneriqueDao;
import com.pill.pill.database.dao.InfoImportanteDao;
import com.pill.pill.database.dao.LienCtDao;
import com.pill.pill.database.dao.PresentationDao;
import com.pill.pill.database.dao.SmrDao;
import com.pill.pill.database.dao.SpecialiteDao;
import com.pill.pill.database.dao.TitulaireSpecialiteDao;
import com.pill.pill.database.dao.VoiesAdministrationDao;
import com.pill.pill.database.entity.Asmr;
import com.pill.pill.database.entity.Composition;
import com.pill.pill.database.entity.ConditionPrescription;
import com.pill.pill.database.entity.Evaluation;
import com.pill.pill.database.entity.Generique;
import com.pill.pill.database.entity.InfoImportante;
import com.pill.pill.database.entity.LienCt;
import com.pill.pill.database.entity.Presentation;
import com.pill.pill.database.entity.Smr;
import com.pill.pill.database.entity.Specialite;
import com.pill.pill.database.entity.TitulaireSpecialite;
import com.pill.pill.database.entity.VoiesAdministration;

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
    public abstract EvaluationDao evaluationDao();
    public abstract GeneriqueDao generiqueDao();
    public abstract InfoImportanteDao infoImportanteDao();
    public abstract LienCtDao lienCtDao();
    public abstract SmrDao smrDao();
    public abstract TitulaireSpecialiteDao titulaireSpecialiteDao();
    public abstract VoiesAdministrationDao voiesAdministrationDao();

    // INSTANCE
    public static PillllDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (PillllDatabase.class) {
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PillllDatabase.class, "PillllDatabase")
                            .addCallback(prepopulatedDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulatedDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                // Ajout du contenu ajouté à l'initialisation de la base de donnée
                ContentValues contentValues = new ContentValues();
                contentValues.put("id_code_cis", 123456);
                // A Enrichir ... /!\
                db.insert("Specialite", OnConflictStrategy.IGNORE, contentValues);

            }
        };
    }
}
