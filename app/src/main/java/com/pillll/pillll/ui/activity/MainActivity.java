package com.pillll.pillll.ui.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pillll.pillll.R;
import com.pillll.pillll.model.entities.Asmr;
import com.pillll.pillll.model.entities.Composition;
import com.pillll.pillll.model.entities.ConditionPrescription;
import com.pillll.pillll.model.entities.Generique;
import com.pillll.pillll.model.entities.InfoImportante;
import com.pillll.pillll.model.entities.LienCt;
import com.pillll.pillll.model.entities.Presentation;
import com.pillll.pillll.model.entities.Smr;
import com.pillll.pillll.model.entities.Specialite;
import com.pillll.pillll.model.entities.TitulaireSpecialite;
import com.pillll.pillll.model.entities.VoiesAdministration;
import com.pillll.pillll.viewModel.DetailViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DetailViewModel detailViewModel;
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
    private Button scanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        //ZXING
        scanButton = (Button)findViewById(R.id.scan_button);
        loadViewModel();
    }

    private void loadViewModel(){

        // On charge les données du view model
        presentationLiveData = detailViewModel.getCurrentPresentation();
        if (presentationLiveData != null){
            libellePresentation = presentationLiveData.getValue().getLibelle();
            if (libellePresentation != null){
                textViewPresentation.setText(libellePresentation);
            }else {
                textViewPresentation.setText("");
            }
        }

        specialiteLiveData = detailViewModel.getCurrentSpecialite();
        if (specialiteLiveData != null){
            libelleSpecialite = specialiteLiveData.getValue().getDenomination();
            if (libelleSpecialite != null){
                textViewSpecialite.setText(libelleSpecialite);
            }else {
                textViewSpecialite.setText("");
            }
        }

        compositionLiveData = detailViewModel.getCurrentCompositions();
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

        asmrLiveData = detailViewModel.getCurrentAsmrs();
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

        smrLiveData = detailViewModel.getCurrentSmrs();
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

        infosImportantesLiveData = detailViewModel.getCurrentInfosImportantes();
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

        voiesAdministrationsLiveData = detailViewModel.getCurrentVoiesAdministrations();
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

        conditionPrescriptionLiveData = detailViewModel.getCurrentConditionsPrescriptions();
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

        titulairesSpecialitesLiveData = detailViewModel.getCurrentTitulaireSpecialites();
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

        generiqueLiveData = detailViewModel.getCurrentGenerique();
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

        lienCtLiveData = detailViewModel.getCurrentLienCt();
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

    //SCAN WITH ZXING
    public void Scan(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.initiateScan();
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                getData(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void getData(String idCodeCip) {

        //On recupere le code cip 7 ou 13 du textView
        detailViewModel.refreshData(idCodeCip);
        detailViewModel.getPresentation(idCodeCip).observeForever(new Observer<Presentation>() {
            @Override
            public void onChanged(@Nullable Presentation presentation) {
                if (presentation != null){
                    textViewPresentation.setText(presentation.getLibelle());
                    long idCodeCis = presentation.getSpecialiteIdCodeCis();
                    detailViewModel.getSpecialite(idCodeCis).observeForever(new Observer<Specialite>() {
                        @Override
                        public void onChanged(@Nullable Specialite specialite) {
                            if (specialite != null){
                                textViewSpecialite.setText(specialite.getDenomination());
                            }else {
                                textViewSpecialite.setText("");
                            }
                        }
                    });
                    detailViewModel.getCompositions(idCodeCis).observeForever(new Observer<List<Composition>>() {
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

                    detailViewModel.getAsmrs(idCodeCis).observeForever(new Observer<List<Asmr>>() {
                        @Override
                        public void onChanged(@Nullable List<Asmr> asmrs) {
                            if(asmrs != null || asmrs.size()!=0){
                                String asmrValeur = "Valeur(s) Asmr: ";
                                for (Asmr asmr: asmrs) {
                                    asmrValeur += asmr.getValeur();
                                    asmrValeur += ". ";
                                    detailViewModel.getLiensCts(asmr.getLienCtCodeDossierHas()).observeForever(new Observer<LienCt>() {
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

                    detailViewModel.getSmrs(idCodeCis).observeForever(new Observer<List<Smr>>() {
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

                    detailViewModel.getInfosImportantes(idCodeCis).observeForever(new Observer<List<InfoImportante>>() {
                        @Override
                        public void onChanged(@Nullable List<InfoImportante> infoImportantes) {
                            if (infoImportantes != null || infoImportantes.size()!=0){
                                String descriptionInfoImportante = "Info(s) importante(s): ";
                                for (InfoImportante infoImportante: infoImportantes) {
                                    descriptionInfoImportante += infoImportante.getDescription();
                                    descriptionInfoImportante += ". ";
                                }
                                textViewInfosImportantes.setText(descriptionInfoImportante);
                            }else{
                                textViewInfosImportantes.setText("Info(s) importante(s): ");
                            }

                        }
                    });

                    detailViewModel.getVoiesAdministrations(idCodeCis).observeForever(new Observer<List<VoiesAdministration>>() {
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

                    detailViewModel.getConditionsPrescriptions(idCodeCis).observeForever(new Observer<List<ConditionPrescription>>() {
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

                    detailViewModel.getTitulairesSpecialites(idCodeCis).observeForever(new Observer<List<TitulaireSpecialite>>() {
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

                    detailViewModel.getGeneriques(idCodeCis).observeForever(new Observer<Generique>() {
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
