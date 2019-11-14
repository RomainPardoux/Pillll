package com.pillll.pillll.ui.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.pillll.pillll.R;
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
import com.pillll.pillll.viewModel.SpecialiteDetailViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SpecialiteDetailViewModel specialiteDetailViewModel;
    private EditText editText;
    private TextView textViewPresentation;
    private TextView textViewSpecialite;
    private TextView textViewComposition;
    private TextView textViewSmr;
    private TextView textViewAsmr;
    private TextView textViewTitulaireSpecialite;
    private TextView textViewVoiesAdministration;
    private TextView textViewConditionsPrescription;
    private TextView textViewInfosImportantes;
    private TextView textViewGenerique;
    private TextView textViewLienCt;

    private String libellePresentation;
    private LiveData<Presentation> presentationLiveData;
    private String libelleSpecialite;
    private LiveData<Specialite> specialiteLiveData;
    private LiveData<List<Composition>> compositionLiveData;
    private List<Composition> compositions;

    private LiveData<List<Smr>> smrLiveData;
    private List<Smr> smrs;
    private LiveData<List<Asmr>> asmrLiveData;
    private List<Asmr> asmrs;
    private LiveData<List<ConditionPrescription>> conditionPrescriptionLiveData;
    private List<ConditionPrescription> conditionsPrescriptions;
    private LiveData<List<TitulaireSpecialite>> titulairesSpecialitesLiveData;
    private List<TitulaireSpecialite> titulairesSpecialites;
    private LiveData<List<VoiesAdministration>> voiesAdministrationsLiveData;
    private List<VoiesAdministration> voiesAdministrations;
    private LiveData<List<InfoImportante>> infosImportantesLiveData;
    private List<InfoImportante> infosImportantes;
    private LiveData<Generique> generiqueLiveData;
    private LiveData<LienCt> lienCtLiveData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.idCodeCisArea);
        textViewPresentation = findViewById(R.id.denomination_presentation_textView);
        textViewSpecialite = findViewById(R.id.denomination_specialite_textView);
        textViewComposition = findViewById(R.id.denomination_substance_textView);

        textViewAsmr = findViewById(R.id.asmr_textView);
        textViewSmr = findViewById(R.id.smr_textView);
        textViewConditionsPrescription = findViewById(R.id.condition_prescription_textView);
        textViewGenerique = findViewById(R.id.generique_textView);
        textViewInfosImportantes = findViewById(R.id.info_importante_textView);
        textViewLienCt = findViewById(R.id.lien_ct_textView);
        textViewTitulaireSpecialite = findViewById(R.id.titulaire_specialite_textView);
        textViewVoiesAdministration = findViewById(R.id.voies_administrations_textView);


        specialiteDetailViewModel = ViewModelProviders.of(this).get(SpecialiteDetailViewModel.class);
        loadViewModel();
    }

    private void loadViewModel(){

        // On charge les données du view model
        presentationLiveData = specialiteDetailViewModel.getCurrentPresentation();
        if (presentationLiveData != null){
            libellePresentation = presentationLiveData.getValue().getLibelle();
            if (libellePresentation != null){
                textViewPresentation.setText(libellePresentation);
            }else {
                textViewPresentation.setText("");
            }
        }

        specialiteLiveData = specialiteDetailViewModel.getCurrentSpecialite();
        if (specialiteLiveData != null){
            libelleSpecialite = specialiteLiveData.getValue().getDenomination();
            if (libelleSpecialite != null){
                textViewSpecialite.setText(libelleSpecialite);
            }else {
                textViewSpecialite.setText("");
            }
        }

        compositionLiveData = specialiteDetailViewModel.getCurrentCompositions();
        if (compositionLiveData != null){
            compositions = compositionLiveData.getValue();
            if (compositions != null){
                String denominationSubstance = "Composition(s): ";
                for (Composition composition: compositions) {
                    denominationSubstance += composition.getDenominationSubstance();
                    denominationSubstance += ". ";
                }
                textViewComposition.setText(denominationSubstance);
            }else {
                textViewComposition.setText("Composition(s): ");
            }

        }


        asmrLiveData = specialiteDetailViewModel.getCurrentAsmrs();
        if (asmrLiveData != null){
            asmrs = asmrLiveData.getValue();
            if (asmrs != null){
                String asmrValeur = "Valeur(s) Asmr: ";
                for (Asmr asmr: asmrs) {
                    asmrValeur += asmr.getValeur();
                    asmrValeur += ". ";
                }
                textViewAsmr.setText(asmrValeur);
            }else {
                textViewAsmr.setText("Valeur(s) Asmr: ");
            }

        }

        smrLiveData = specialiteDetailViewModel.getCurrentSmrs();
        if (smrLiveData != null){
            smrs = smrLiveData.getValue();
            if (smrs != null){
                String smrValeur = "Valeur(s) Smr: ";
                for (Smr smr: smrs) {
                    smrValeur += smr.getValeur();
                    smrValeur += ". ";
                }
                textViewSmr.setText(smrValeur);
            }else {
                textViewSmr.setText("Valeur(s) Smr: ");
            }
        }

        infosImportantesLiveData = specialiteDetailViewModel.getCurrentInfosImportantes();
        if (infosImportantesLiveData != null){
            infosImportantes = infosImportantesLiveData.getValue();
            if (infosImportantes != null){
                String descriptionInfoImportante = "Info(s) importante(s): ";
                for (InfoImportante infoImportante: infosImportantes) {
                    descriptionInfoImportante += infoImportante.getDescription();
                    descriptionInfoImportante += ". ";
                }
                textViewInfosImportantes.setText(descriptionInfoImportante);
            }else{
                textViewInfosImportantes.setText("Info(s) importante(s): ");
            }
        }

        voiesAdministrationsLiveData = specialiteDetailViewModel.getCurrentVoiesAdministrations();
        if (voiesAdministrationsLiveData != null){
            voiesAdministrations = voiesAdministrationsLiveData.getValue();
            if (voiesAdministrations != null){
                String voiesAdministrationLibelle= "Voie(s) administration(s): ";
                for (VoiesAdministration voiesAdministartion:voiesAdministrations ) {
                    voiesAdministrationLibelle += voiesAdministartion.getVoiesAdministration();
                    voiesAdministrationLibelle += ". ";
                }
                textViewVoiesAdministration.setText(voiesAdministrationLibelle);
            }else {
                textViewVoiesAdministration.setText("Voie(s) administration(s): ");
            }
        }

        conditionPrescriptionLiveData = specialiteDetailViewModel.getCurrentConditionsPrescriptions();
        if (conditionPrescriptionLiveData != null){
            conditionsPrescriptions = conditionPrescriptionLiveData.getValue();
            if (conditionsPrescriptions != null){
                String conditionsPrescriptionsLibelle = "Condition(s) prescription(s): ";
                for (ConditionPrescription conditionPrescription: conditionsPrescriptions) {
                    conditionsPrescriptionsLibelle += conditionPrescription.getConditionPrescription();
                    conditionsPrescriptionsLibelle += ". ";
                }
                textViewConditionsPrescription.setText(conditionsPrescriptionsLibelle);
            }else {
                textViewConditionsPrescription.setText("Condition(s) prescription(s): ");
            }
        }

        titulairesSpecialitesLiveData = specialiteDetailViewModel.getCurrentTitulaireSpecialites();
        if(titulairesSpecialitesLiveData != null){
            titulairesSpecialites = titulairesSpecialitesLiveData.getValue();
            if (titulairesSpecialites != null){
                String titulaireSpecialiteLibelle = "Titulaire(s) specialite(s): ";
                for (TitulaireSpecialite titulaireSpecialite: titulairesSpecialites) {
                    titulaireSpecialiteLibelle += titulaireSpecialite.getTitulaire();
                    titulaireSpecialiteLibelle += ". ";
                }
                textViewTitulaireSpecialite.setText(titulaireSpecialiteLibelle);
            }else {
                textViewTitulaireSpecialite.setText("Titulaire(s) specialite(s): ");
            }
        }

        generiqueLiveData = specialiteDetailViewModel.getCurrentGenerique();
        if (generiqueLiveData != null){
            String generiqueLiebelle = "Libelle Groupe: ";
            Generique generique = generiqueLiveData.getValue();
            if (generique!=null){
                generiqueLiebelle += generique.getLibelleGroupe();
            }
            if (generiqueLiebelle != null){
                textViewGenerique.setText(generiqueLiebelle);
            }else {
                textViewGenerique.setText("Libelle Groupe: ");
            }
        }

        lienCtLiveData = specialiteDetailViewModel.getCurrentLienCt();
        if (lienCtLiveData != null){
            String lienCtLibelle = "Lien Ct: ";
            LienCt lienCt = lienCtLiveData.getValue();
            if (lienCt!=null){
                lienCtLibelle += lienCt.getLienAvisCt();
            }
            if (lienCtLibelle != null){
                textViewLienCt.setText(lienCtLibelle);
            }else {
                textViewLienCt.setText("Lien Ct: ");
            }
        }
    }

    public void getData(View view) {

        //On recupere le code cip 7 ou 13 du textView
        String idCodeCip = editText.getText().toString();
        specialiteDetailViewModel.refreshData(idCodeCip);
        specialiteDetailViewModel.getPresentation(idCodeCip).observeForever(new Observer<Presentation>() {
            @Override
            public void onChanged(@Nullable Presentation presentation) {
                if (presentation != null){
                    textViewPresentation.setText(presentation.getLibelle());
                    long idCodeCis = presentation.getSpecialiteIdCodeCis();
                    specialiteDetailViewModel.getSpecialite(idCodeCis).observeForever(new Observer<Specialite>() {
                        @Override
                        public void onChanged(@Nullable Specialite specialite) {
                            if (specialite != null){
                                textViewSpecialite.setText(specialite.getDenomination());
                            }else {
                                textViewSpecialite.setText("");
                            }
                        }
                    });
                    specialiteDetailViewModel.getCompositions(idCodeCis).observeForever(new Observer<List<Composition>>() {
                        @Override
                        public void onChanged(@Nullable List<Composition> compositions) {
                            if (compositions != null || compositions.size() != 0){
                                String denominationSubstance = "Composition(s): ";
                                for (Composition composition: compositions) {
                                    denominationSubstance += composition.getDenominationSubstance();
                                    denominationSubstance += ". ";
                                }
                                textViewComposition.setText(denominationSubstance);
                            }else {
                                textViewComposition.setText("Composition(s): ");
                            }
                        }
                    });

                    specialiteDetailViewModel.getAsmrs(idCodeCis).observeForever(new Observer<List<Asmr>>() {
                        @Override
                        public void onChanged(@Nullable List<Asmr> asmrs) {
                            if(asmrs != null || asmrs.size()!=0){
                                String asmrValeur = "Valeur(s) Asmr: ";
                                for (Asmr asmr: asmrs) {
                                    asmrValeur += asmr.getValeur();
                                    asmrValeur += ". ";
                                    specialiteDetailViewModel.getLiensCts(asmr.getLienCtCodeDossierHas()).observeForever(new Observer<LienCt>() {
                                        @Override
                                        public void onChanged(@Nullable LienCt lienCt) {
                                            if (lienCt!=null){
                                                String lienCtLiebelle = "Lien avis ct: ";
                                                lienCtLiebelle += lienCt.getLienAvisCt();
                                                if (lienCtLiebelle != null) {
                                                    textViewGenerique.setText(lienCtLiebelle);
                                                }

                                            }else {
                                                textViewGenerique.setText("Lien avis ct: ");
                                            }
                                        }
                                    });
                                }
                                textViewAsmr.setText(asmrValeur);
                            }else {
                                textViewAsmr.setText("Valeur(s) Asmr: ");
                                textViewGenerique.setText("Lien avis ct: ");
                            }
                        }
                    });

                    specialiteDetailViewModel.getSmrs(idCodeCis).observeForever(new Observer<List<Smr>>() {
                        @Override
                        public void onChanged(@Nullable List<Smr> smrs) {
                            if(smrs != null || smrs.size()!=0){
                                String smrValeur = "Valeur(s) smr: ";
                                for (Smr smr: smrs) {
                                    smrValeur += smr.getValeur();
                                    smrValeur += ". ";
                                }
                                textViewSmr.setText(smrValeur);
                            }else {
                                textViewSmr.setText("Valeur(s) smr: ");
                            }
                        }
                    });

                    specialiteDetailViewModel.getInfosImportantes(idCodeCis).observeForever(new Observer<List<InfoImportante>>() {
                        @Override
                        public void onChanged(@Nullable List<InfoImportante> infoImportantes) {
                            if (infosImportantes != null || infoImportantes.size()!=0){
                                String descriptionInfoImportante = "Info(s) importante(s): ";
                                for (InfoImportante infoImportante: infosImportantes) {
                                    descriptionInfoImportante += infoImportante.getDescription();
                                    descriptionInfoImportante += ". ";
                                }
                                textViewInfosImportantes.setText(descriptionInfoImportante);
                            }else{
                                textViewInfosImportantes.setText("Info(s) importante(s): ");
                            }

                        }
                    });

                    specialiteDetailViewModel.getVoiesAdministrations(idCodeCis).observeForever(new Observer<List<VoiesAdministration>>() {
                        @Override
                        public void onChanged(@Nullable List<VoiesAdministration> voiesAdministrations) {
                            if (voiesAdministrations!=null || voiesAdministrations.size() != 0){
                                String voiesAdministrationLibelle= "Voie(s) administration(s): ";
                                for (VoiesAdministration voiesAdministartion: voiesAdministrations ) {
                                    voiesAdministrationLibelle += voiesAdministartion.getVoiesAdministration();
                                    voiesAdministrationLibelle += ". ";
                                }
                                textViewVoiesAdministration.setText(voiesAdministrationLibelle);
                            }else {
                                textViewVoiesAdministration.setText("Voie(s) administration(s): ");
                            }
                        }
                    });

                    specialiteDetailViewModel.getConditionsPrescriptions(idCodeCis).observeForever(new Observer<List<ConditionPrescription>>() {
                        @Override
                        public void onChanged(@Nullable List<ConditionPrescription> conditionPrescriptions) {
                            if (conditionPrescriptions != null || conditionPrescriptions.size() != 0){
                                String conditionsPrescriptionsLibelle = "Condition(s) prescription(s): ";
                                for (ConditionPrescription conditionPrescription: conditionPrescriptions) {
                                    conditionsPrescriptionsLibelle += conditionPrescription.getConditionPrescription();
                                    conditionsPrescriptionsLibelle += ". ";
                                }
                                textViewConditionsPrescription.setText(conditionsPrescriptionsLibelle);
                            }else {
                                textViewConditionsPrescription.setText("Condition(s) prescription(s): ");
                            }
                        }
                    });

                    specialiteDetailViewModel.getTitulairesSpecialites(idCodeCis).observeForever(new Observer<List<TitulaireSpecialite>>() {
                        @Override
                        public void onChanged(@Nullable List<TitulaireSpecialite> titulaireSpecialites) {
                            if (titulaireSpecialites!=null || titulaireSpecialites.size()!=0){
                                String titulaireSpecialiteLibelle = "Titulaire(s) specialite(s): ";
                                for (TitulaireSpecialite titulaireSpecialite: titulaireSpecialites) {
                                    titulaireSpecialiteLibelle += titulaireSpecialite.getTitulaire();
                                    titulaireSpecialiteLibelle += ". ";
                                }
                                textViewTitulaireSpecialite.setText(titulaireSpecialiteLibelle);
                            }else {
                                textViewTitulaireSpecialite.setText("Titulaire(s) specialite(s): ");
                            }
                        }
                    });

                    specialiteDetailViewModel.getGeneriques(idCodeCis).observeForever(new Observer<Generique>() {
                        @Override
                        public void onChanged(@Nullable Generique generique) {
                            if (generique!= null){
                                String generiqueLiebelle = "Libelle Groupe: ";
                                generiqueLiebelle += generique.getLibelleGroupe();
                                if (generiqueLiebelle != null) {
                                    textViewGenerique.setText(generiqueLiebelle);
                                }
                            }else {
                                textViewGenerique.setText("Libelle Groupe: ");
                            }

                        }
                    });
                }
            }
        });
    }
}
