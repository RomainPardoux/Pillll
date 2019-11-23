package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO used to retrieve JSON Info Importante data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class InfoImportanteData {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("date_debut")
    @Expose
    private String dateDebut;
    @SerializedName("date_fin")
    @Expose
    private String dateFin;
    @SerializedName("description")
    @Expose
    private String description;

    // GETTER AND SETTER

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
