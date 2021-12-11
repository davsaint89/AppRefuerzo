package com.example.apprefuerzo.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.apprefuerzo.R;
import com.example.apprefuerzo.controladores.RecuperarContraControlador;
import com.example.apprefuerzo.controladores.RegistroControlador;
import com.example.apprefuerzo.utiles.ValidarCorreo;
import com.google.android.material.textfield.TextInputEditText;

public class RecuperarContrasenaActivity extends AppCompatActivity {

    private TextInputEditText tie_correo;
    private Button b_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);
        tie_correo= findViewById(R.id.tie_correo);
        b_enviar= findViewById(R.id.b_enviar);

        b_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(habilitar()){
                    RecuperarContraControlador.login(RecuperarContrasenaActivity.this, getCorreo());
                }else{
                    Toast.makeText(RecuperarContrasenaActivity.this, "Dirección de correo inválida", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private boolean habilitar(){
        String correo = getCorreo().trim();
        if(ValidarCorreo.validar(correo)){
            return true;
        }else{
            return false;
        }
    }
    public String getCorreo() {
        return tie_correo.getText().toString();
    }
}