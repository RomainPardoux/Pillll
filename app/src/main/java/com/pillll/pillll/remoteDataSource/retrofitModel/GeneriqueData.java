package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO used to retrieve JSON Generique data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class GeneriqueData {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("identifiant_groupe")
    @Expose
    private String identifiantGroupe;
    @SerializedName("libelle_groupe")
    @Expose
    private String libelleGroupe;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("numero_tri")
    @Expose
    private String numeroTri;

    // GETTER AND SETTER

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentifiantGroupe() {
        return identifiantGroupe;
    }

    public void setIdentifiantGroupe(String identifiantGroupe) {
        this.identifiantGroupe = identifiantGroupe;
    }

    public String getLibelleGroupe() {
        return libelleGroupe;
    }

    public void setLibelleGroupe(String libelleGroupe) {
        this.libelleGroupe = libelleGroupe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumeroTri() {
        return numeroTri;
    }

    public void setNumeroTri(String numeroTri) {
        this.numeroTri = numeroTri;
    }
}
