package com.pill.pill.repositories;

import android.arch.lifecycle.LiveData;
import com.pill.pill.database.dao.VoiesAdministrationDao;
import com.pill.pill.models.VoiesAdministration;
import java.util.List;

/**
 * Created by Pardoux Romain on 18/01/2019
 */

public class VoiesAdministrationDataRepository {

    private final VoiesAdministrationDao voiesAdministrationDao;

    public VoiesAdministrationDataRepository(VoiesAdministrationDao voiesAdministrationDao) {
        this.voiesAdministrationDao = voiesAdministrationDao;
    }

    public LiveData<VoiesAdministration> selectVoiesAdministrationParId(long id){
        return this.voiesAdministrationDao.selectVoiesAdministrationParId(id);
    }

    public LiveData<List<VoiesAdministration>> selectVoiesAdministrationParCodeCis(long specialiteIdCodeCis){
        return this.voiesAdministrationDao.selectVoiesAdministrationParCodeCis(specialiteIdCodeCis);
    }

    public long insertVoiesAdministration(VoiesAdministration voiesAdministration){
        return this.voiesAdministrationDao.insertVoiesAdministration(voiesAdministration);
    }

    public int updateVoiesAdministration (VoiesAdministration voiesAdministration){
        return this.voiesAdministrationDao.updateVoiesAdministration(voiesAdministration);
    }

    public int deleteVoiesAdministration (VoiesAdministration voiesAdministration){
        return this.voiesAdministrationDao.deleteVoiesAdministration(voiesAdministration);
    }
}
