package com.pillll.pillll.database.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pardoux Romain on 09/11/2019
 */

public class VoiesAdministrationData {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("voies_administration")
    @Expose
    private String voiesAdministration;

    // GETTER AND SETTER

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVoiesAdministration() {
        return voiesAdministration;
    }

    public void setVoiesAdministration(String voiesAdministration) {
        this.voiesAdministration = voiesAdministration;
    }
}
