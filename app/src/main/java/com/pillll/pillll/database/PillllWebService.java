package com.pillll.pillll.database;

import com.pillll.pillll.database.pojo.PresentationData;
import com.pillll.pillll.database.pojo.PresentationsData;
import com.pillll.pillll.database.pojo.SpecialiteData;
import com.pillll.pillll.database.pojo.SpecialitesData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PillllWebService {

    // PRESENTATION
    @GET("presentations/{id}")
    Call<PresentationData> getPresentation(@Path("id") Integer id);

    @GET("presentations/codecip7/{codeCip7}")
    Call<PresentationData> getPresentationWithCodeCip7(@Path("codeCip7") String codeCip7);

    @GET("presentations/codecip13/{codeCip13}")
    Call<PresentationData> getPresentationWithCodeCip13(@Path("codeCip13") String codeCip13);

    @GET("presentations")
    Call<PresentationsData> listPresentation(@Query("libelle") String libelle );

    // SPECIALITE
    @GET("specialites/{idCodeCis}")
    Call<SpecialiteData> getSpecialiteWithCodeCis(@Path("idCodeCis") Long idCodeCis);

    @GET("specialites")
    Call<SpecialitesData> listSpecialiteWithDenomination(@Query("denomination") String denomination);

}
