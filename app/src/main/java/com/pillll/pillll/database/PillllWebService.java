package com.pillll.pillll.database;

import com.pillll.pillll.database.pojo.Presentation;
import com.pillll.pillll.database.pojo.Presentations;
import com.pillll.pillll.database.pojo.Specialite;
import com.pillll.pillll.database.pojo.Specialites;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PillllWebService {

    // PRESENTATION
    @GET("presentations/{id}")
    Call<Presentation> getPresentation(@Path("id") Integer id);

    @GET("presentations/codecip7/{codeCip7}")
    Call<Presentation> getPresentationWithCodeCip7(@Path("codeCip7") String codeCip7);

    @GET("presentations/codecip13/{codeCip13}")
    Call<Presentation> getPresentationWithCodeCip13(@Path("codeCip13") String codeCip13);

    @GET("presentations")
    Call<Presentations> listPresentation(@Query("libelle") String libelle );

    // SPECIALITE
    @GET("specialites/{idCodeCis}")
    Call<Specialite> getSpecialiteWithCodeCis(@Path("idCodeCis") Long idCodeCis);

    @GET("specialites")
    Call<Specialites> listSpecialiteWithDenomination(@Query("denomination") String denomination);

}
