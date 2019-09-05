package com.pillll.pillll.database.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import java.util.List;

/**
 * Specialite Pojo class which can be used to fetch it relation entities.
 * When the Pojo is returned from a query, all of its relations are also fetched by Room.
 * @author Romain Pardoux
 * @version 1.0
 */
public class SpecialiteEtRelations {

    @Embedded
    public Specialite specialite;

    @Relation(entity = Composition.class, parentColumn = "specialite.idCodeCis", entityColumn = "specialiteIdCodeCis")
    public List<Composition> compositions;

    @Relation(entity = Presentation.class, parentColumn = "specialite.idCodeCis", entityColumn = "specialiteIdCodeCis")
    public List<Presentation> presentations;

    @Relation(entity = ConditionPrescription.class, parentColumn = "specialite.idCodeCis", entityColumn = "specialiteIdCodeCis")
    public List<ConditionPrescription> conditionPrescriptions;

    @Relation(entity = VoiesAdministration.class, parentColumn = "specialite.idCodeCis", entityColumn = "specialiteIdCodeCis")
    public List<VoiesAdministration> voiesAdministrations;

    @Relation(entity = TitulaireSpecialite.class, parentColumn = "specialite.idCodeCis", entityColumn = "specialiteIdCodeCis")
    public List<TitulaireSpecialite> titulaireSpecialites;

    @Relation(entity = Asmr.class, parentColumn = "specialite.idCodeCis", entityColumn = "specialiteIdCodeCis")
    public List<Asmr> asmrs;

    @Relation(entity = Smr.class, parentColumn = "specialite.idCodeCis", entityColumn = "specialiteIdCodeCis")
    public List<Smr> smrs;

    @Relation(entity = InfoImportante.class, parentColumn = "specialite.idCodeCis", entityColumn = "specialiteIdCodeCis")
    public List<InfoImportante> infoImportantes;

}
