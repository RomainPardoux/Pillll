package com.pillll.pillll.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.pillll.pillll.database.entity.Asmr;
import java.util.List;

/**
 * Created by Pardoux Romain on 08/01/2019
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
