package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO used to retrieve JSON Lien CT data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class LienCtData {

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
