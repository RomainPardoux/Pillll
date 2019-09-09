package com.pillll.pillll.database;

import com.pillll.pillll.database.entity.Asmr;
import com.pillll.pillll.database.entity.Composition;
import com.pillll.pillll.database.entity.ConditionPrescription;
import com.pillll.pillll.database.entity.Generique;
import com.pillll.pillll.database.entity.InfoImportante;
import com.pillll.pillll.database.entity.LienCt;
import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.database.entity.Smr;
import com.pillll.pillll.database.entity.Specialite;
import com.pillll.pillll.database.entity.TitulaireSpecialite;
import com.pillll.pillll.database.entity.VoiesAdministration;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PillllWebService {

    public static final String ENDPOINT = "http://www.pillll.com/api/";

    // ASMR
    @GET("/asmrs/{id}")
    Call<Asmr> getAsmr(@Path("id") Integer id);

    @GET("/asmrs")
    Call<List<Asmr>> listAsmr(@Query("idCodeCis") Long idCodeCis );

    // COMPOSITION
    @GET("/compositions/{id}")
    Call<Composition> getComposition(@Path("id") Integer id);

    @GET("/compositions")
    Call<List<Composition>> listComposition(@Query("idCodeCis") Long idCodeCis );

    // CONDITION PRESCRIPTION
    @GET("/conditionsPrescriptions/{id}")
    Call<ConditionPrescription> getConditionPrescription(@Path("id") Integer id);

    @GET("/conditionsPrescriptions")
    Call<List<ConditionPrescription>> listConditionPrescription(@Query("idCodeCis") Long idCodeCis );

    // GENERIQUE
    @GET("/generiques/{id}")
    Call<Generique> getGenerique(@Path("id") Integer id);

    @GET("/generiques")
    Call<Generique> listGenerique(@Query("idCodeCis") Long idCodeCis );

    // INFO IMPORTANTE
    @GET("/infosImportantes/{id}")
    Call<InfoImportante> getInfoImportante(@Path("id") Integer id);

    @GET("/infosImportantes")
    Call<List<InfoImportante>> listInfoImportante(@Query("idCodeCis") Long idCodeCis );

    // LIEN CT
    @GET("/lientCts/{codeDossierHas}")
    Call<LienCt> getLienCt(@Path("codeDossierHas") String codeDossierHas);

    // PRESENTATION
    @GET("/presentations/{id}")
    Call<Presentation> getPresentation(@Path("id") Integer id);

    @GET("/presentations/codeCip7/{codeCip7}")
    Call<Presentation> getPresentationWithCodeCip7(@Path("codeCip7") String codeCip7);

    @GET("/presentations/codeCip13/{codeCip13}")
    Call<Presentation> getPresentationWithCodeCip13(@Path("codeCip13") String codeCip13);

    @GET("/presentations")
    Call<List<Presentation>> listPresentation(@Query("idCodeCis") Long idCodeCis );

    // SMR
    @GET("/smrs/{id}")
    Call<Smr> getSmr(@Path("id") Integer id);

    @GET("/smrs")
    Call<List<Smr>> listSmr(@Query("idCodeCis") Long idCodeCis );

    // SPECIALITE
    @GET("Specialites/{idCodeCis}")
    Call<Specialite> getSpecialiteWithCodeCis(@Path("idCodeCis") Long idCodeCis);

    @GET("specialites")
    Call<List<Specialite>> listSpecialiteWithDenomination(@Query("denomination") String denomination);

    // TITULAIRE SPECIALITE
    @GET("/titulairesSpecialites/{id}")
    Call<TitulaireSpecialite> getTitulaireSpecialite(@Path("id") Integer id);

    @GET("/titulairesSpecialites")
    Call<List<TitulaireSpecialite>> listTitulaireSpecialite(@Query("idCodeCis") Long idCodeCis );

    // VOIES ADMINISTRATION
    @GET("/voiesAdministrations/{id}")
    Call<VoiesAdministration> getVoiesAdministration(@Path("id") Integer id);

    @GET("/voiesAdministrations")
    Call<List<VoiesAdministration>> listVoiesAdministration(@Query("idCodeCis") Long idCodeCis );

}
