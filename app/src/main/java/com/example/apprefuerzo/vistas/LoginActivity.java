package com.example.apprefuerzo.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apprefuerzo.MainActivity;
import com.example.apprefuerzo.R;
import com.example.apprefuerzo.controladores.LoginControlador;
import com.example.apprefuerzo.utiles.ValidarCorreo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private String usuario= "", contraseña = "";
    private EditText et_usuario, et_contraseña;
    private Button b_login;
    private TextView tv_olvidocontra, tv_tv_registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioConectado();

        et_usuario = findViewById(R.id.et_usuario);
        et_contraseña = findViewById(R.id.et_contraseña);
        b_login = findViewById(R.id.b_login);
        tv_olvidocontra = findViewById(R.id.tv_olvidocontra);
        tv_tv_registrarse = findViewById(R.id.tv_registrarse);



        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (habilitar()){
                    LoginControlador.login(LoginActivity.this, getCorreo(), getContraseña());
                }else {
                    Toast.makeText(LoginActivity.this, "Ingreso de datos no validos", Toast.LENGTH_SHORT).show();
                }


//                usuario = et_usuario.getText().toString();
//                contraseña = et_contraseña.getText().toString();
//                LoginCOntrolador.login(LoginActivity.this, );
//                Toast.makeText(LoginActivity.this, "Entrando con " + usuario + " " + contraseña, Toast.LENGTH_LONG).show();
            }
        });
        tv_olvidocontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //intenciones en las actividades
                startActivity(new Intent(LoginActivity.this, RecuperarContrasenaActivity.class));
            }
        });

        tv_tv_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
            }
        });
    }

    private boolean habilitar(){

            String correo = getCorreo().trim();
            String contraseña = getContraseña().trim();
            if(ValidarCorreo.validar(correo) && contraseña.length() > 5){
                return true;
            }else{
                return false;
            }
        }


        public String getCorreo() {
            return et_usuario.getText().toString();
        }

        public String getContraseña() {
            return et_contraseña.getText().toString();
        }

    private void usuarioConectado() {

        FirebaseUser usuario= FirebaseAuth.getInstance().getCurrentUser();
        if(usuario!=null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}


