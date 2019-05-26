package com.pill.pill.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Pardoux Romain on 04/01/2019
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