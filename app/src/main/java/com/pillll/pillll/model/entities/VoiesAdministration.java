package com.pillll.pillll.model.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * This class will have a mapping SQLite table in the database for Voies Administration entity.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Entity(foreignKeys = @ForeignKey(entity = Specialite.class, parentColumns = "id_code_cis", childColumns = "specialite_id_code_cis", onDelete = CASCADE, onUpdate = CASCADE),
        indices = {@Index(value = {"specialite_id_code_cis"})})
public class VoiesAdministration {

    @PrimaryKey
    private long id;
    @ColumnInfo(name = "voies_administration")
    private String voiesAdministration;
    @ColumnInfo(name = "specialite_id_code_cis")
    private long specialiteIdCodeCis;

    public VoiesAdministration(String voiesAdministration, long specialiteIdCodeCis) {
        this.voiesAdministration = voiesAdministration;
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }

    public VoiesAdministration() {
    }

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

    public long getSpecialiteIdCodeCis() {
        return specialiteIdCodeCis;
    }

    public void setSpecialiteIdCodeCis(long specialiteIdCodeCis) {
        this.specialiteIdCodeCis = specialiteIdCodeCis;
    }
}
