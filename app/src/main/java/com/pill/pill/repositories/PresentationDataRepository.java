package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;
import com.pill.pill.database.dao.PresentationDao;
import com.pill.pill.models.Presentation;
import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

public class PresentationDataRepository {

    private final PresentationDao presentationDao;

    public PresentationDataRepository(PresentationDao presentationDao) {
        this.presentationDao = presentationDao;
    }

    public LiveData<Presentation> selectPresentationParId(long id){
        return this.presentationDao.selectPresentationParId(id);
    }

    public LiveData<List<Presentation>> selectPresentationParCodeCis(long specialiteIdCodeCis){
        return this.presentationDao.selectPresentationParCodeCis(specialiteIdCodeCis);
    }

    public long insertPresentation(Presentation presentation){
        return this.presentationDao.insertPresentation(presentation);
    }

    public int updatePresentation (Presentation presentation){
        return this.presentationDao.updatePresentation(presentation);
    }

    public int deletePresentation (Presentation presentation){
        return this.presentationDao.deletePresentation(presentation);
    }
}
