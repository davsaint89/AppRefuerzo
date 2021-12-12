package com.example.apprefuerzo.vistas.perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.apprefuerzo.R;

public class PerfilActivity extends AppCompatActivity {

    private ImageButton ib_atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ib_atras= findViewById(R.id.ib_atras);
        ib_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_perfil, new PerfilFragment())
                .commit();
    }
}