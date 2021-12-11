package com.example.apprefuerzo.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.apprefuerzo.R;
import com.example.apprefuerzo.controladores.RegistroControlador;
import com.example.apprefuerzo.utiles.ValidarCorreo;
import com.google.android.material.textfield.TextInputEditText;

public class RegistroActivity extends AppCompatActivity {

    private TextInputEditText tie_nombre, tie_correo,tie_contraseña, tie_confirmarContra;
    private Button b_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        tie_nombre = findViewById(R.id.tie_nombre);
        tie_correo = findViewById(R.id.tie_correo);
        tie_contraseña = findViewById(R.id.tie_contraseña);
        tie_confirmarContra = findViewById(R.id.tie_confirmarContra);
        b_registrar= findViewById(R.id.b_registrar);


        b_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(habilitar()){
                    RegistroControlador.registro(RegistroActivity.this, getNombre(), getCorreo(), getContraseña());
                }else{
                    Toast.makeText(RegistroActivity.this, "Ingreso de datos inválidos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean habilitar(){

        String nombre = getNombre().trim();
        String correo = getCorreo().trim();
        String contraseña = getContraseña().trim();
        String confirmarContra = getConfirmarContra().trim();

        if(nombre.length()>3 && ValidarCorreo.validar(correo) && contraseña.length() > 5 && confirmarContra.equals(contraseña)){
            return true;
        }else{
            return false;
        }
    }


    public String getNombre() {
        return tie_nombre.getText().toString();
    }

    public String getCorreo() {
        return tie_correo.getText().toString();
    }

    public String getContraseña() {
        return tie_contraseña.getText().toString();
    }

    public String getConfirmarContra() {
        return tie_confirmarContra.getText().toString();
    }
}