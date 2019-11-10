package com.pillll.pillll.database.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pardoux Romain on 09/11/2019
 */

public class LienCt {

    @SerializedName("id")
    @Expose
    private String codeDossierHas;
    @SerializedName("lien_avis_ct")
    @Expose
    private String lienAvisCt;

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
