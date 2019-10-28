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
import com.pillll.pillll.database.entity.Composition;
import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.database.entity.Specialite;
import com.pillll.pillll.viewModel.SpecialiteDetailViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SpecialiteDetailViewModel specialiteDetailViewModel;
    private EditText editText;
    private TextView textViewPresentation;
    private TextView textViewSpecialite;
    private TextView textViewComposition;
    private String libellePresentation;
    private LiveData<Presentation> presentationLiveData;
    private String libelleSpecialite;
    private LiveData<Specialite> specialiteLiveData;
    private LiveData<List<Composition>> compositionLiveData;
    private List<Composition> compositions;
    private List<String> denominationSubstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.idCodeCisArea);
        textViewPresentation = findViewById(R.id.denomination_textView);
        textViewSpecialite = findViewById(R.id.denomination_specialite_textView);
        textViewComposition = findViewById(R.id.denomination_substance_textView);
        specialiteDetailViewModel = ViewModelProviders.of(this).get(SpecialiteDetailViewModel.class);

        // On charge les données du view model
        presentationLiveData = specialiteDetailViewModel.getCurrentPresentation();
        if (presentationLiveData != null){
            libellePresentation = presentationLiveData.getValue().getLibelle();
        }
        if (libellePresentation != null){
            textViewPresentation.setText(libellePresentation);
        }else {
            textViewPresentation.setText("");
        }
        specialiteLiveData = specialiteDetailViewModel.getCurrentSpecialite();
        if (specialiteLiveData != null){
            libelleSpecialite = specialiteLiveData.getValue().getDenomination();
        }
        if (libelleSpecialite != null){
            textViewSpecialite.setText(libelleSpecialite);
        }else {
            textViewSpecialite.setText("");
        }
compositionLiveData = specialiteDetailViewModel.getCurrentCompositions();
        if (compositionLiveData != null){
            compositions = compositionLiveData.getValue();
        }
        if (compositions != null){
            String denominationSubstance = "";
            for (Composition composition: compositions) {
                denominationSubstance += composition.getDenominationSubstance();
                denominationSubstance += "  /  ";
            }
            textViewComposition.setText(denominationSubstance);
        }else {
            textViewComposition.setText("");
        }
    }

    public void getData(View view) {

        //On recupere le code cip 7 ou 13 du textView
        String idCodeCip = editText.getText().toString();
        specialiteDetailViewModel.refreshPresentation(idCodeCip);
        specialiteDetailViewModel.getPresentation(idCodeCip).observeForever(new Observer<Presentation>() {
            @Override
            public void onChanged(@Nullable Presentation presentation) {
                if (presentation != null){
                    textViewPresentation.setText(presentation.getLibelle());
                    long idCodeCis = presentation.getSpecialiteIdCodeCis();
                    specialiteDetailViewModel.refreshSpecialite(idCodeCis);
                    specialiteDetailViewModel.getSpecialite(idCodeCis).observeForever(new Observer<Specialite>() {
                        @Override
                        public void onChanged(@Nullable Specialite specialite) {
                            if (specialite != null){
                                textViewSpecialite.setText(specialite.getDenomination());
                            }
                        }
                    });
                    specialiteDetailViewModel.refreshCompositions(idCodeCis);
                    specialiteDetailViewModel.getCompositions(idCodeCis).observeForever(new Observer<List<Composition>>() {
                        @Override
                        public void onChanged(@Nullable List<Composition> compositions) {
                            if (compositions != null){
                                String denominationSubstance = "";
                                for (Composition composition: compositions) {
                                    denominationSubstance += composition.getDenominationSubstance();
                                    denominationSubstance += "  /  ";
                                }
                                textViewComposition.setText(denominationSubstance);
                            }
                        }
                    });
                }
            }
        });
    }
}
