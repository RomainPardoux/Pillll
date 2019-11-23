package com.pillll.pillll.model.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * This class will have a mapping SQLite table in the database for Lien CT entity.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Entity
public class LienCt {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "code_dossier_has")
    private String codeDossierHas;
    @ColumnInfo(name = "lien_avis_ct")
    private String lienAvisCt;

    public LienCt(String codeDossierHas, String lienAvisCt) {
        this.codeDossierHas = codeDossierHas;
        this.lienAvisCt = lienAvisCt;
    }

    public LienCt() {
    }

    // GETTER AND SETTER
    public String getCodeDossierHas() {
        return codeDossierHas;
    }

    public void setCodeDossierHas(String codeDossierHas) {
        this.codeDossierHas = codeDossierHas;
    }

    public String getLienAvisCt() {
        return lienAvisCt;
    }

    public void setLienAvisCt(String lienAvisCt) {
        this.lienAvisCt = lienAvisCt;
    }
}
