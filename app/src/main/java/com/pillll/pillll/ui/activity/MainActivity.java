package com.pillll.pillll.ui.activity;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pillll.pillll.R;
import com.pillll.pillll.database.entity.Specialite;
import com.pillll.pillll.repositories.SpecialiteDataRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
