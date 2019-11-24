package com.pillll.pillll.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.pillll.pillll.model.entities.Asmr;
import java.util.List;

/**
 * DAO class used to access to asmr data using Room persistence library.
 * This class includes methods that offer abstract access to app database.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
@Dao
public interface AsmrDao {

    @Query("SELECT * FROM Asmr WHERE id = :id")
    LiveData<Asmr> selectAsmrById(long id);

    @Query("SELECT * FROM Asmr WHERE specialite_id_code_cis = :specialiteIdCodeCis")
    LiveData<List<Asmr>> selectAsmrByCodeCis(long specialiteIdCodeCis);

    @Insert
    long insertAsmr(Asmr asmr);

    @Insert
    void insertAll(List<Asmr> asmrs);

    @Update
    int updateAsmr(Asmr asmr);

    @Delete
    int deleteAsmr(Asmr asmr);
}
