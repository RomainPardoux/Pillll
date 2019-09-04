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
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface PillllWebService {

    public static final String ENDPOINT = "http://www.pillll.com/api";

    // ASMR
    @GET("/asmrs/{id}")
    Asmr getAsmr(@Path("id") Integer id);

    @GET("/asmrs")
    List<Asmr> listAsmr(@Query("idCodeCis") Long idCodeCis );

    @GET("/asmrs/{id}")
    Asmr getAsmrAsync(@Path("id") Integer id, Callback<List<Asmr>> callback);

    @GET("/asmrs")
    List<Asmr> listAsmrAsync(@Query("idCodeCis") Long idCodeCis, Callback<List<Asmr>> callback );

    // COMPOSITION
    @GET("/compositions/{id}")
    Composition getComposition(@Path("id") Integer id);

    @GET("/compositions")
    List<Composition> listComposition(@Query("idCodeCis") Long idCodeCis );

    @GET("/compositions/{id}")
    Composition getCompositionAsync(@Path("id") Integer id, Callback<List<Composition>> callback );

    @GET("/compositions")
    List<Composition> listCompositionAsync(@Query("idCodeCis") Long idCodeCis, Callback<List<Composition>> callback  );

    // CONDITION PRESCRIPTION
    @GET("/conditionsPrescriptions/{id}")
    ConditionPrescription getConditionPrescription(@Path("id") Integer id);

    @GET("/conditionsPrescriptions")
    List<ConditionPrescription> listConditionPrescription(@Query("idCodeCis") Long idCodeCis );

    @GET("/conditionsPrescriptions/{id}")
    ConditionPrescription getConditionPrescriptionAsync(@Path("id") Integer id, Callback<List<ConditionPrescription>> callback );

    @GET("/conditionsPrescriptions")
    List<ConditionPrescription> listConditionPrescriptionAsync(@Query("idCodeCis") Long idCodeCis, Callback<List<ConditionPrescription>> callback  );

    // GENERIQUE
    @GET("/generiques/{id}")
    Generique getGenerique(@Path("id") Integer id);

    @GET("/generiques")
    Generique listGenerique(@Query("idCodeCis") Long idCodeCis );

    @GET("/generiques/{id}")
    Generique getGeneriqueAsync(@Path("id") Integer id, Callback<Generique> callback );

    @GET("/generiques")
    Generique listGeneriqueAsync(@Query("idCodeCis") Long idCodeCis, Callback<Generique> callback  );

    // INFO IMPORTANTE
    @GET("/infosImportantes/{id}")
    InfoImportante getInfoImportante(@Path("id") Integer id);

    @GET("/infosImportantes")
    List<InfoImportante> listInfoImportante(@Query("idCodeCis") Long idCodeCis );

    @GET("/infosImportantes/{id}")
    InfoImportante getInfoImportanteAsync(@Path("id") Integer id, Callback<List<InfoImportante>> callback );

    @GET("/infosImportantes")
    List<InfoImportante> listInfoImportanteAsync(@Query("idCodeCis") Long idCodeCis, Callback<List<InfoImportante>> callback  );

    // LIEN CT
    @GET("/lientCts/{codeDossierHas}")
    LienCt getLienCt(@Path("codeDossierHas") String codeDossierHas);

    @GET("/lientCts/{codeDossierHas}")
    LienCt getLienCtAsync(@Path("codeDossierHas") String codeDossierHas, Callback<LienCt> callback );

    // PRESENTATION
    @GET("/presentations/{id}")
    Presentation getPresentation(@Path("id") Integer id);

    @GET("/presentations/codeCip7/{codeCip7}")
    Presentation getPresentationWithCodeCip7(@Path("codeCip7") String codeCip7);

    @GET("/presentations/codeCip13/{codeCip13}")
    Presentation getPresentationWithCodeCip13(@Path("codeCip13") String codeCip13);

    @GET("/presentations")
    List<Presentation> listPresentation(@Query("idCodeCis") Long idCodeCis );

    @GET("/presentations/{id}")
    Presentation getPresentationAsync(@Path("id") Integer id, Callback<Presentation> callback );

    @GET("/presentations/codeCip7/{codeCip7}")
    Presentation getPresentationWithCodeCip7Async(@Path("codeCip7") String codeCip7, Callback<Presentation> callback);

    @GET("/presentations/codeCip13/{codeCip13}")
    Presentation getPresentationWithCodeCip13Async(@Path("codeCip13") String codeCip13, Callback<Presentation> callback);

    @GET("/presentations")
    List<Presentation> listPresentationAsync(@Query("idCodeCis") Long idCodeCis, Callback<List<Presentation>> callback );

    // SMR
    @GET("/smrs/{id}")
    Smr getSmr(@Path("id") Integer id);

    @GET("/smrs")
    List<Smr> listSmr(@Query("idCodeCis") Long idCodeCis );

    @GET("/smrs/{id}")
    Smr getSmrAsync(@Path("id") Integer id, Callback<List<Smr>> callback);

    @GET("/smrs")
    List<Smr> listSmrAsync(@Query("idCodeCis") Long idCodeCis, Callback<List<Smr>> callback );

    // SPECIALITE
    @GET("/Specialites/{idCodeCis}")
    Specialite getSpecialiteWithCodeCis(@Path("idCodeCis") Long idCodeCis);

    @GET("/specialites")
    List<Specialite> listSpecialiteWithDenomination(@Query("denomination") String denomination);

    @GET("/Specialites/{idCodeCis}")
    Specialite getSpecialiteAsyncWithCodeCis(@Path("idCodeCis") Long idCodeCis, Callback<Specialite> callback);

    @GET("/specialites")
    List<Specialite> listSpecialiteAsyncWithDenomination(@Query("denomination") String denomination, Callback<List<Specialite>> callback);

    // TITULAIRE SPECIALITE
    @GET("/titulairesSpecialites/{id}")
    TitulaireSpecialite getTitulaireSpecialite(@Path("id") Integer id);

    @GET("/titulairesSpecialites")
    List<TitulaireSpecialite> listTitulaireSpecialite(@Query("idCodeCis") Long idCodeCis );

    @GET("/titulairesSpecialites/{id}")
    TitulaireSpecialite getTitulaireSpecialiteAsync(@Path("id") Integer id, Callback<List<TitulaireSpecialite>> callback);

    @GET("/titulairesSpecialites")
    List<TitulaireSpecialite> listTitulaireSpecialiteAsync(@Query("idCodeCis") Long idCodeCis, Callback<List<TitulaireSpecialite>> callback );

    // VOIES ADMINISTRATION
    @GET("/voiesAdministrations/{id}")
    VoiesAdministration getVoiesAdministration(@Path("id") Integer id);

    @GET("/voiesAdministrations")
    List<VoiesAdministration> listVoiesAdministration(@Query("idCodeCis") Long idCodeCis );

    @GET("/voiesAdministrations/{id}")
    VoiesAdministration getVoiesAdministrationAsync(@Path("id") Integer id, Callback<List<VoiesAdministration>> callback);

    @GET("/voiesAdministrations")
    List<VoiesAdministration> listVoiesAdministrationAsync(@Query("idCodeCis") Long idCodeCis, Callback<List<VoiesAdministration>> callback );

}
