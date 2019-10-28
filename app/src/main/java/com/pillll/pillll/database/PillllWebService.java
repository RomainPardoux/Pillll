package com.pillll.pillll.database;

import com.pillll.pillll.database.entity.Asmr;
import com.pillll.pillll.database.entity.Asmrs;
import com.pillll.pillll.database.entity.Composition;
import com.pillll.pillll.database.entity.Compositions;
import com.pillll.pillll.database.entity.ConditionPrescription;
import com.pillll.pillll.database.entity.ConditionsPrescriptions;
import com.pillll.pillll.database.entity.Generique;
import com.pillll.pillll.database.entity.Generiques;
import com.pillll.pillll.database.entity.InfoImportante;
import com.pillll.pillll.database.entity.InfosImportantes;
import com.pillll.pillll.database.entity.LienCt;
import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.database.entity.Presentations;
import com.pillll.pillll.database.entity.Smr;
import com.pillll.pillll.database.entity.Smrs;
import com.pillll.pillll.database.entity.Specialite;
import com.pillll.pillll.database.entity.Specialites;
import com.pillll.pillll.database.entity.TitulaireSpecialite;
import com.pillll.pillll.database.entity.TitulairesSpecialites;
import com.pillll.pillll.database.entity.VoiesAdministration;
import com.pillll.pillll.database.entity.VoiesAdministrations;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PillllWebService {

    // ASMR
    @GET("asmrs/{id}")
    Call<Asmr> getAsmr(@Path("id") Integer id);

    @GET("asmrs")
    Call<Asmrs> listAsmr(@Query("idCodeCis") Long idCodeCis );

    // COMPOSITION
    @GET("compositions/{id}")
    Call<Composition> getComposition(@Path("id") Integer id);

    @GET("compositions")
    Call<Compositions> listComposition(@Query("idCodeCis") Long idCodeCis );

    // CONDITION PRESCRIPTION
    @GET("conditionsPrescriptions/{id}")
    Call<ConditionPrescription> getConditionPrescription(@Path("id") Integer id);

    @GET("conditionsPrescriptions")
    Call<ConditionsPrescriptions> listConditionPrescription(@Query("idCodeCis") Long idCodeCis );

    // GENERIQUE
    @GET("generiques/{id}")
    Call<Generique> getGenerique(@Path("id") Integer id);

    @GET("generiques")
    Call<Generiques> listGenerique(@Query("idCodeCis") Long idCodeCis );

    // INFO IMPORTANTE
    @GET("infosImportantes/{id}")
    Call<InfoImportante> getInfoImportante(@Path("id") Integer id);

    @GET("infosImportantes")
    Call<InfosImportantes> listInfoImportante(@Query("idCodeCis") Long idCodeCis );

    // LIEN CT
    @GET("lientCts/{codeDossierHas}")
    Call<LienCt> getLienCt(@Path("codeDossierHas") String codeDossierHas);

    // PRESENTATION
    @GET("presentations/{id}")
    Call<Presentation> getPresentation(@Path("id") Integer id);

    @GET("presentations/codecip7/{codeCip7}")
    Call<Presentation> getPresentationWithCodeCip7(@Path("codeCip7") String codeCip7);

    @GET("presentations/codecip13/{codeCip13}")
    Call<Presentation> getPresentationWithCodeCip13(@Path("codeCip13") String codeCip13);

    @GET("presentations")
    Call<Presentations> listPresentation(@Query("idCodeCis") Long idCodeCis );

    // SMR
    @GET("smrs/{id}")
    Call<Smr> getSmr(@Path("id") Integer id);

    @GET("smrs")
    Call<Smrs> listSmr(@Query("idCodeCis") Long idCodeCis );

    // SPECIALITE
    @GET("specialites/{idCodeCis}")
    Call<Specialite> getSpecialiteWithCodeCis(@Path("idCodeCis") Long idCodeCis);

    @GET("specialites")
    Call<Specialites> listSpecialiteWithDenomination(@Query("denomination") String denomination);

    // TITULAIRE SPECIALITE
    @GET("titulairesSpecialites/{id}")
    Call<TitulaireSpecialite> getTitulaireSpecialite(@Path("id") Integer id);

    @GET("titulairesSpecialites")
    Call<TitulairesSpecialites> listTitulaireSpecialite(@Query("idCodeCis") Long idCodeCis );

    // VOIES ADMINISTRATION
    @GET("voiesAdministrations/{id}")
    Call<VoiesAdministration> getVoiesAdministration(@Path("id") Integer id);

    @GET("voiesAdministrations")
    Call<VoiesAdministrations> listVoiesAdministration(@Query("idCodeCis") Long idCodeCis );

}
