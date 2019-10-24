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
import android.widget.Toast;

import com.pillll.pillll.R;
import com.pillll.pillll.database.PillllWebService;
import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.repositories.PresentationDataRepository;
import com.pillll.pillll.viewModel.SpecialiteDetailViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // REPOSITORIES
    private SpecialiteDetailViewModel specialiteDetailViewModel;
    private LiveData<Presentation> currentPresentation;
    private EditText editText;
    private TextView textView;

    //TEST
    private LiveData<String> text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.idCodeCisArea);
        textView = findViewById(R.id.denomination_textView);
        specialiteDetailViewModel = ViewModelProviders.of(this).get(SpecialiteDetailViewModel.class);
        specialiteDetailViewModel.refreshPresentation("2756239");
        specialiteDetailViewModel.getCurrentPresentations("2756239").observeForever(new Observer<Presentation>() {
            @Override
            public void onChanged(@Nullable Presentation presentation) {
                Log.d(this.getClass().getCanonicalName(), "onChanged");
                if (presentation != null){
                    Log.d(this.getClass().getCanonicalName(), "onChanged_presentationNotNull");
                    textView.setText(presentation.getLibelle());
                }
            }
        });
    }

/*    public void getDataFromApi(View view) {

        //IdCodeCis input
        *//*String idCodeCip = editText.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PillllWebService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PillllWebService presentationWebService = retrofit.create(PillllWebService.class);

        Call<Presentation> call = presentationWebService.getPresentationWithCodeCip7(idCodeCip);
        call.enqueue(new Callback<Presentation>() {
            @Override
            public void onResponse(Call<Presentation> call, Response<Presentation> response) {
                textView.setText(response.body().getLibelle());
            }

            @Override
            public void onFailure(Call<Presentation> call, Throwable t) {
                // action à effectuer en cas d'echec
            }
        });
*//*

        *//*specialiteDetailViewModel.initViewModel(idCodeCip);
        currentPresentation = specialiteDetailViewModel.getCurrentPresentations();
        textView.setText(currentPresentation.getValue().getLibelle());
    *//*}*/
}
