package com.example.apprefuerzo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String usuario= "", contraseña = "";
    private EditText et_usuario, et_contraseña;
    private Button b_login;
    private TextView tv_olvidocontra, tv_tv_registrarse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_usuario= findViewById(R.id.et_usuario);
        et_contraseña= findViewById(R.id.et_contraseña);
        b_login= findViewById(R.id.b_login);
        tv_olvidocontra= findViewById(R.id.tv_olvidocontra);
        tv_tv_registrarse= findViewById(R.id.tv_registrarse);


        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario= et_usuario.getText().toString();
                contraseña= et_contraseña.getText().toString();
                Toast.makeText(MainActivity.this, "Entrando con " + usuario + " " + contraseña, Toast.LENGTH_LONG).show();
            }
        });
        tv_olvidocontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //intenciones en las actividades
                startActivity(new Intent(MainActivity.this, RecuperarContrasenaActivity.class));
            }
        });

        tv_tv_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrarseActivity.class));
            }
        });
    }
}