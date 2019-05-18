package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;
import com.pill.pill.database.dao.CompositionDao;
import com.pill.pill.database.entity.Composition;
import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

public class CompositionDataRepository {

    private final CompositionDao compositionDao;

    public CompositionDataRepository(CompositionDao compositionDao) {
        this.compositionDao = compositionDao;
    }

    public LiveData<Composition> selectCompositionParId(long id){
        return this.compositionDao.selectCompositionParId(id);
    }

    public LiveData<List<Composition>> selectCompositionParCodeCis(long specialiteIdCodeCis){
        return this.compositionDao.selectCompositionParCodeCis(specialiteIdCodeCis);
    }

    public long insertComposition(Composition composition){
        return this.compositionDao.insertComposition(composition);
    }

    public int updateComposition (Composition composition){
        return this.compositionDao.updateComposition(composition);
    }

    public int deleteComposition (Composition composition){
        return this.compositionDao.deleteComposition(composition);
    }
}
