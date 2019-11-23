package com.pillll.pillll.model.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import java.util.List;

/**
 * Specialite Pojo class which can be used to fetch it relation entities.
 * When the Pojo is returned from a query, all of its relations are also fetched by Room.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class SpecialiteEtRelations {

    @Embedded
    public Specialite specialite;

    @Relation(entity = Composition.class, parentColumn = "id_code_cis", entityColumn = "specialite_id_code_cis")
    public List<Composition> compositions;

    @Relation(entity = Presentation.class, parentColumn = "id_code_cis", entityColumn = "specialite_id_code_cis")
    public List<Presentation> presentations;

    @Relation(entity = ConditionPrescription.class, parentColumn = "id_code_cis", entityColumn = "specialite_id_code_cis")
    public List<ConditionPrescription> conditionPrescriptions;

    @Relation(entity = VoiesAdministration.class, parentColumn = "id_code_cis", entityColumn = "specialite_id_code_cis")
    public List<VoiesAdministration> voiesAdministrations;

    @Relation(entity = TitulaireSpecialite.class, parentColumn = "id_code_cis", entityColumn = "specialite_id_code_cis")
    public List<TitulaireSpecialite> titulaireSpecialites;

    @Relation(entity = Asmr.class, parentColumn = "id_code_cis", entityColumn = "specialite_id_code_cis")
    public List<Asmr> asmrs;

    @Relation(entity = Smr.class, parentColumn = "id_code_cis", entityColumn = "specialite_id_code_cis")
    public List<Smr> smrs;

    @Relation(entity = InfoImportante.class, parentColumn = "id_code_cis", entityColumn = "specialite_id_code_cis")
    public List<InfoImportante> infoImportantes;

}
