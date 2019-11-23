package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO used to retrieve JSON Titulaire Specialite data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class TitulaireSpecialiteData {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("titulaire")
    @Expose
    private String titulaire;

    // GETTER AND SETTER

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }
}
