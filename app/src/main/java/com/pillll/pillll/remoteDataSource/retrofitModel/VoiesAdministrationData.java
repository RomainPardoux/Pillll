package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO used to retrieve JSON Voies Administration data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
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
