package com.pillll.pillll.database.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pardoux Romain on 09/11/2019
 */
public class TitulaireSpecialite {

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
