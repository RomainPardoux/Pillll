package com.pillll.pillll.model.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import com.pillll.pillll.model.converter.DateConverter;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * This class will have a mapping SQLite table in the database for Info importante entity.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis", onDelete = CASCADE, onUpdate = CASCADE),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class InfoImportante {

    @PrimaryKey
    private long id;
    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "date_debut")
    private Date dateDebut;
    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "date_fin")
    private Date dateFin;
    private String description;
    @ColumnInfo(name = "specialite_id_code_cis")
    private long specialiteIdCodeCis;

    public InfoImportante( Date dateDebut, Date dateFin, String description, long specialiteIdCodeCis) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }


    public InfoImportante() {
    }

    // GETTER AND SETTER
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSpecialiteIdCodeCis() {
        return specialiteIdCodeCis;
    }

    public void setSpecialiteIdCodeCis(long specialiteIdCodeCis) {
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }
}
