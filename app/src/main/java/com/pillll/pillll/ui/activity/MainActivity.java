package com.pillll.pillll.ui.activity;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pillll.pillll.R;
import com.pillll.pillll.database.entity.Specialite;
import com.pillll.pillll.repositories.SpecialiteDataRepository;

public class MainActivity extends AppCompatActivity {

    // REPOSITORIES
    private SpecialiteDataRepository specialiteDataSource;
/*    private LiveData<Specialite> currentSpecialite;*/
    private Specialite specialite;
    private EditText editText;
    private TextView denominationTextView;
    private TextView formePharmaTextView;
    private String idCodeCis;
    private long idCodeCisLong;
    private String denomination;
    private String formePharma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        specialiteDataSource = new SpecialiteDataRepository(getApplication());
        editText = findViewById(R.id.idCodeCisArea);
        denominationTextView = findViewById(R.id.denomination_textView);
        formePharmaTextView = findViewById(R.id.formePharmaceutique_textView);
    }

    public void getDataFromSqlite(View view) {
/*        idCodeCis = editText.getText().toString();
        idCodeCisLong = Long.valueOf(idCodeCis);
        currentSpecialite = specialiteDataSource.getSpecialiteFromSqliteByCodeCis(idCodeCisLong);
        denomination = currentSpecialite.getValue().getDenomination();
        formePharma = currentSpecialite.getValue().getFormePharmaceutique();
        denominationTextView.setText(denomination);
        formePharmaTextView.setText(formePharma);*/
    }

    public void getPersistableDataFromApi(View view) {
/*        idCodeCis = editText.getText().toString();
        idCodeCisLong = Long.valueOf(idCodeCis);
        currentSpecialite = specialiteDataSource.getPersistableSpecialiteFromApiByCodeCis(idCodeCisLong);
        denomination = currentSpecialite.getValue().getDenomination();
        formePharma = currentSpecialite.getValue().getFormePharmaceutique();
        denominationTextView.setText(denomination);
        formePharmaTextView.setText(formePharma);*/
    }

    public void getDataFromApi(View view) {
        idCodeCis = editText.getText().toString();
        idCodeCisLong = Long.valueOf(idCodeCis);
        specialite = specialiteDataSource.getSpecialiteFromApiByCodeCis(idCodeCisLong);
        denomination = specialite.getDenomination();
        formePharma = specialite.getFormePharmaceutique();
        denominationTextView.setText(denomination);
        formePharmaTextView.setText(formePharma);
    }
}
